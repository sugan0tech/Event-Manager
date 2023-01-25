package in.ac.skcet.event_manager.commands;

import in.ac.skcet.event_manager.models.Event;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class EventCmdToEventTest {
    EventCommand eventCommand;
    SimpleDateFormat simpleDateFormat;
    @BeforeEach
    void setUp() {
        simpleDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        eventCommand = new EventCommand("Desc", "III CSE C", "2010-01-01 IST 13:00:01", "2020-12-01 IST 13:00:01");
    }

    @Test
    void fromDateTest() {
        EventCmdToEvent eventCmdToEvent = new EventCmdToEvent();
        Event event = eventCmdToEvent.convert(eventCommand);
        assertEquals("Fri Jan 01 13:00:01 IST 2010", simpleDateFormat.format(event.getFromDate() ));
    }

    @Test
    void endDateTest() {
        EventCmdToEvent eventCmdToEvent = new EventCmdToEvent();
        Event event = eventCmdToEvent.convert(eventCommand);
        assertEquals("Tue Dec 01 13:00:01 IST 2020", simpleDateFormat.format(event.getEndDate()) );
    }
}