package in.ac.skcet.event_manager.services;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.models.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
@Slf4j
@AllArgsConstructor
public class TimerService {
    PushNotificationService pushNotificationService;
    public void setTimerForEvent(Event event){

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    pushNotificationService.eventCompletionNotification(event);
                    log.info("An Automatic Event Timer For Staffs");
                } catch (FirebaseMessagingException e) {
                    log.error("error in timer task");
                }

            }
        };

        Timer timer = new Timer("Timer");
        Long delay = 30000L;
        timer.schedule(timerTask, delay);
    }

}
