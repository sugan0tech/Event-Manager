package in.ac.skcet.event_manager.attendance;

import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttendanceStaffService {
    TeacherService teacherService;
    AttendanceRepository attendanceRepository;

}
