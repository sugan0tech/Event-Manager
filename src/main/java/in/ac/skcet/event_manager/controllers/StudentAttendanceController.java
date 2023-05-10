package in.ac.skcet.event_manager.controllers;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.attendance.AttendanceStudentService;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.firebase_notification.PushNotificationService;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.teacher.TeacherService;
import in.ac.skcet.event_manager.time_table.TimeTableHoursService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/attendance")
public class StudentAttendanceController {
    StudentService studentService;
    TeacherService teacherService;
    AttendanceStudentService attendanceService;
    PushNotificationService pushNotificationService;


    private Map<String, Map<String, Map<String, Boolean>>> getStringMapMap(@PathVariable String date, String classCode) {
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

    @PostMapping("/put-curr/{staffId}")
    public void addAttendance(@PathVariable String staffId, @RequestBody Map<String, String> attendanceForm ) throws FirebaseMessagingException, TeacherNotFoundException {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDate.format(formatter);

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

    @PostMapping("/put/{staffId}/{date}")
    public void addAttendanceWithDate(@PathVariable String staffId, @PathVariable String date, @RequestBody Map<String, String> attendanceForm ) throws FirebaseMessagingException, TeacherNotFoundException {

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

    @PostMapping("/list/{staffId}/{date}")
    public Map<String, Map<String, Map<String, Boolean>>> getAttendanceList(@PathVariable String staffId, @PathVariable String date) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        return getStringMapMap(date, classCode);
    }


    @PostMapping("/list-by-class-code/{classCode}/{date}")
    public Map<String, Map<String, Map<String, Boolean>>> getAttendanceListByClassCode(@PathVariable String classCode, @PathVariable String date) {

        return getStringMapMap(date, classCode);
    }

    @PostMapping("/percentage-daily/{staffId}/{startDate}/{endDate}")
    public Map<String, Double> getAttendancePercentageDaily(@PathVariable String staffId, @PathVariable String startDate, @PathVariable String endDate) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        return attendanceService.getAttendancePercentageDaily(classCode, new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }

    @PostMapping("/percentage-daily-by-class-code/{classCode}/{startDate}/{endDate}")
    public Map<String, Double> getAttendancePercentageDailyByClassCode(@PathVariable String classCode, @PathVariable String startDate, @PathVariable String endDate) {
        return attendanceService.getAttendancePercentageDaily(classCode, new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }

    @PostMapping("/percentage-hourly/{staffId}/{startDate}/{endDate}")
    public Map<String, Double> getAttendancePercentageHourly(@PathVariable String staffId, @PathVariable String startDate, @PathVariable String endDate) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        return attendanceService.getAttendancePercentageHourly(classCode, new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }
    @PostMapping("/percentage-hourly-by-class-code/{classCode}/{startDate}/{endDate}")
    public Map<String, Double> getAttendancePercentageHourlyByClassCode(@PathVariable String classCode, @PathVariable String startDate, @PathVariable String endDate){
        return attendanceService.getAttendancePercentageHourly(classCode, new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }

    @PostMapping("/per-student/{rollNo}/{startDate}/{endDate}")
    public List<Map<String, Map<String, Boolean>>> getStudentAttendanceStat(@PathVariable  String rollNo, @PathVariable String startDate, @PathVariable String endDate){
        return attendanceService.getAttendancePerStudentAtRange(rollNo, new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }

    @PostMapping("/per-class/{staffId}/{startDate}/{endDate}")
    public List<List<Map<String, Map<String, Boolean>>>> getStudentAttendanceStatClass(@PathVariable  String staffId,@PathVariable String startDate,@PathVariable String endDate) throws TeacherNotFoundException {
        return attendanceService.getAttendancePerClassAtRange(teacherService.findById(staffId).getClassCode(), new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }

    @PostMapping("/per-class-by-class-code/{classCode}/{startDate}/{endDate}")
    public List<List<Map<String, Map<String, Boolean>>>> getStudentAttendanceStatClassByClassCode(@PathVariable  String classCode,@PathVariable String startDate,@PathVariable String endDate) {
        return attendanceService.getAttendancePerClassAtRange(classCode, new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }

    @PostMapping("/all/{date}")
    public List<Map<String, Map<String, Integer>>> getAttendanceStatForAllClass(@PathVariable String date){
        return attendanceService.getAttendancePerClass(date);
    }
    @PostMapping("get-period")
    public int getPeriod(){
        return TimeTableHoursService.getPeriodNumber(LocalTime.now());
    }
}
