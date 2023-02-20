package in.ac.skcet.event_manager.controllers.apis;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.commands.EventCmdToEvent;
import in.ac.skcet.event_manager.commands.EventCommand;
import in.ac.skcet.event_manager.models.Attendance;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Student;
import in.ac.skcet.event_manager.models.StudentStat;
import in.ac.skcet.event_manager.repositories.AttendanceRepository;
import in.ac.skcet.event_manager.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
@Slf4j
public class TeacherController {

    TeacherService teacherService;
    EventService eventService;
    EventStatService eventStatService;
    EventCmdToEvent eventCmdToEvent;
    StudentService studentService;
    AttendanceRepository attendanceRepository;
    PushNotificationService pushNotificationService;


    @PostMapping("/events/pending/{staffId}")
    public List<Event> getEvents(@PathVariable String staffId){

       log.info(staffId);
       log.info(teacherService.findById("srigirit369").toString());
       return teacherService.findEvents(staffId);
    }

    @PostMapping("/events/past-five/{classCode}")
    public List<Event> getPastFiveEvents(@PathVariable String classCode) {
        return eventService.getPastFiveEvents(classCode);
    }


    @PostMapping("/event/stats/{eventId}/{classCode}")
    public Map<String, Integer> getEventStatus(@PathVariable Integer eventId, @PathVariable String classCode){
        return eventStatService.getEventStat(eventId, classCode);
    }

    @PostMapping("/event/stats-list/{eventId}/{classCode}")
    public List<StudentStat> getStudentWithStats(@PathVariable Integer eventId, @PathVariable  String classCode){
        return eventStatService.getStudentStatusList(eventId, classCode);
    }

    @PostMapping("/event/new")
    public void createEvent(@ModelAttribute EventCommand eventCommand) throws FirebaseMessagingException {
        log.info(eventCommand.toString());
        Event event = eventCmdToEvent.convert(eventCommand);
        if(event == null) {
            log.info("Invalid Event!");
        }
        else {
            log.info(eventService.save(event).toString());
            log.info(event.getClassCode());
            pushNotificationService.notifyAll(event.getClassCode());
        }



    }
    @PostMapping("/event/notification/{studentId}")
    public void sendNotification(@PathVariable String studentId){
    }


    @PostMapping("/student/attendance/{studentId}")
    public void addAttendance(@PathVariable String studentId){
        Student student = studentService.findByID(studentId).orElse(new Student());
        Attendance attendance = attendanceRepository.findById(1L).orElse(new Attendance());
        student.addAttendance(attendance);
        studentService.save(student);
    }


}
