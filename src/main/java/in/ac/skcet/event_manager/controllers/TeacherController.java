package in.ac.skcet.event_manager.controllers;

import in.ac.skcet.event_manager.attendance.AttendanceService;
import in.ac.skcet.event_manager.event.*;
import in.ac.skcet.event_manager.attendance.AttendanceRepository;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.firebase_notification.PushNotificationService;
import in.ac.skcet.event_manager.on_duty.OnDutyFormService;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.teacher.StaffEventTimer;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/get-class-code/{staffId}")
    public String getClassCode(@PathVariable String staffId) throws TeacherNotFoundException {
        return teacherService.findById(staffId).getClassCode();
    }


    @PostMapping("/student-list/{staffId}")
    public Map<String, String> getList(@PathVariable String staffId) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        Map<String, String> studentList = new TreeMap<>();
        studentService.findByClassCode(classCode).forEach(student ->
                studentList.put(student.getRollNo(), student.getName())
        );
        return studentList;
    }

    @PostMapping("/student-list-by-class-code/{classCode}")
    public Map<String, String> getListByClassCode(@PathVariable String classCode){
        Map<String, String> studentList = new TreeMap<>();
        studentService.findByClassCode(classCode).forEach(student ->
                studentList.put(student.getRollNo(), student.getName())
        );
        return studentList;
    }



}
