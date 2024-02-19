package in.ac.skcet.event_manager.time_table;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timetable")
@AllArgsConstructor
public class TimeTableController {
    TimeTableService timeTableService;

    @PostMapping("/new")
    public TimeTable newTimeTable(@RequestBody TimeTable timeTable){
        return timeTableService.save(timeTable);
    }

    @PostMapping("/get/{classCode}")
    public TimeTable newTimeTable(@RequestParam String classCode){
        return timeTableService.findByClassCode(classCode);
    }
}
