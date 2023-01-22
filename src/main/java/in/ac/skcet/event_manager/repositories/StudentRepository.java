package in.ac.skcet.event_manager.repositories;

import in.ac.skcet.event_manager.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
