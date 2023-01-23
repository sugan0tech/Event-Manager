package in.ac.skcet.event_manager.repositories;

import in.ac.skcet.event_manager.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, String> {
    Set<Student> findByClassCode(String classCode);
}
