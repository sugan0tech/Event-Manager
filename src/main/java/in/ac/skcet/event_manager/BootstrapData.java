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
import org.springframework.stereotype.Component;

import java.util.Date;


@Slf4j
@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private EventRepository eventRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Teacher teacherOne = Teacher.builder().name("James")
                .classCode("III CSE C")
                .mail("james@gmail.com")
                .mobile(faker.number().digits(10))
                .staffId("ramesh")
                .build();
        Teacher teacherTwo = Teacher.builder().name("Vames")
                .classCode("III CSE B")
                .mail("Vames@gmail.com")
                .mobile(faker.number().digits(10))
                .staffId("Vames")
                .build();

        Student studentOneB = Student.builder().rollNo("20eucs064")
                .name("Bala")
                .classCode("III CSE B")
                .dateOfBirth(new Date())
                .mobile(faker.number().digits(10))
                .isHosteler(true)
                .mail("20eucs064@skcet.ac.in").build();

        Student studentTwoB = Student.builder().rollNo("20eucs065")
                .name("Muruga")
                .classCode("III CSE B")
                .dateOfBirth(new Date())
                .mobile(faker.number().digits(10))
                .isHosteler(true)
                .mail("20eucs065@skcet.ac.in").build();

        Student studentOne = Student.builder().rollNo("20eucs147")
                .name("sugan")
                .classCode("III CSE C")
                .dateOfBirth(new Date())
                .mobile(faker.number().digits(10))
                .isHosteler(true)
                .mail("20eucs147@skcet.ac.in").build();

        Student studentTwo = Student.builder().rollNo("20eucs148")
                .name("sujith")
                .classCode("III CSE C")
                .dateOfBirth(new Date())
                .mobile(faker.number().digits(10))
                .isHosteler(true)
                .mail("20eucs148@skcet.ac.in").build();

        Event eventOne = Event.builder()
                .eventId(1)
                .description("HourOfPlacement")
                .fromDate(new Date())
                .endDate(new Date())
                .build();

        Event eventTwo = Event.builder()
                .eventId(2)
                .description("Bootathon")
                .fromDate(new Date())
                .endDate(new Date())
                .build();


        teacherRepository.save(teacherOne);
        teacherRepository.save(teacherTwo);
        studentRepository.save(studentOne);
        studentRepository.save(studentTwo);
        studentRepository.save(studentOneB);
        studentRepository.save(studentTwoB);
        eventRepository.save(eventOne);
        eventRepository.save(eventTwo);

    }
}
