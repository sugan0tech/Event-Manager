package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.commands.EventCmdToEvent;
import in.ac.skcet.event_manager.commands.EventCommand;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.services.EventService;
import in.ac.skcet.event_manager.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/event")
public class EventCRUDController {

    EventService eventService;
    StudentService studentService;
    EventCmdToEvent eventCmdToEvent;

    @PostMapping("/get/{studentId}")
    public List<Event> getEvents(@PathVariable String studentId){
        return studentService.getPendingEvents(studentId);
    }

    @PostMapping("/new")
    public void createEvent(@ModelAttribute EventCommand eventCommand){
           log.info(eventService.save(eventCmdToEvent.convert(eventCommand)).toString());
    }

    @PostMapping("/update/{studentId}/{eventId}")
    public void updateEventStatus(@PathVariable String studentId, @PathVariable String eventId){

        studentService.updateEvent(studentId, eventId);
    }
}
