package in.ac.skcet.event_manager.time_table;

import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TimeTableStaffService {
    private final TimeTableStaffRepository timeTableStaffRepository;
    private final TeacherService teacherService;


    public TimeTableStaff save(TimeTableStaff timeTableStaff) throws TeacherNotFoundException {
        TimeTableStaff tmp = findByStaff(timeTableStaff.getStaff().getStaffId());
        if(tmp != null){
            tmp.setDayOne(timeTableStaff.getDayOne());
            tmp.setDayTwo(timeTableStaff.getDayTwo());
            tmp.setDayThree(timeTableStaff.getDayThree());
            tmp.setDayFour(timeTableStaff.getDayFour());
            tmp.setDayFive(timeTableStaff.getDayFive());
            return timeTableStaffRepository.save(tmp);
        }
        return timeTableStaffRepository.save(timeTableStaff);
    }

    public TimeTableStaff findById(int id){
        return timeTableStaffRepository.findById(id).orElse(null);
    }

    public TimeTableStaff findByStaff(String staffId) throws TeacherNotFoundException {
        return timeTableStaffRepository.findByStaff(teacherService.findById(staffId));
    }
}
