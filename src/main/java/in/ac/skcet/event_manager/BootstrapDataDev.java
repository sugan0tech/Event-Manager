package in.ac.skcet.event_manager;

import com.github.javafaker.Faker;
import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Student;
import in.ac.skcet.event_manager.models.Teacher;
import in.ac.skcet.event_manager.repositories.EventRepository;
import in.ac.skcet.event_manager.repositories.StudentRepository;
import in.ac.skcet.event_manager.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;


@Slf4j
@Component
@AllArgsConstructor
@Profile({"default", "dev"})
public class BootstrapDataDev implements CommandLineRunner {
    private EventRepository eventRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;


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

        Student studentOneB = Student.builder().rollNo("20eucs152")
                .name("Sushanthika")
                .classCode("III CSE C")
                .dateOfBirth(new Date())
                .mobile(faker.number().digits(10))
                .isHosteler(false)
                .mail("20eucs152@skcet.ac.in").build();

        Student studentTwoB = Student.builder().rollNo("20eucs125")
                .name("selvakumar")
                .classCode("III CSE C")
                .dateOfBirth(new Date())
                .mobile(faker.number().digits(10))
                .isHosteler(true)
                .mail("20eucs125@skcet.ac.in").build();

        Student studentOne = Student.builder().rollNo("20eucs147")
                .name("sugan")
                .classCode("III CSE C")
                .dateOfBirth(new Date())
                .mobile(faker.number().digits(10))
                .isHosteler(true)
                .mail("20eucs147@skcet.ac.in").build();

        Student studentTwo = Student.builder().rollNo("20eucs137")
                .name("giri")
                .classCode("III CSE C")
                .dateOfBirth(new Date())
                .mobile(faker.number().digits(10))
                .isHosteler(true)
                .mail("20eucs137@skcet.ac.in").build();

        Student studentThree = Student.builder().rollNo("20eucs127")
                .name("shalini")
                .classCode("III CSE C")
                .dateOfBirth(new Date())
                .mobile(faker.number().digits(10))
                .isHosteler(true)
                .mail("20eucs127@skcet.ac.in").build();

        Event eventOne = Event.builder()
                .title("HourOfPlacement")
                .description("From SKCET placement portal")
                .fromDate(new Date())
                .endDate(new Date())
                .classCodes("III CSE C")
                .build();

        Event eventTwo = Event.builder()
                .title("Bootathon")
                .description("From Bootcamp devision")
                .fromDate(new Date())
                .endDate(new Date())
                .classCodes("III CSE C")
                .build();


        eventRepository.save(eventOne);
        eventRepository.save(eventTwo);

        teacherRepository.save(teacherOne);
        teacherRepository.save(teacherTwo);
        studentRepository.save(studentOne);
        studentRepository.save(studentTwo);
        studentRepository.save(studentOneB);
        studentRepository.save(studentTwoB);
        studentRepository.save(studentThree);

        log.info(studentRepository.findByClassCode("III CSE C").toString());

    }
}
