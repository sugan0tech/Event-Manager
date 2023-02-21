package in.ac.skcet.event_manager.services;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Note;
import in.ac.skcet.event_manager.models.Student;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class PushNotificationService {
   FirebaseMessagingService firebaseMessagingService;
   StudentService studentService;
   RegisteredUserService registeredUserService;

   public void pendingStudentsNotification(Event event) throws FirebaseMessagingException {
       Set<Student> studnetSet = studentService.findByClassCode(event.getClassCode());
       log.info(studnetSet.toString());
       for(Student student : studnetSet){
           String token = registeredUserService.getTokenByEmail(student.getMail()).orElse(null);
           if(token != null){
               Note note = Note.builder()
                       .content("hiii")
                       .subject("Complete it soon!!!")
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
