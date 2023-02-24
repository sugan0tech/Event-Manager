package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.commands.OnDutyFormCommand;
import in.ac.skcet.event_manager.commands.OnDutyFormCommandToOnDutyForm;
import in.ac.skcet.event_manager.exception.studentnotfoundexception;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.services.OnDutyFormService;
import in.ac.skcet.event_manager.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
@Slf4j
public class StudentController {

    StudentService studentService;
    OnDutyFormCommandToOnDutyForm onDutyFormCommandToOnDutyForm;
    OnDutyFormService onDutyFormService;

    @PostMapping("/get/{studentId}")
    public List<Event> getEvents(@PathVariable String studentId) throws studentnotfoundexception {
        return studentService.getPendingEvents(studentId);
    }

    @PostMapping("/update/{studentId}/{eventId}")
    public void updateEventStatus(@PathVariable String studentId, @PathVariable String eventId){
        studentService.updateEvent(studentId, eventId);
    }

    @PostMapping("/addOd/{studentId}")
    public void updateOdForm(@ModelAttribute OnDutyFormCommand onDutyFormCommand) throws studentnotfoundexception {
        log.info(onDutyFormCommand.toString());
        onDutyFormService.save(onDutyFormCommandToOnDutyForm.convert(onDutyFormCommand));
    }

}
