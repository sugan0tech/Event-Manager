package in.ac.skcet.event_manager.commands;

import in.ac.skcet.event_manager.models.Event;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class EventCmdToEventTest {
    EventCommand eventCommand;
    @BeforeEach
    void setUp() {
        eventCommand = new EventCommand("Desc", "III CSE C", "2010-01-01 13:00:01", "2020-12-01 13:00:01");
    }

    @Test
    void fromDateTest() {
        EventCmdToEvent eventCmdToEvent = new EventCmdToEvent();
        Event event = eventCmdToEvent.convert(eventCommand);
        assertEquals("Fri Jan 01 13:00:01 IST 2010", event.getFromDate().toString() );
    }

    @Test
    void endDateTest() {
        EventCmdToEvent eventCmdToEvent = new EventCmdToEvent();
        Event event = eventCmdToEvent.convert(eventCommand);
        assertEquals("Tue Dec 01 13:00:01 IST 2020", event.getEndDate().toString() );
    }
}