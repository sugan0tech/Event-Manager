package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.services.TeacherService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    TeacherService teacherService;

    @PostMapping("/events/pending/{staffId}")
    public List<Event> getEvents(@PathVariable String staffId){
        return teacherService.findEvents(staffId);
    }

    @PostMapping("/events/past")
    public List<Event> getPastFiveEvents(){
        return new ArrayList<>();
    }

}
