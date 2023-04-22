package in.ac.skcet.event_manager.attendance;

import in.ac.skcet.event_manager.teacher.TeacherService;
import in.ac.skcet.event_manager.time_table.TimeTableStaffService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherAttendanceService {
    private TeacherService teacherService;
    private TimeTableStaffService timeTableStaffService;
}
