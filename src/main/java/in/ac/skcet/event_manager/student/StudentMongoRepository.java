package in.ac.skcet.event_manager.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentMongoRepository extends MongoRepository<Student, String> {
    Student findByRollNo(String rollNo);
    List<Student> findAllByClassCode(String classCode);
    List<Student> findAll();
}
