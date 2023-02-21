package in.ac.skcet.event_manager.repositories;

import in.ac.skcet.event_manager.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    Set<Teacher> findByClassCode(String classCode);
}
