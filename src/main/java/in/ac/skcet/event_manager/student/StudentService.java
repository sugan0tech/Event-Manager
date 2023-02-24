package in.ac.skcet.event_manager.student;

import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.event.Event;
import in.ac.skcet.event_manager.attendance.AttendanceRepository;
import in.ac.skcet.event_manager.event.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class StudentService {
    private final AttendanceRepository attendanceRepository;

    StudentRepository studentRepository;
    EventService eventService;

    @Transactional
    public Student save(Student student){
        return studentRepository.save(student);
    }

    public Student findByID(String id) throws StudentNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student Not found id :" + id));
    }

    public List<Event> getPendingEvents(String studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("student not found"));
        log.info(student.toString());
        return eventService.findAll().stream().filter(event -> !student.getEvents().contains(event)).collect(Collectors.toList());
    }


    public void updateEvent(String studentId, String eventId){

        Event event = eventService.findById(Integer.valueOf(eventId)).orElse(null);
        Student stu = studentRepository.findById(studentId).orElse(null);
        if(stu == null || event == null)
            return;

        log.info(event.toString());
        stu.addEvent(event);
        studentRepository.save(stu);
    }

    public Set<Student> findByClassCode(String classCode){
        return studentRepository.findByClassCode(classCode);
    }

    public Boolean isPresent(String rollNo, String date){
        return studentRepository.findById(rollNo).orElse(new Student()).getAttendanceSet().contains(attendanceRepository.findByDate(Date.valueOf(date)).orElse(new Attendance()));
    }

    public void updateOd(Student student){
        student.setOnDuty(true);
        studentRepository.save(student);
    }
}
