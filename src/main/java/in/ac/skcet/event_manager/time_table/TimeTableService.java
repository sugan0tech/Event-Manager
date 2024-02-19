package in.ac.skcet.event_manager.time_table;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TimeTableService {
    TimeTableRepository timeTableRepository;

    public TimeTable save(TimeTable timeTable){
        return timeTableRepository.save(timeTable);
    }

    public TimeTable findByClassCode(String ClassCode){
        return timeTableRepository.findById(ClassCode).orElse(new TimeTable());
    }

    public List<TimeTable> findAll(){
        return timeTableRepository.findAll();
    }
}
