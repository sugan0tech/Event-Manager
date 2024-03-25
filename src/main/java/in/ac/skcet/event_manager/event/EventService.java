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
        var usedLocations = eventRepository.findAll().stream()
                .map(Event::getLocation)
                .collect(Collectors.toSet());

//        var eventList = eventRepository.findAll().stream()
//                .filter(event1 -> event1.getLocation().equals(event.getLocation()))
//                .collect(Collectors.toList());
//
//        var isSlotAvailable = false;
//        eventList.forEach(event1 -> {
//            if (event.getEndDate() > event1.getFromDate()  && event1.getEndDate() < event.getEndDate())
//                return true;
//            return false;
//        });

        if(usedLocations.contains(event.getLocation())){
            log.error("Slot already reserved " + event.getLocation());
            return null;
        }
        return eventRepository.save(event);
    }

    public Optional<Event> findById(Integer eventId) {
        return eventRepository.findById(eventId);
    }
    public List<Event> findByClassCode(String classCode){
        return eventRepository.findAll().stream().filter(event -> classCodeService.compareCodes(event.getClassCode(), classCode)&&(event.getEndDate().compareTo(new Date()) >= 0)).collect(Collectors.toList());
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> getPastFiveEvents(String classCode){
        return eventRepository.findAll().stream().filter(event -> classCodeService.compareCodes(event.getClassCode(), classCode)&&(event.getEndDate().compareTo(new Date()) < 0)).collect(Collectors.toList());
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

        if (stu.getEvents().contains(event)) {
            return;
        }

        stu.addEvent(event);
        studentService.save(stu);
    }

    public Map<Location, Boolean> getLocationList(){
        var usedLocations = eventRepository.findAll().stream()
                .map(Event::getLocation)
                .collect(Collectors.toSet());

        Map<Location, Boolean> locationMap = new HashMap<>();

        // Initialize the HashMap with locations and false values
        locationMap.put(Location.KRISHNA_HALL, usedLocations.contains(Location.KRISHNA_HALL));
        locationMap.put(Location.BS01, usedLocations.contains(Location.BS01));
        locationMap.put(Location.BS02, usedLocations.contains(Location.BS02));
        locationMap.put(Location.BS03, usedLocations.contains(Location.BS03));
        locationMap.put(Location.BS04, usedLocations.contains(Location.BS04));
        locationMap.put(Location.SEMINAR_HALL, usedLocations.contains(Location.SEMINAR_HALL));
        locationMap.put(Location.CONFERENCE_HALL, usedLocations.contains(Location.CONFERENCE_HALL));
        locationMap.put(Location.JB_LAB, usedLocations.contains(Location.JB_LAB));
        locationMap.put(Location.AK_LAB, usedLocations.contains(Location.AK_LAB));
        return locationMap;
    }

}
