package in.ac.skcet.event_manager.time_table;

import org.springframework.stereotype.Service;


@Service
public class TimeTableStaffService {
    private final TimeTableStaffRepository timeTableStaffRepository;

    public TimeTableStaffService(TimeTableStaffRepository timeTableStaffRepository) {
        this.timeTableStaffRepository = timeTableStaffRepository;
    }

    public TimeTableStaff save(TimeTableStaff timeTableStaff){
        return timeTableStaffRepository.save(timeTableStaff);
    }

    public TimeTableStaff findById(int id){
        return timeTableStaffRepository.findById(id).orElse(null);
    }

//    public TimeTableStaff findByStaffId( staffId){
//        return timeTableStaffRepository.findByStaff(staffId);
//    }
}
