package in.ac.skcet.event_manager.services;

import in.ac.skcet.event_manager.commands.EventCmdToEvent;
import in.ac.skcet.event_manager.commands.EventCommand;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Student;
import in.ac.skcet.event_manager.repositories.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class EventService {

    EventRepository eventRepository;

    public Event save(EventCommand eventCommand) {
        EventCmdToEvent eventCmdToEvent = new EventCmdToEvent();
        Event event = eventCmdToEvent.convert(eventCommand);
        return eventRepository.save(event);
    }

}
