package in.ac.skcet.event_manager.student;

import in.ac.skcet.event_manager.class_code.ClassCodeService;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.LazyLoader;
import org.springframework.data.mongodb.core.convert.LazyLoadingProxy;
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

    public void updateOd(String rollNo){
        var student = studentRepository.findByRollNo(rollNo);
        student.setOnDuty(true);
        // todo to be implemented after DBref used for junk files
//        log.info(student.toString());
//        if (student.getAttendancePeriodSet() instanceof LazyLoadingProxy) {
//            LazyLoadingProxy lazyLoadingProxy = (LazyLoadingProxy) student.getAttendancePeriodSet();
//            lazyLoadingProxy.getSource(); // Load the proxy
//            log.info(lazyLoadingProxy.getTarget().toString());
//        }

        studentRepository.save(student);
    }

    public void cancelOd(String rollNo){
        var student = studentRepository.findByRollNo(rollNo);
        student.setOnDuty(false);
        studentRepository.save(student);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public String getClassCode(String rollNo){
        return studentRepository.findByRollNo(rollNo).getClassCode();
    }
}
