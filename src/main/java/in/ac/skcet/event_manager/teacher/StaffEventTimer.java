package in.ac.skcet.event_manager.teacher;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.event.Event;
import in.ac.skcet.event_manager.firebase_notification.PushNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
@Slf4j
@AllArgsConstructor
public class StaffEventTimer {
    PushNotificationService pushNotificationService;
    public void setTimerForEvent(Event event){

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    pushNotificationService.eventCompletionNotification(event);
                    log.info("An Automatic Event Timer For Staffs");
                } catch (FirebaseMessagingException e) {
                    log.error(e.toString());
                }

            }
        };

        log.info("Scheduled time is also working");
        new Timer().schedule(timerTask, event.getEndDate());

    }

}
