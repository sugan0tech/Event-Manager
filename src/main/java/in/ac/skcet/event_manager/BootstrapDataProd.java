package in.ac.skcet.event_manager;

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


    @Override
    public void run(String... args) throws Exception {
        Map<String, Integer> stats = eventStatService.getEventStat(31, "III CSE C");
        log.info(eventStatService.getStudentStatusList(31, "III CSE C").toString());
        log.info(stats.toString());
        log.info("-------------------------------------------------------------------");
        log.info(resourceLoader.getResource("classpath:").getURI().getPath());
        log.info(new Date().toString());
        log.info(studentService.findByID("20eucs124").getAttendanceBitSetMap().toString());
        log.info(studentService.findByID("20eucs125").getAttendanceBitSetMap().toString());

    }

}
