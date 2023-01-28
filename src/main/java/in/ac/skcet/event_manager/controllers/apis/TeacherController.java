package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.StudentStat;
import in.ac.skcet.event_manager.services.EventStatService;
import in.ac.skcet.event_manager.services.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
@Slf4j
public class TeacherController {

    TeacherService teacherService;
    EventStatService eventStatService;

    @PostMapping("/events/pending/{staffId}")
    public List<Event> getEvents(@PathVariable String staffId){

       log.info(staffId);
       log.info(teacherService.findById("ramesh").toString());
       return teacherService.findEvents(staffId);
    }

    @PostMapping("/events/past-five")
    public List<Event> getPastFiveEvents(){
        return new ArrayList<>();
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
}
