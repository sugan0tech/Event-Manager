package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.commands.EventCmdToEvent;
import in.ac.skcet.event_manager.commands.EventCommand;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Student;
import in.ac.skcet.event_manager.repositories.EventRepository;
import in.ac.skcet.event_manager.repositories.StudentRepository;
import in.ac.skcet.event_manager.services.EventService;
import in.ac.skcet.event_manager.services.StudentService;
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
    EventService eventService;
    StudentService studentService;
    @PostMapping("/get/{studentId}")
    public List<Event> getEvents(@PathVariable String studentId){
        return studentService.findById(studentId);
    }

    @PostMapping("/new")
    public void createEvent(@ModelAttribute EventCommand eventCommand){
           log.info(eventService.save(eventCommand).toString());

    }

    @PostMapping("/update/{studentId}/{eventId}")
    public void updateEventStatus(@PathVariable String studentId, @PathVariable String eventId){
        studentService.save(studentId, eventId);
    }
}
