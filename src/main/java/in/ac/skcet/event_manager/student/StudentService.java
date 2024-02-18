package in.ac.skcet.event_manager.student;

import in.ac.skcet.event_manager.class_code.ClassCodeService;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StudentService {

    StudentMongoRepository studentRepository;
    ClassCodeService classCodeService;

    @Transactional
    public Student save(Student student){
        return studentRepository.save(student);
    }

    public Student findByID(String id) throws StudentNotFoundException {
        return studentRepository.findByRollNo(id);
    }

    public List<Student> findByClassCode(String classCode){
        return studentRepository.findAllByClassCode(classCode);
    }

    public void updateOd(Student student){
        student.setOnDuty(true);
        studentRepository.insert(student);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }
}
