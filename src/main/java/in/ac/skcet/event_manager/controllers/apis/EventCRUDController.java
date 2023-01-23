package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.commands.EventCmdToEvent;
import in.ac.skcet.event_manager.commands.EventCommand;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Student;
import in.ac.skcet.event_manager.repositories.EventRepository;
import in.ac.skcet.event_manager.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/event")
public class EventCRUDController {
    EventRepository eventRepository;
    StudentRepository studentRepository;

    @PostMapping("/get/{studentId}")
    public List<Event> getEvents(@PathVariable String studentId){
        Student stu = studentRepository.findById(studentId).orElse(null);

        if(stu == null)
            return new ArrayList<>();

        log.info(stu.toString());
        return eventRepository.findAll().stream().filter(event -> !stu.getEvents().contains(event)).collect(Collectors.toList());
    }

    @PostMapping("/new")
    public void createEvent(@ModelAttribute EventCommand eventCommand){
        EventCmdToEvent eventCmdToEvent = new EventCmdToEvent();
        Event event = eventCmdToEvent.convert(eventCommand);
        log.info(eventRepository.save(event).toString());
    }

    @PostMapping("/update/{studentId}/{eventId}")
    public void updateEventStatus(@PathVariable String studentId, @PathVariable String eventId){
        log.info(studentId);
        log.info(eventId);
        Event event = eventRepository.findById(Integer.valueOf(eventId)).orElse(null);
        Student stu = studentRepository.findById(studentId).orElse(null);
        if(stu == null || event == null)
            return;

        log.info(event.toString());
        stu.addEvent(event);
        studentRepository.save(stu);
    }
}
