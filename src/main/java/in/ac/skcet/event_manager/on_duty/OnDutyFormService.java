package in.ac.skcet.event_manager.on_duty;

import in.ac.skcet.event_manager.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OnDutyFormService {
    OnDutyFormRepository onDutyFormRepository;
    StudentService studentService;

    public Optional<OnDutyForm> findById(Long id){
        return onDutyFormRepository.findById(id);
    }

    public OnDutyForm save(OnDutyForm onDutyForm){
        onDutyForm.getStudentSet().forEach(student -> {
            studentService.updateOd(student);
        });
        return onDutyFormRepository.save(onDutyForm);
    }
}