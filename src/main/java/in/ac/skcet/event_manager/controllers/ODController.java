package in.ac.skcet.event_manager.controllers;

import in.ac.skcet.event_manager.exception.OdFormNotFoundException;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.on_duty.OnDutyForm;
import in.ac.skcet.event_manager.on_duty.OnDutyFormService;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/od")
@AllArgsConstructor
public class ODController {

    StudentService studentService;
    OnDutyFormService onDutyFormService;
    TeacherService teacherService;

    @PostMapping("/cancel/{id}")
    public void cancelOd(@PathVariable Long id) throws OdFormNotFoundException {
        OnDutyForm onDutyForm = onDutyFormService.findById(id);
        onDutyForm.getStudentSet().forEach(student -> {
            student.setOnDuty(false);
            studentService.save(student);
        });
        onDutyFormService.delete(id);
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
