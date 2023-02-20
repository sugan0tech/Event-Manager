package in.ac.skcet.event_manager;

import com.github.javafaker.Faker;
import in.ac.skcet.event_manager.models.*;
import in.ac.skcet.event_manager.repositories.AttendanceRepository;
import in.ac.skcet.event_manager.repositories.EventRepository;
import in.ac.skcet.event_manager.repositories.StudentRepository;
import in.ac.skcet.event_manager.repositories.TeacherRepository;
import in.ac.skcet.event_manager.services.RegisteredUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
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


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Teacher teacherOne = Teacher.builder().name("James")
                .classCode("III CSE C")
                .mail("srigirit369@gmail.com")
                .mobile(faker.number().digits(10))
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

        Attendance attendance = Attendance.builder().date(new Date()).build();
        attendanceRepository.save(attendance);

        studentOne.addAttendance(attendance);
        studentTwo.addAttendance(attendance);

        eventRepository.save(eventOne);
        eventRepository.save(eventTwo);

        teacherRepository.save(teacherOne);
        teacherRepository.save(teacherTwo);
        teacherRepository.save(teacherThree);
        studentRepository.save(studentOne);
        studentRepository.save(studentTwo);
        studentRepository.save(studentOneB);
        studentRepository.save(studentTwoB);
        studentRepository.save(studentThree);

        log.info(studentRepository.findByClassCode("III CSE C").toString());
        RegisteredUser registeredUser = RegisteredUser.builder()
                .email("20eucs147@skcet.ac.in")
                .token("fHye8lejSBCyDodRhf_gcL:APA91bFBtVs3JfCl2PiBB28Cpr7TW2s8rEcxleQWv4pfxqm-oBd6MLTIfK2DGEMf-lRiPbnsQRpinCyr2xMeyeSoqPuA1Yt7EelymJY4dT9NEAOlA2-ZjWfLXLPwDK1UC3DrEr6F2mdZ\n")
                .build();

        registeredUserService.save(registeredUser);


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                log.info("timer things");
                log.info("timer things");
                log.info("timer things");
                log.info("timer things");
                log.info("timer things");
                log.info("timer things");
            }
        };
        Timer timer = new Timer("Timer");
        Long delay = 2000L;
        timer.schedule(timerTask, delay);

    }
}
