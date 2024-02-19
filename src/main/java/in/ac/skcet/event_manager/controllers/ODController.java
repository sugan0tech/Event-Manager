package in.ac.skcet.event_manager.controllers;

import in.ac.skcet.event_manager.exception.OdFormNotFoundException;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.on_duty.*;
import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/od")
@AllArgsConstructor
public class ODController {

    StudentService studentService;
    OnDutyFormService onDutyFormService;
    TeacherService teacherService;
    OnDutyEndTimer onDutyEndTimer;
    OnDutyFormCommandToOnDutyForm onDutyFormCommandToOnDutyForm;

    @PostMapping("/cancel/{id}")
    public void cancelOd(@PathVariable String id) throws OdFormNotFoundException {
        OnDutyForm onDutyForm = onDutyFormService.findById(id);
        onDutyForm.getStudentSet().forEach(studentId -> {
            studentService.cancelOd(studentId);
        });
        onDutyFormService.delete(id);
    }

    @PostMapping("student/addOd/{studentId}")
    public void updateOdForm(@ModelAttribute OnDutyFormCommand onDutyFormCommand) {
        log.info(onDutyFormCommand.toString());
        onDutyEndTimer.autoEndOdTimer(onDutyFormService.save(onDutyFormCommandToOnDutyForm.convert(onDutyFormCommand)));
    }

    @PostMapping("student/getOd/{studentId}")
    public List<OnDutyForm> getOdForm(@PathVariable  String studentId){
        return onDutyFormService.findAll().stream().filter(onDutyForm ->
                onDutyForm.getStudentSet().contains(studentId)
        ).collect(Collectors.toList());
    }

    @PostMapping("/list/{staffId}")
    public List<OnDutyForm> getOdList(@PathVariable String staffId) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        return onDutyFormService.findByClassCode(classCode);
    }
    @PostMapping("/list-by-class-code/{classCode}")
    public List<OnDutyForm> getOdListByClassCode(@PathVariable String classCode) {
        return onDutyFormService.findByClassCode(classCode);
    }
}
