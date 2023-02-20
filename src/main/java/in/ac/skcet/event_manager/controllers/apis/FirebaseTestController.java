package in.ac.skcet.event_manager.controllers.apis;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.models.Note;
import in.ac.skcet.event_manager.services.FirebaseMessagingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Slf4j
@RestController
public class FirebaseTestController {
    FirebaseMessagingService firebaseMessagingService;

    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note
                                   ) throws FirebaseMessagingException {
        log.info(note.toString());
        return firebaseMessagingService.sendNotification(note, "gold");
    }
}
