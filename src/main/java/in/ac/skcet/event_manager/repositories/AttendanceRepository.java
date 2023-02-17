package in.ac.skcet.event_manager.repositories;

import in.ac.skcet.event_manager.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
