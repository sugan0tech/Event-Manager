package in.ac.skcet.event_manager.attendance;

import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceService {
    AttendanceRepository attendanceRepository;
    StudentService studentService;

    public Map<String, Double> getAttendancePercentage(String classCode, Date startDate, Date endDate){
        Map<String, Double> studentPercentage = new TreeMap<>();
        long totalDays =countDays(startDate, endDate);
        log.info("total days " + totalDays);
        studentService.findByClassCode(classCode).forEach(student -> studentPercentage.put(student.getRollNo(), ((double)noOfDaysPresent(student, startDate, endDate)/totalDays)*100));
        return studentPercentage;
    }

    public Long noOfDaysPresent(Student student, Date startDate, Date endDate){
        return student.getAttendanceSet().stream().filter(attendance -> attendance.getDate().after(startDate) && attendance.getDate().before(endDate)).count();
    }

    public Long countDays(Date startDate, Date endDate){
        return attendanceRepository.findAll().stream().filter(attendance ->
                attendance.getDate().after(startDate) && attendance.getDate().before(endDate)).count();
    }
}
