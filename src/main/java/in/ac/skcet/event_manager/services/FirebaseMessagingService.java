package in.ac.skcet.event_manager.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import in.ac.skcet.event_manager.models.Note;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {
    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    public String sendNotification(Note note, String token) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();

        Message message = Message
                .builder()
                .setToken("ezHx0XqJR0KzWB7uisx16r:APA91bFqMGVoppfbXUrLya2mievN6d7IqKJhwhoxmozR0lQjMnUCmKGnTezhB7Zvy2l8lL4KDBowO7Awn_s8ZA4QvUIwg-j7tnrY6cQBO2Ng_d5U7Q89FCzJ-uDls5Vlm7cTf073qtO8")
                .setNotification(notification)
                .putAllData(note.getData())
                .build();

        return firebaseMessaging.send(message);
    }
}
