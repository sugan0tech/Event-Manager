package in.ac.skcet.event_manager.controllers;

import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.attendance.AttendanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/attendance")
public class AttendanceController {

    AttendanceService attendanceService;

    @PostMapping("/get-dates")
    List<Attendance> getDates(){
        return attendanceService.getDates();
    }
}
