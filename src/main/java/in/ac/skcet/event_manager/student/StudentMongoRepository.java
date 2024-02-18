package in.ac.skcet.event_manager.student;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentMongoRepository extends MongoRepository<StudentMongo, String> {
    StudentMongo findByRollNo(String rollNo);
    List<StudentMongo> findAllByClassCode(String classCode);
    List<StudentMongo> findAll();
}
