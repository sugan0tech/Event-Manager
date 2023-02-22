package in.ac.skcet.event_manager.controllers.apis;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.commands.EventCmdToEvent;
import in.ac.skcet.event_manager.commands.EventCommand;
import in.ac.skcet.event_manager.models.*;
import in.ac.skcet.event_manager.repositories.AttendanceRepository;
import in.ac.skcet.event_manager.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    TimerService timerService;


    @PostMapping("/events/pending/{staffId}")
    public List<Event> getEvents(@PathVariable String staffId){

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
            timerService.setTimerForEvent(event);
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
    public Map<String, Map<String, Boolean>> getAttendanceList(@PathVariable String classCode, @PathVariable String date){

        Map<String, Map<String, Boolean>> studentList = new TreeMap<>();

        studentService.findByClassCode(classCode).forEach(student -> {
            Map<String, Boolean> intermediateStudentData = new HashMap<>();
            intermediateStudentData.put(student.getName(), studentService.isPresent(student.getRollNo(), date));
            studentList.put(student.getRollNo(), intermediateStudentData);
            });
        return studentList;
    }

    @PostMapping("/student/attendance/{classCode}/{date}")
    public void addAttendance(@PathVariable String classCode, @PathVariable String date, @RequestBody Map<String, String> attendanceForm ) throws FirebaseMessagingException {
        log.info(attendanceForm.toString());
        final Attendance attendance = attendanceRepository.findByDate(java.sql.Date.valueOf(date)).orElse(Attendance.builder().date(java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))).build());
        attendanceRepository.save(attendance);
        attendanceForm.forEach((studentId, status) -> {
            if(status.equals("true")){
                Student student = studentService.findByID(studentId).orElse(null);
                if(student == null ){
                    log.info("null null");
                }else{
                    student.addAttendance(attendance);
                    studentService.save(student);
                }
            }
        });

        pushNotificationService.attendanceNotification(classCode, attendance);
    }
}
