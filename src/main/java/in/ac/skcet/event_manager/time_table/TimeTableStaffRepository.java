package in.ac.skcet.event_manager.time_table;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableStaffRepository extends JpaRepository<TimeTableStaff, Integer>{
//    public TimeTableStaff findByStaffId(int id);
}
