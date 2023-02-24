package in.ac.skcet.event_manager.controllers.apis;

import in.ac.skcet.event_manager.event.Event;
import in.ac.skcet.event_manager.student.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/apis")
@Slf4j
public class TestApi {
    @PostMapping({"/{id}"})
    public String testApi(@PathVariable String id){
        log.info(id);
        return id;
    }
    @PostMapping("/event/{studentId}")
    public Event getSampleEvent(@PathVariable String studentId){
        return Event.builder()
                .eventId(1)
                .description("HourOfPlacement")
                .fromDate(new Date())
                .endDate(new Date())
                .build();
    }

    @PostMapping("/stu")
    public List<Student> getStu(){
        List<Student> lst = new ArrayList<>();
        Student studentOne = Student.builder().rollNo("20eucs147")
                .name("sugan")
                .classCode("III CSE C")
                .dateOfBirth(java.sql.Date.valueOf("2002-12-16"))
                .mobile("1234567890")
                .mail("20eucs147@skcet.ac.in").build();
        Student studentTwo = Student.builder().rollNo("20eucs148")
                .name("sujith")
                .classCode("III CSE C")
                .dateOfBirth(java.sql.Date.valueOf("2002-12-16"))
                .mobile("1234567890")
                .mail("20eucs148@skcet.ac.in").build();

        lst.add(studentOne);
        lst.add(studentTwo);
        return lst;
    }
}
