package in.ac.skcet.event_manager.time_table;

import in.ac.skcet.event_manager.class_code.ClassCodeService;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.teacher.Teacher;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class TimeTableStaffService {
    private final TimeTableStaffRepository timeTableStaffRepository;
    private final ClassCodeService classCodeService;
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

    public List<String> freeList(String classCode, int period){
        List<String> freeStaff = new ArrayList<>();
        if(period > 6 || period < 0){
            return freeStaff;
        }
        timeTableStaffRepository.findAll().stream().forEach(timeTableStaff -> {
            Teacher teacher = (Teacher) timeTableStaff.getStaff();
            if(classCodeService.compareCodes(teacher.getClassCode(), classCode) && timeTableStaff.getDayOne().get(period).equals("Free")){
                freeStaff.add(timeTableStaff.getStaff().getStaffId());
            }
        });
        return freeStaff;
    }
}
