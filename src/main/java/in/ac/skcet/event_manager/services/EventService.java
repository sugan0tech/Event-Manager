package in.ac.skcet.event_manager.services;

import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.repositories.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;


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

    public List<Event> getPastFiveEvents(String classCode) {
        return eventRepository.findAllByEndDateBeforeAndClassCodeLike(new Date(), classCode + "%", PageRequest.of(0,5, Sort.by("endDate").descending()));

    }

}
