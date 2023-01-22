package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Student;
import in.ac.skcet.event_manager.repositories.EventRepository;
import in.ac.skcet.event_manager.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/event")
public class EventCRUDController {
    EventRepository eventRepository;
    StudentRepository studentRepository;


    @PostMapping("/get/{studentId}")
    public List<Event> getEvents(@PathVariable String studentId){
        Student stu = studentRepository.findById(studentId).orElse(new Student());
        log.info(stu.toString());
        return eventRepository.findAll();
    }

    @PostMapping("/update/{studentId}/{eventId}")
    public void updateEventStatus(@PathVariable String studentId, @PathVariable String eventId){
        log.info(studentId);
        log.info(eventId);
        Event event = eventRepository.findById(Integer.valueOf(eventId)).orElse(new Event());
        Student stu = studentRepository.findById(studentId).orElse(new Student());
        log.info(event.toString());
        stu.addEvent(event);
        studentRepository.save(stu);
    }
}
