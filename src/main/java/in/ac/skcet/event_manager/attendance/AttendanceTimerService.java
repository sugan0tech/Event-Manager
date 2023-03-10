package in.ac.skcet.event_manager.attendance;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.firebase_notification.PushNotificationService;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.teacher.Teacher;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class AttendanceTimerService {
    StudentService studentService;
    TeacherService teacherService;
    PushNotificationService pushNotificationService;
    AttendanceService attendanceService;

//    @Scheduled(fixedDelay = 100000) // for test
    @Scheduled(cron = "0 33 11 * * *") // for 9:30 am
    public void sendAttendanceNotification() throws TeacherNotFoundException {

        List<Teacher> teacherList = teacherService.findAll();
        Date date  = new Date();
        teacherList.forEach(teacher -> {
            try {
                pushNotificationService.attendanceNotificationPerStaff(teacher,attendanceService.findByDate(new SimpleDateFormat("yyyy-MM-dd").format(date)));
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
