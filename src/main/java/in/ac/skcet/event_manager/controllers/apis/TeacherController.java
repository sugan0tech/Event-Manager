package in.ac.skcet.event_manager.controllers.apis;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.event.*;
import in.ac.skcet.event_manager.exception.OdFormNotFoundException;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.attendance.AttendanceRepository;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.firebase_notification.PushNotificationService;
import in.ac.skcet.event_manager.on_duty.OnDutyForm;
import in.ac.skcet.event_manager.on_duty.OnDutyFormService;
import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.student.StudentStat;
import in.ac.skcet.event_manager.teacher.StaffEventTimer;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
@Slf4j
public class TeacherController {

    TeacherService teacherService;
    EventService eventService;
    EventStatService eventStatService;
    EventCmdToEvent eventCmdToEvent;
    StudentService studentService;
    AttendanceRepository attendanceRepository;
    PushNotificationService pushNotificationService;
    StaffEventTimer staffEventTimer;
    OnDutyFormService onDutyFormService;


    @PostMapping("/events/pending/{staffId}")
    public List<Event> getEvents(@PathVariable String staffId) throws TeacherNotFoundException{

       log.info(staffId);
       log.info(teacherService.findById("srigirit369").toString());
       return teacherService.findEvents(staffId);
    }

    @PostMapping("/events/past-five/{classCode}")
    public List<Event> getPastFiveEvents(@PathVariable String classCode) {
        return eventService.getPastFiveEvents(classCode);
    }


    @PostMapping("/event/stats/{eventId}/{classCode}")
    public Map<String, Integer> getEventStatus(@PathVariable Integer eventId, @PathVariable String classCode){
        return eventStatService.getEventStat(eventId, classCode);
    }

    @PostMapping("/event/stats-list/{eventId}/{classCode}")
    public List<StudentStat> getStudentWithStats(@PathVariable Integer eventId, @PathVariable  String classCode){
        return eventStatService.getStudentStatusList(eventId, classCode);
    }

    @PostMapping("/event/new")
    public String createEvent(@ModelAttribute EventCommand eventCommand) throws FirebaseMessagingException {
        Event event = eventCmdToEvent.convert(eventCommand);
        if(event == null) {
            log.info("Invalid Event!");
            return "invalid";
        }
        else {
            log.info(eventService.save(event).toString());
            log.info(event.getClassCode());
            pushNotificationService.eventNotification(event);
            staffEventTimer.setTimerForEvent(event);
        }

        return "added";
    }

    @PostMapping("/student/getList/{classCode}")
    public Map<String, String> getList(@PathVariable String classCode){
        Map<String, String> studentList = new TreeMap<>();
        studentService.findByClassCode(classCode).forEach(student ->
                studentList.put(student.getRollNo(), student.getName())
        );
        return studentList;
    }

    @PostMapping("/student/attendanceList/{classCode}/{date}")
    public Map<String, Map<String, Map<String, Boolean>>> getAttendanceList(@PathVariable String classCode, @PathVariable String date){

        Map<String, Map<String, Map<String, Boolean>>> studentList = new TreeMap<>();

        studentService.findByClassCode(classCode).forEach(student -> {
            Map<String, Map<String, Boolean>> intermediateStudentData = new HashMap<>();
            Map<String, Boolean> booleanMap = new HashMap<>();
            booleanMap.put("isPresent", studentService.isPresent(student.getRollNo(), date));
            booleanMap.put("onOd", student.getOnDuty());
            intermediateStudentData.put(student.getName(), booleanMap);
            studentList.put(student.getRollNo(), intermediateStudentData);
            });
        return studentList;
    }

    @PostMapping("/cancelOd/{id}")
    public void cancelOd(@PathVariable Long id) throws OdFormNotFoundException {
        OnDutyForm onDutyForm = onDutyFormService.findById(id);
        onDutyForm.getStudentSet().forEach(student -> {
            student.setOnDuty(false);
            studentService.save(student);
        });
        onDutyFormService.delete(id);
    }

    @PostMapping("/getOdList/{classCode}")
    public List<OnDutyForm> getOdList(@PathVariable String classCode){
        return onDutyFormService.findByClassCode(classCode);
    }

    @PostMapping("/student/attendance/{classCode}/{date}")
    public void addAttendance(@PathVariable String classCode, @PathVariable String date, @RequestBody Map<String, String> attendanceForm ) throws FirebaseMessagingException{
        log.info(attendanceForm.toString());
        final Attendance attendance = attendanceRepository.findByDate(java.sql.Date.valueOf(date)).orElse(Attendance.builder().date(java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))).build());
        attendanceRepository.save(attendance);
        attendanceForm.forEach((studentId, status) -> {
            Student student;
            try {
                student = studentService.findByID(studentId);
            } catch (StudentNotFoundException e) {
                throw new RuntimeException(e);
            }
            if(status.equals("true")){
                student.addAttendance(attendance);
            }else{
                if(studentService.isPresent(studentId, new SimpleDateFormat("yyyy-MM-dd").format(attendance.getDate()))){
                    assert student != null;
                    student.getAttendanceSet().remove(attendance);
                }
            }
            studentService.save(student);
        });

        pushNotificationService.attendanceNotification(classCode, attendance);
    }
}
