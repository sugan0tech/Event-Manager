package in.ac.skcet.event_manager.controllers.apis;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.attendance.AttendanceService;
import in.ac.skcet.event_manager.event.*;
import in.ac.skcet.event_manager.exception.OdFormNotFoundException;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.attendance.AttendanceRepository;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.firebase_notification.PushNotificationService;
import in.ac.skcet.event_manager.on_duty.OnDutyForm;
import in.ac.skcet.event_manager.on_duty.OnDutyFormService;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.student.StudentStat;
import in.ac.skcet.event_manager.teacher.StaffEventTimer;
import in.ac.skcet.event_manager.teacher.TeacherService;
import in.ac.skcet.event_manager.time_table.TimeTableHoursService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
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
    AttendanceService attendanceService;


    @PostMapping("/events/pending/{staffId}")
    public List<Event> getEvents(@PathVariable String staffId) throws TeacherNotFoundException{
       log.info(staffId);
       log.info(teacherService.findById(staffId).toString());
       return teacherService.findEvents(staffId);
    }

    @PostMapping("/getClassCode/{staffId}")
    public String getClassCode(@PathVariable String staffId) throws TeacherNotFoundException {
        return teacherService.findById(staffId).getClassCode();
    }

    @PostMapping("/events/past-five/{staffId}")
    public List<Event> getPastFiveEvents(@PathVariable String staffId) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        return eventService.getPastFiveEvents(classCode);
    }

    @PostMapping("/event/stats/{eventId}/{staffId}")
    public Map<String, Integer> getEventStatus(@PathVariable Integer eventId, @PathVariable String staffId) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        return eventStatService.getEventStat(eventId, classCode);
    }

    @PostMapping("/event/stats-list/{eventId}/{staffId}")
    public List<StudentStat> getStudentWithStats(@PathVariable Integer eventId, @PathVariable  String staffId) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
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

    @PostMapping("/student/getList/{staffId}")
    public Map<String, String> getList(@PathVariable String staffId) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        Map<String, String> studentList = new TreeMap<>();
        studentService.findByClassCode(classCode).forEach(student ->
                studentList.put(student.getRollNo(), student.getName())
        );
        return studentList;
    }

    @PostMapping("/student/attendanceList/{staffId}/{date}")
    public Map<String, Map<String, Map<String, Boolean>>> getAttendanceList(@PathVariable String staffId, @PathVariable String date) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();

        Map<String, Map<String, Map<String, Boolean>>> studentList = new TreeMap<>();

        studentService.findByClassCode(classCode).forEach(student -> {
            Map<String, Map<String, Boolean>> intermediateStudentData = new HashMap<>();
            Map<String, Boolean> booleanMap = new HashMap<>();
            try {
                booleanMap.put("isPresent", attendanceService.isPresent(student.getRollNo(), date));
            } catch (StudentNotFoundException e) {
                log.info(e.toString());
            }
            booleanMap.put("onOd", student.getOnDuty());
            intermediateStudentData.put(student.getName(), booleanMap);
            studentList.put(student.getRollNo(), intermediateStudentData);
            });
        return studentList;
    }

    @PostMapping("/getAttendancePercentage/daily/{staffId}/{startDate}/{endDate}")
    public Map<String, Double> getAttendancePercentageDaily(@PathVariable String staffId, @PathVariable String startDate, @PathVariable String endDate) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        return attendanceService.getAttendancePercentageDaily(classCode, new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }

    @PostMapping("/getAttendancePercentage/hourly/{staffId}/{startDate}/{endDate}")
    public Map<String, Double> getAttendancePercentageHourly(@PathVariable String staffId, @PathVariable String startDate, @PathVariable String endDate) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        return attendanceService.getAttendancePercentageHourly(classCode, new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
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

    @PostMapping("/getOdList/{staffId}")
    public List<OnDutyForm> getOdList(@PathVariable String staffId) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        return onDutyFormService.findByClassCode(classCode);
    }

    @PostMapping("/student/attendance/{staffId}/{date}")
    public void addAttendance(@PathVariable String staffId, @PathVariable String date, @RequestBody Map<String, String> attendanceForm ) throws FirebaseMessagingException, TeacherNotFoundException {

        final Attendance attendance = attendanceService.findByDate(date);

        attendanceForm.forEach((studentId, status) -> {
            try {
                attendanceService.updateAttendance(studentId, attendance, Boolean.valueOf(status));
            } catch (StudentNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        pushNotificationService.attendanceNotificationPerStaff(teacherService.findById(staffId), attendance);
    }
    @PostMapping("/student/attendance/stats/{rollNo}/{startDate}/{endDate}")
    public List<Map<String, Map<String, Boolean>>> getStudentAttendanceStat(@PathVariable  String rollNo,@PathVariable String startDate,@PathVariable String endDate){
        return attendanceService.getAttendancePerStudentAtRange(rollNo, new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }
    @PostMapping("/student/attendance/statsPerClass/{staffId}/{startDate}/{endDate}")
    public List<List<Map<String, Map<String, Boolean>>>> getStudentAttendanceStatClass(@PathVariable  String staffId,@PathVariable String startDate,@PathVariable String endDate) throws TeacherNotFoundException {
        return attendanceService.getAttendancePerClassAtRange(teacherService.findById(staffId).getClassCode(), new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }
    @PostMapping("/attendance/all/{date}")
    public List<Map<String, Map<String, Integer>>> getAttendanceStatForAllClass(@PathVariable String date){
        return attendanceService.getAttendancePerClass(date);
    }
    @PostMapping("getPeriod")
    public int getPeriod(){
        return TimeTableHoursService.getPeriodNumber(LocalTime.now());
    }

}
