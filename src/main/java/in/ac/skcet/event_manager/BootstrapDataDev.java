package in.ac.skcet.event_manager;

import com.github.javafaker.Faker;
import in.ac.skcet.event_manager.models.*;
import in.ac.skcet.event_manager.repositories.AttendanceRepository;
import in.ac.skcet.event_manager.repositories.EventRepository;
import in.ac.skcet.event_manager.repositories.StudentRepository;
import in.ac.skcet.event_manager.repositories.TeacherRepository;
import in.ac.skcet.event_manager.services.EventStatService;
import in.ac.skcet.event_manager.services.RegisteredUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


@Slf4j
@Component
@AllArgsConstructor
@Profile({"default", "dev"})
public class BootstrapDataDev implements CommandLineRunner {
    private EventRepository eventRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private AttendanceRepository attendanceRepository;
    private RegisteredUserService registeredUserService;
    private EventStatService eventStatService;


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Teacher teacherOne = Teacher.builder().name("James")
                .classCode("III CSE C")
                .mail("srigirit369@gmail.com")
                .mobile("9344953235")
                .staffId("srigirit369")
                .build();
        Teacher teacherTwo = Teacher.builder().name("sugankpms")
                .classCode("III CSE C")
                .mail("sugankpms@gmail.com")
                .mobile(faker.number().digits(10))
                .staffId("sugankpms")
                .build();
        Teacher teacherThree = Teacher.builder().name("giri")
                .classCode("III CSE C")
                .mail("srigiriboopathy@gmail.com")
                .mobile(faker.number().digits(10))
                .staffId("srigiriboopathy")
                .build();

        Teacher teacherFour = Teacher.builder().name("Gowthamani R")
                .classCode("III CSE C")
                .mail("gowthamanir@skcet.ac.in")
                .mobile(faker.number().digits(10))
                .staffId("gowthamanir")
                .build();

        Teacher teacherFive = Teacher.builder().name("Sasikala Rani K")
                .classCode("III CSE C")
                .mail("sasikalaranik@skcet.ac.in")
                .mobile(faker.number().digits(10))
                .staffId("sasikalaranik")
                .build();



        Student studentOneB = Student.builder().rollNo("20eucs152")
                .name("Sushanthika M")
                .classCode("III CSE C")
                .dateOfBirth(java.sql.Date.valueOf("2002-12-16"))
                .mobile(faker.number().digits(10))
                .mail("20eucs152@skcet.ac.in").build();

        Student studentTwoB = Student.builder().rollNo("20eucs125")
                .name("Selvakumar S")
                .classCode("III CSE C")
                .dateOfBirth(java.sql.Date.valueOf("2002-12-16"))
                .mobile(faker.number().digits(10))
                .mail("20eucs125@skcet.ac.in").build();

        Student studentOne = Student.builder().rollNo("20eucs147")
                .name("Sugavanesh M")
                .classCode("III CSE C")
                .dateOfBirth(java.sql.Date.valueOf("2002-12-16"))
                .mobile(faker.number().digits(10))
                .mail("20eucs147@skcet.ac.in").build();

        Student studentTwo = Student.builder().rollNo("20eucs137")
                .name("Srigiri T")
                .classCode("III CSE C")
                .dateOfBirth(java.sql.Date.valueOf("2002-12-16"))
                .mobile(faker.number().digits(10))
                .mail("20eucs137@skcet.ac.in").build();

        Student studentThree = Student.builder().rollNo("20eucs127")
                .name("Shalini S")
                .classCode("III CSE C")
                .dateOfBirth(java.sql.Date.valueOf("2002-12-16"))
                .mobile(faker.number().digits(10))
                .mail("20eucs127@skcet.ac.in").build();

        Event eventOne = Event.builder()
                .title("HourOfPlacement")
                .description("From SKCET placement portal")
                .fromDate(new Date())
                .endDate(new Date())
                .classCode("III CSE C")
                .build();

        Event eventTwo = Event.builder()
                .title("Bootathon")
                .description("From Bootcamp devision")
                .fromDate(new Date())
                .endDate(new Date())
                .classCode("III CSE C")
                .build();

