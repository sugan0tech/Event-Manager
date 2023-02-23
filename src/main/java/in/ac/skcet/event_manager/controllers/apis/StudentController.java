package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.exception.studentnotfoundexception;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    StudentService studentService;

    @PostMapping("/get/{studentId}")
    public List<Event> getEvents(@PathVariable String studentId) throws studentnotfoundexception {
        return studentService.getPendingEvents(studentId);
    }

    @PostMapping("/update/{studentId}/{eventId}")
    public void updateEventStatus(@PathVariable String studentId, @PathVariable String eventId){
        studentService.updateEvent(studentId, eventId);
    }

}
