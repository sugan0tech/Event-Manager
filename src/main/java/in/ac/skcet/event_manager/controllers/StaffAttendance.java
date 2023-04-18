package in.ac.skcet.event_manager.controllers;

import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.teacher.Teacher;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/staff/attendance")
@AllArgsConstructor
public class StaffAttendance {
    private TeacherService teacherService;

    @PostMapping("/put/{staffId}/{status}")
    public Teacher markAttandance(@PathVariable String staffId, @PathVariable boolean status) throws TeacherNotFoundException {
        Teacher teacher = teacherService.findById(staffId);
        teacher.setPresent(status);
        return teacherService.save(teacher);
    }
    @PostMapping("/get/list")
    public List<Teacher> getAttendanceList(){
        return teacherService.findAll().stream().filter(teacher -> teacher.isPresent()).collect(Collectors.toList());
    }
}