        Attendance attendance = Attendance.builder().date(java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))).build();
        attendanceRepository.save(attendance);

        studentOne.addAttendance(attendance);
        studentTwo.addAttendance(attendance);

        eventRepository.save(eventOne);
        eventRepository.save(eventTwo);

        teacherRepository.save(teacherOne);
        teacherRepository.save(teacherTwo);
        teacherRepository.save(teacherThree);
        teacherRepository.save(teacherFour);
        teacherRepository.save(teacherFive);
        studentRepository.save(studentOne);
        studentRepository.save(studentTwo);
        studentRepository.save(studentOneB);
        studentRepository.save(studentTwoB);
        studentRepository.save(studentThree);

        log.info(studentRepository.findByClassCode("III CSE C").toString());
        RegisteredUser registeredUser = RegisteredUser.builder()
                .email("20eucs125@skcet.ac.in")
                .token("emrPinweTCuiJw41rFS1XS:APA91bF6EvlddkxAk9OppGO-iw3WwSl9ZCzck8MZu10nz-e6eF083PYvFjIT4f2QFGuEbB29X3NsZDyqH0Eu8fMloGUM2rAb4XAN6seZrRTZimJ3cJCb_28nPvmr6LW9Zyok6fZ4ozVK")
                .build();
        RegisteredUser registeredUser2 = RegisteredUser.builder()
                .email("srigirit369@gmail.com")
                .token("d-cE2jjRRrWCX_wYsFaESv:APA91bFlh_ng-w2DgF8hPlvd5CgCSMbyscw9AdMuZP_cHxbakY4_SMiDoWKvZ1foFHPHch55cgmReoIcDkEvT4WBhpwV59DGViFcoEzhFN97kbtym1-JRzfk08_-qUSycaN0oEU-8JzD")
                .build();
        RegisteredUser registeredUser3 = RegisteredUser.builder()
                .email("sugankpms@gmail.com")
                .token("f9zgiDOzQ1iPDjFlN-pkkY:APA91bGZ5KO27qOqq7X-ANIv74Vb9P9zhq6M3Fti1J5CX6Dy423M3ICSk87j0y8Q_JxM_WvnzoIzlh4WZv1tA1rLqRoneWYh9ii7xMbUfNdPBKBuqZCirX1oIn9VixS9HoLglgFH_1wW")
                .build();

        RegisteredUser registeredUser4 = RegisteredUser.builder()
                .email("20eucs137@skcet.ac.in")
                .token("fySS3NlRTaSQ1D1-u_SCN4:APA91bFABQpz2cRhJim-8Zw4TYz0YvI86GbDnaLPcpPbTfgIXa8Vbj6qqKMIDxfbX-NBMXU88orrRJYuHHJEqyOeZ_kpfzACyvqoyxrji27MMrCKE9xqiiesQOiBKh-AzXPzOMUr3h6E")
                .build();
        registeredUserService.save(registeredUser);
        registeredUserService.save(registeredUser2);
        registeredUserService.save(registeredUser3);
        registeredUserService.save(registeredUser4);


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Map<String, Integer> stats = eventStatService.getEventStat(1, "III CSE C");
                log.info(stats.toString());
            }
        };

        Timer timer = new Timer("Timer");
        Long delay = 25000L;
        timer.schedule(timerTask, delay);

    }

    @Slf4j
    @Component
    @AllArgsConstructor
    @Profile("prod")
    @Order
    @Transactional
    public static class BootstrapDataProd implements CommandLineRunner {
        private EventRepository eventRepository;
        private StudentRepository studentRepository;
        private TeacherRepository teacherRepository;
        private AttendanceRepository attendanceRepository;
        private RegisteredUserService registeredUserService;
        private EventStatService eventStatService;


        @Override
        public void run(String... args) throws Exception {
            log.info(studentRepository.findAll().toString());
            Map<String, Integer> stats = eventStatService.getEventStat(31, "III CSE C");
            log.info(eventStatService.getStudentStatusList(31, "III CSE C").toString());
            log.info(stats.toString());
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Map<String, Integer> stats = eventStatService.getEventStat(31, "III CSE C");
                    log.info(stats.toString());
                }
            };

            Timer timer = new Timer("Timer");
            Long delay = 1000L;
            timer.schedule(timerTask, delay);
        }
    }
}
