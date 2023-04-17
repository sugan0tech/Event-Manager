package in.ac.skcet.event_manager.controllers;

import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.time_table.TimeTableStaff;
import in.ac.skcet.event_manager.time_table.TimeTableStaffCmdToTimeTableStaff;
import in.ac.skcet.event_manager.time_table.TimeTableStaffCommand;
import in.ac.skcet.event_manager.time_table.TimeTableStaffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("staff/timetable")
public class StaffTimeTableController {
    private TimeTableStaffService timeTableStaffService;
    private TimeTableStaffCmdToTimeTableStaff timeTableConverter;

    @PostMapping("/get/{staffId}")
    public TimeTableStaff getStaffTimeTableByStaff(@PathVariable String staffId) throws TeacherNotFoundException {
        return timeTableStaffService.findByStaff(staffId);
    }

    @PostMapping("/new")
    public TimeTableStaff addStaffTimeTableByStaff(@ModelAttribute TimeTableStaffCommand timeTableStaffCommand) throws TeacherNotFoundException {
        return timeTableStaffService.save(timeTableConverter.convert(timeTableStaffCommand));
    }
}
