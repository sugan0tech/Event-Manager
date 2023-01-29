package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.commands.EventCmdToEvent;
import in.ac.skcet.event_manager.commands.EventCommand;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.StudentStat;
import in.ac.skcet.event_manager.services.EventService;
import in.ac.skcet.event_manager.services.EventStatService;
import in.ac.skcet.event_manager.services.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
@Slf4j
public class TeacherController {

    TeacherService teacherService;
    EventService eventService;
    EventStatService eventStatService;
    EventCmdToEvent eventCmdToEvent;

    @PostMapping("/events/pending/{staffId}")
    public List<Event> getEvents(@PathVariable String staffId){

       log.info(staffId);
       log.info(teacherService.findById("ramesh").toString());
       return teacherService.findEvents(staffId);
    }

    @PostMapping("/events/past-five/{classCode}")
    public List<Event> getPastFiveEvents(@PathVariable String classCode) throws ParseException {
        return eventService.getPastFiveEvents(classCode);
    }


    @PostMapping("/event/stats/{eventId}/{classCode}")
    public Integer getEventStatus(@PathVariable Integer eventId, @PathVariable String classCode){
        log.info(eventId.toString());
        log.info(classCode);
        return eventStatService.getEventStat(eventId, classCode);
    }

    @PostMapping("/event/stats-list/{eventId}/{classCode}")
    public List<StudentStat> getStudentWithStats(@PathVariable Integer eventId, @PathVariable  String classCode){
        return eventStatService.getStudentStatusList(eventId, classCode);
    }

    @PostMapping("/event/new")
    public void createEvent(@ModelAttribute EventCommand eventCommand){
        log.info(eventService.save(eventCmdToEvent.convert(eventCommand)).toString());
    }
}
