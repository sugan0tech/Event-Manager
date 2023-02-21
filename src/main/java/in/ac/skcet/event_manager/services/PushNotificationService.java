package in.ac.skcet.event_manager.services;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Note;
import in.ac.skcet.event_manager.models.Student;
import in.ac.skcet.event_manager.models.Teacher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class PushNotificationService {
   FirebaseMessagingService firebaseMessagingService;
   StudentService studentService;
   RegisteredUserService registeredUserService;
   TeacherService teacherService;
   EventStatService eventStatService;


   public void eventCompletionNotification(Event event) throws FirebaseMessagingException {
       Set<Teacher> teacherSet = teacherService.findByClassCode(event.getClassCode());
       log.info(teacherSet.toString());
       Map<String, Integer> stats = eventStatService.getEventStat(event.getEventId(), event.getClassCode());
       log.info(stats.toString());
       for(Teacher teacher : teacherSet){
           String token = registeredUserService.getTokenByEmail(teacher.getMail()).orElse(null);
           if(token != null){
               Note note = Note.builder()
                        .content("pending -" + stats.get("pending") + "\ncompleted -" + stats.get("completed"))
                       .subject("Event Ended { " + event.getTitle() + " }")
                       .build();

               log.info(token);
               firebaseMessagingService.sendNotificationByToken(note, token);
           }
       }
   }

    public void eventNotification(Event event) throws FirebaseMessagingException {
        Set<Student> studnetSet = studentService.findByClassCode(event.getClassCode());
        for(Student student : studnetSet){
            String token = registeredUserService.getTokenByEmail(student.getMail()).orElse(null);
            if(token != null){
                Note note = Note.builder()
                        .content(event.getTitle())
                        .subject("New event alert!!!")
                        .build();

                log.info(token);
                firebaseMessagingService.sendNotificationByToken(note, token);
            }
        }
    }

}
