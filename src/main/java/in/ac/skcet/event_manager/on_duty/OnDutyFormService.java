package in.ac.skcet.event_manager.on_duty;

import in.ac.skcet.event_manager.class_code.ClassCodeService;
import in.ac.skcet.event_manager.exception.OdFormNotFoundException;
import in.ac.skcet.event_manager.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OnDutyFormService {
    OnDutyFormRepository onDutyFormRepository;
    StudentService studentService;
    ClassCodeService classCodeService;

    public List<OnDutyForm> findAll(){
        return onDutyFormRepository.findAll();
    }

    public OnDutyForm findById(String id) throws OdFormNotFoundException {
        return onDutyFormRepository.findById(id).orElseThrow(() -> new OdFormNotFoundException("Expected OdForm not found id:" + id));
    }

    public OnDutyForm save(OnDutyForm onDutyForm){
        log.info(onDutyForm.toString());
        onDutyForm.getStudentSet().forEach(studentId -> studentService.updateOd(studentId));
        return onDutyFormRepository.save(onDutyForm);
    }

    public void cancel(OnDutyForm onDutyForm, String staffId){
        onDutyForm.getStudentSet().forEach(studentId -> studentService.cancelOd(studentId, onDutyForm.getId()));
        onDutyForm.setCanceledBy(staffId);
        onDutyFormRepository.save(onDutyForm);
    }

    public List<OnDutyForm> findByClassCode(String classCode){
        return onDutyFormRepository.findAll().stream().filter(onDutyForm -> onDutyForm.getStudentSet().stream().anyMatch(studentId -> classCodeService.compareCodes(studentService.getClassCode(studentId), classCode))).collect(Collectors.toList());
    }
    public void delete(String id){
        onDutyFormRepository.findById(id).ifPresentOrElse(onDutyForm -> onDutyForm.getStudentSet().forEach(studentId -> studentService.cancelOd(studentId, onDutyForm.getId())), () -> log.warn("Od form not found at id:" + id));
        onDutyFormRepository.deleteById(id);
    }
}