package in.ac.skcet.event_manager.student;

import in.ac.skcet.event_manager.class_code.ClassCodeService;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class StudentService {

    StudentRepository studentRepository;
    ClassCodeService classCodeService;

    @Transactional
    public Student save(Student student){
        return studentRepository.save(student);
    }

    public Student findByID(String id) throws StudentNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student Not found id :" + id));
    }

    public List<Student> findByClassCode(String classCode){
        return studentRepository.findAll().stream().filter(student -> classCodeService.compareCodes(student.getClassCode(), classCode)).collect(Collectors.toList());
    }

    public void updateOd(Student student){
        student.setOnDuty(true);
        studentRepository.save(student);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }
}
