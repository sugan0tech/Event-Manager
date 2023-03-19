package in.ac.skcet.event_manager.controllers;

import in.ac.skcet.event_manager.event.EventService;
import in.ac.skcet.event_manager.exception.EventNotFoundException;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.on_duty.*;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.event.Event;
import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
    TeacherService teacherService;

    @PostMapping("/get/{studentId}")
    public List<Event> getEvents(@PathVariable String studentId) throws StudentNotFoundException {
        return eventService.getPendingEvents(studentId);
    }

    @PostMapping("/getClassCode/{rollNo}")
    public String getClassCode(@PathVariable String rollNo) throws StudentNotFoundException {
        return studentService.findByID(rollNo).getClassCode();
    }

    @PostMapping("/student/getList/{staffId}")
    public Map<String, String> getList(@PathVariable String staffId) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        Map<String, String> studentList = new TreeMap<>();
        studentService.findByClassCode(classCode).forEach(student ->
                studentList.put(student.getRollNo(), student.getName())
        );
        return studentList;
    }

    @PostMapping("/student/get-list-by-class-code/{classCode}")
    public Map<String, String> getListByClassCode(@PathVariable String classCode){
        Map<String, String> studentList = new TreeMap<>();
        studentService.findByClassCode(classCode).forEach(student ->
                studentList.put(student.getRollNo(), student.getName())
        );
        return studentList;
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
