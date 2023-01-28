package in.ac.skcet.event_manager.services;

import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Student;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EventStatService {
    EventService eventService;
    StudentService studentService;
    public Integer getEventStat(Integer eventId, String classCode){
        Event  event = eventService.findById(eventId).orElse(new Event());
        List<Student> students = new ArrayList<>(studentService.findByClassCode(classCode));

        int total = students.size();
        log.info("total " + total);
        int pending = students.stream().filter(student -> !student.getEvents().contains(event)).collect(Collectors.toList()).size();
        log.info("pending " + pending);
        return pending;
    }

}
