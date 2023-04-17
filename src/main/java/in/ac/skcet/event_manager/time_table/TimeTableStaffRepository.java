package in.ac.skcet.event_manager.time_table;

import in.ac.skcet.event_manager.teacher.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableStaffRepository extends JpaRepository<TimeTableStaff, Integer>{
    public TimeTableStaff findByStaff(Staff staff);
}
