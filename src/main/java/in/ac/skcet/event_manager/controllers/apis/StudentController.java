package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.event.EventService;
import in.ac.skcet.event_manager.exception.EventNotFoundException;
import in.ac.skcet.event_manager.on_duty.*;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.event.Event;
import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
@Slf4j
public class StudentController {

    StudentService studentService;
    OnDutyFormCommandToOnDutyForm onDutyFormCommandToOnDutyForm;
    OnDutyFormService onDutyFormService;
    OnDutyEndTimer onDutyEndTimer;
    EventService eventService;

    @PostMapping("/get/{studentId}")
    public List<Event> getEvents(@PathVariable String studentId) throws StudentNotFoundException {
        return eventService.getPendingEvents(studentId);
    }

    @PostMapping("/getClassCode/{rollNo}")
    public String getClassCode(@PathVariable String rollNo) throws StudentNotFoundException {
        return studentService.findByID(rollNo).getClassCode();
    }

    @PostMapping("/update/{studentId}/{eventId}")
    public void updateEventStatus(@PathVariable String studentId, @PathVariable String eventId) throws StudentNotFoundException, EventNotFoundException {
        eventService.updateEvent(studentId, eventId);
    }

    @PostMapping("/addOd/{studentId}")
    public void updateOdForm(@ModelAttribute OnDutyFormCommand onDutyFormCommand) {
        log.info(onDutyFormCommand.toString());
        onDutyEndTimer.autoEndOdTimer(onDutyFormService.save(onDutyFormCommandToOnDutyForm.convert(onDutyFormCommand)));
    }

    @PostMapping("/getOd/{studentId}")
    public List<OnDutyForm> getOdForm(@PathVariable  String studentId) throws StudentNotFoundException {
        Student student = studentService.findByID(studentId);
        return onDutyFormService.findAll().stream().filter(onDutyForm ->
            onDutyForm.getStudentSet().contains(student)
        ).collect(Collectors.toList());
    }

}
