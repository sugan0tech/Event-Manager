package in.ac.skcet.event_manager.attendance;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AttendanceService {
    AttendanceRepository attendanceRepository;

    public List<Attendance> getDates(){
        return attendanceRepository.findAll();
    }
}
