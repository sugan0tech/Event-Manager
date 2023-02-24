package in.ac.skcet.event_manager.event;

import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentStat;
import in.ac.skcet.event_manager.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EventStatService {

    EventService eventService;
    StudentService studentService;

    @Transactional
    public Map<String, Integer> getEventStat(Integer eventId, String classCode){

        Event  event = eventService.findById(eventId).orElse(new Event());
        List<Student> students = new ArrayList<>(studentService.findByClassCode(classCode));
        Map<String, Integer> result = new HashMap<>();

        result.put("total", students.size());
        result.put("pending", (int) students.stream().filter(student -> !student.getEvents().contains(event)).count());
        result.put("completed", result.get("total") - result.get("pending"));

        log.info(result.toString());
        return result;
    }

    public List<StudentStat> getStudentStatusList(Integer eventId, String classCode){

        Event  event = eventService.findById(eventId).orElse(new Event());
        List<Student> students = new ArrayList<>(studentService.findByClassCode(classCode));
        List<StudentStat> studentStats = new ArrayList<>();
        students.forEach(
                student -> {
                    if(student.getEvents().contains(event)){
                        studentStats.add(new StudentStat(student, true));
                    }else
                        studentStats.add(new StudentStat(student, false));
                }
        );
        return studentStats;
    }
}
