package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.on_duty.OnDutyEndTimer;
import in.ac.skcet.event_manager.on_duty.OnDutyFormCommand;
import in.ac.skcet.event_manager.on_duty.OnDutyFormCommandToOnDutyForm;
import in.ac.skcet.event_manager.exception.studentnotfoundexception;
import in.ac.skcet.event_manager.event.Event;
import in.ac.skcet.event_manager.on_duty.OnDutyFormService;
import in.ac.skcet.event_manager.student.StudentService;
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
    OnDutyEndTimer onDutyEndTimer;

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
        onDutyEndTimer.autoEndOdTimer(onDutyFormService.save(onDutyFormCommandToOnDutyForm.convert(onDutyFormCommand)));
    }

}
