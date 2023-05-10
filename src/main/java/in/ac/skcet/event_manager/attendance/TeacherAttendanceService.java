package in.ac.skcet.event_manager.attendance;

import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.teacher.Staff;
import in.ac.skcet.event_manager.teacher.Teacher;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherAttendanceService {
    private TeacherService teacherService;

    public Teacher markAttendance(String staffId, boolean status) throws TeacherNotFoundException {
        Teacher teacher = teacherService.findById(staffId);
        teacher.setPresent(status);
        return teacherService.save(teacher);
    }

    public List<Teacher> getAttendanceList(){
        return teacherService.findAll().stream().filter(Staff::isPresent).collect(Collectors.toList());
    }
}
