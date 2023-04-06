package in.ac.skcet.event_manager.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import in.ac.skcet.event_manager.event.EventCmdToEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import in.ac.skcet.event_manager.class_code.ClassCodeService;
import in.ac.skcet.event_manager.event.Event;
import in.ac.skcet.event_manager.event.EventService;
import in.ac.skcet.event_manager.event.EventStatService;
import in.ac.skcet.event_manager.firebase_notification.PushNotificationService;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.teacher.StaffEventTimer;
import in.ac.skcet.event_manager.teacher.Teacher;
import in.ac.skcet.event_manager.teacher.TeacherService;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class EventControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @Mock
    private StudentService studentService;

    @Mock
    private EventStatService eventStatService;

    @Mock
    private ClassCodeService classCodeService;

    @Mock
    private TeacherService teacherService;

    @Mock
    private PushNotificationService pushNotificationService;

    @Mock
    private StaffEventTimer staffEventTimer;

    @Mock
    private EventCmdToEvent eventCmdToEvent;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    public void testGetEvents() throws Exception {
        List<Event> events = new ArrayList<>();
        Event event1 = new Event();
        event1.setEventId(1);
        Event event2 = new Event();
        event2.setEventId(2);
        events.add(event1);
        events.add(event2);

        when(teacherService.findById(anyString())).thenReturn(new Teacher());
        when(teacherService.findEvents(anyString())).thenReturn(events);

        mockMvc.perform(MockMvcRequestBuilders.post("/events/pending/{staffId}", "staff1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
