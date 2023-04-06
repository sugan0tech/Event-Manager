package in.ac.skcet.event_manager.controllers;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.class_code.ClassCodeService;
import in.ac.skcet.event_manager.event.*;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.firebase_notification.PushNotificationService;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.student.StudentStat;
import in.ac.skcet.event_manager.teacher.StaffEventTimer;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/events")
public class EventController {
    EventService eventService;
    StudentService studentService;
    EventStatService eventStatService;
    ClassCodeService classCodeService;
    TeacherService teacherService;
    PushNotificationService pushNotificationService;
    StaffEventTimer staffEventTimer;
    EventCmdToEvent eventCmdToEvent;

    @PostMapping("/pending/{staffId}")
    public List<Event> getEvents(@PathVariable String staffId) throws TeacherNotFoundException {
        log.info(staffId);
        log.info(teacherService.findById(staffId).toString());
        return teacherService.findEvents(staffId);
    }

    @PostMapping("/pending-by-class-code/{classCode}")
    public List<Event> getEventsByClassCode(@PathVariable String classCode){
        return eventService.findByClassCode(classCode);
    }

    @PostMapping("/past-five/{staffId}")
    public List<Event> getPastFiveEvents(@PathVariable String staffId) throws TeacherNotFoundException {
        String classCode = teacherService.findById(staffId).getClassCode();
        return eventService.getPastFiveEvents(classCode);
    }

    @PostMapping("/past-five-by-class-code/{classCode}")
    public List<Event> getPastFiveEventsByClassCode(@PathVariable String classCode) {
        return eventService.getPastFiveEvents(classCode);
    }

    @PostMapping("/stats/{eventId}/{staffId}")
    public Map<String, Integer> getEventStatus(@PathVariable Integer eventId, @PathVariable String staffId) throws TeacherNotFoundException {
        String staffClassCode = teacherService.findById(staffId).getClassCode();
        String eventClassCode = eventService.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found")).getClassCode();

        if(staffClassCode.length() > eventClassCode.length()){
            return eventStatService.getEventStat(eventId, staffClassCode);
        }
        return eventStatService.getEventStat(eventId, eventClassCode);
    }
    @PostMapping("/stats-by-class-code/{eventId}/{classCode}")
    public Map<String, Integer> getEventStatusByClassCode(@PathVariable Integer eventId, @PathVariable String classCode) {
        return eventStatService.getEventStat(eventId, classCode);
    }

    @PostMapping("/stats-list/{eventId}/{staffId}")
    public List<StudentStat> getStudentWithStats(@PathVariable Integer eventId, @PathVariable  String staffId) throws TeacherNotFoundException {
        String staffClassCode = teacherService.findById(staffId).getClassCode();
        String eventClassCode = eventService.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found")).getClassCode();

        if(staffClassCode.length() > eventClassCode.length()){
            return eventStatService.getStudentStatusList(eventId, staffClassCode);
        }
        return eventStatService.getStudentStatusList(eventId, eventClassCode);
    }

    @PostMapping("/stats-list-by-class-code/{eventId}/{classCode}")
    public List<StudentStat> getStudentWithStatsByClassCode(@PathVariable Integer eventId, @PathVariable  String classCode){
        return eventStatService.getStudentStatusList(eventId, classCode);
    }

    @PostMapping("/new")
    public String createEvent(@ModelAttribute EventCommand eventCommand) throws FirebaseMessagingException {
        Event event = eventCmdToEvent.convert(eventCommand);
        if(event == null) {
            log.info("Invalid Event!");
            return "invalid";
        }
        else {
            log.info(eventService.save(event).toString());
            log.info(event.getClassCode());
            pushNotificationService.eventNotification(event);
            staffEventTimer.setTimerForEvent(event);
        }

        return "added";
    }

}
