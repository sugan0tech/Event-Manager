package in.ac.skcet.event_manager.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.event.Event;

public class StudentTest {

    private Student student;
    private Event event1;
    private Event event2;
    private Attendance attendance;
    private BitSet bitSet;

    @BeforeEach
    public void setUp() {
        student = Student.builder()
                .rollNo("123")
                .name("John Doe")
                .classCode("ABC")
                .dateOfBirth(Date.valueOf("2000-01-01"))
                .mail("john.doe@example.com")
                .mobile("1234567890")
                .onDuty(false)
                .build();

        event1 = Event.builder()
                .eventId(1)
                .description("Description 1")
                .build();

        event2 = Event.builder()
                .eventId(2)
                .description("Description 2")
                .build();

        attendance = Attendance.builder()
                .id(1L)
                .build();

        bitSet = new BitSet();
        bitSet.set(1);
    }

    @Test
    public void testAddEvent() {
        student.addEvent(event1);
        Set<Event> expectedEvents = new HashSet<>();
        expectedEvents.add(event1);
        assertEquals(expectedEvents, student.getEvents());
    }

    @Test
    public void testAddAttendance() {
        student.addAttendance(attendance, bitSet);
        assertEquals(bitSet, student.getAttendanceBitSetMap().get(attendance));
    }

}
