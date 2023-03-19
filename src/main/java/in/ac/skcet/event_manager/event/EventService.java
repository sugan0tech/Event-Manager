package in.ac.skcet.event_manager.event;

import in.ac.skcet.event_manager.class_code.ClassCodeService;
import in.ac.skcet.event_manager.exception.EventNotFoundException;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
@Slf4j
@Service
public class EventService {

    EventRepository eventRepository;
    StudentService studentService;
    ClassCodeService classCodeService;

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Optional<Event> findById(Integer eventId) {
        return eventRepository.findById(eventId);
    }
    public List<Event> findByClassCode(String classCode){return eventRepository.findAll().stream().filter(event -> classCodeService.compareCodes(event.getClassCode(), classCode)).collect(Collectors.toList());}

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> getPastFiveEvents(String classCode){
        return eventRepository.findAll().stream().filter(event -> event.getClassCode().equals(classCode)&&(event.getEndDate().compareTo(new Date()) < 0)).collect(Collectors.toList());

    }

    public List<Event> getPendingEvents(String studentId) throws StudentNotFoundException {
        Student student = studentService.findByID(studentId);
        log.info(student.toString());
        return eventRepository.findAll().stream().filter(event -> (!student.getEvents().contains(event)) && (classCodeService.compareCodes(student.getClassCode(), event.getClassCode())) ).collect(Collectors.toList());
    }

    public void updateEvent(String studentId, String eventId) throws StudentNotFoundException, EventNotFoundException {

        Event event = findById(Integer.valueOf(eventId)).orElseThrow(() -> new EventNotFoundException("Event not found id :" + eventId));
        Student stu = studentService.findByID(studentId);
        if(stu == null || event == null)
            return;

        stu.addEvent(event);
        studentService.save(stu);
    }

}
