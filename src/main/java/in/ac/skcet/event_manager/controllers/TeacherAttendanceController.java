package in.ac.skcet.event_manager.controllers;

import in.ac.skcet.event_manager.attendance.TeacherAttendanceService;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.teacher.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff/attendance")
@AllArgsConstructor
public class TeacherAttendanceController {
    private TeacherAttendanceService teacherAttendanceService;

    @PostMapping("/put/{staffId}/{status}")
    public Teacher markAttendance(@PathVariable String staffId, @PathVariable boolean status) throws TeacherNotFoundException {
        return teacherAttendanceService.markAttendance(staffId, status);
    }
    @PostMapping("/get/list")
    public List<Teacher> getAttendanceList(){
        return teacherAttendanceService.getAttendanceList();
    }
}
