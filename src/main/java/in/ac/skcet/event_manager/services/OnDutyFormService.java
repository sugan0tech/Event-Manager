package in.ac.skcet.event_manager.services;

import in.ac.skcet.event_manager.models.OnDutyForm;
import in.ac.skcet.event_manager.repositories.OnDutyFormRepository;
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