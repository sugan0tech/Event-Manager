package in.ac.skcet.event_manager.event;

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

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Optional<Event> findById(Integer eventId) {
        return eventRepository.findById(eventId);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> getPastFiveEvents(String classCode){
        return eventRepository.findAll().stream().filter(event -> event.getClassCode().equals(classCode)&&(event.getEndDate().compareTo(new Date()) < 0)).collect(Collectors.toList());

    }

}
