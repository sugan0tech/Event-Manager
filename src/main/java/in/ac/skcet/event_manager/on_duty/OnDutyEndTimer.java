package in.ac.skcet.event_manager.on_duty;
import in.ac.skcet.event_manager.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
@Slf4j
@AllArgsConstructor
public class OnDutyEndTimer {
    StudentService studentService;

    public void autoEndOdTimer(OnDutyForm onDutyForm){

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                log.info("removed Od : " + onDutyForm.getDescription());
                onDutyForm.getStudentSet().forEach(studentId -> {
                    studentService.cancelOd(studentId);
                });
            }
        };

        log.info(onDutyForm.getEndDate().toString());
        new Timer().schedule(timerTask, onDutyForm.getEndDate());

    }
}
