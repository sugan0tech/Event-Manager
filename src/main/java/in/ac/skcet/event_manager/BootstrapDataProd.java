package in.ac.skcet.event_manager;

import in.ac.skcet.event_manager.event.EventService;
import in.ac.skcet.event_manager.on_duty.OnDutyForm;
import in.ac.skcet.event_manager.on_duty.OnDutyFormService;
import in.ac.skcet.event_manager.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import in.ac.skcet.event_manager.event.EventStatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;


@Slf4j
@Component
@AllArgsConstructor
@Profile("prod")
@Order
@Transactional
public class BootstrapDataProd implements CommandLineRunner {
    private EventStatService eventStatService;
    private StudentService studentService;
    private ResourceLoader resourceLoader;
    private OnDutyFormService onDutyFormService;
    private EventService eventService;


    @Override
    public void run(String... args) throws Exception {
        onDutyFormService.findAll().forEach(onDutyForm -> {
            if(onDutyForm.getEndDate().before(new Date())){
                onDutyForm.getStudentSet().forEach(student -> {
                    student.setOnDuty(false);
                    studentService.save(student);
                });
            }
        });
    }

}
