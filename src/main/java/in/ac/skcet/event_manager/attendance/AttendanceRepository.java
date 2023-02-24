package in.ac.skcet.event_manager.attendance;

import in.ac.skcet.event_manager.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByDate(Date date);
}
