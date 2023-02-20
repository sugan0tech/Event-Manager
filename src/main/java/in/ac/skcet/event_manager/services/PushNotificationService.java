package in.ac.skcet.event_manager.services;

import com.google.firebase.messaging.FirebaseMessagingException;
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

   public String notifyAll(String classCode) throws FirebaseMessagingException {
       Set<Student> studnetSet = studentService.findByClassCode(classCode);
       log.info(studnetSet.toString());
       for(Student student : studnetSet){
           String token = registeredUserService.getTokenByEmail(student.getMail());
           if(token != null){
               Note note = Note.builder()
                       .content("hiii")
                       .subject("whi")
                       .image("hisis")
                       .build();

               log.info(token);
               firebaseMessagingService.sendNotificationByToken(note, token);
           }
       }
       return "yeah";
   }

}
