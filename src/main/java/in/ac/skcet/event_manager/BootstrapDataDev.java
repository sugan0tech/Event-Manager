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

        Attendance attendance = Attendance.builder().date(new Date()).build();
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
                .email("20eucs147@skcet.ac.in")
                .token("fBXw5XxYTc2eksqIwAkCPk:APA91bGcKc_Zcn_RZYSOYqwiNvtr74eB79Hf8iIn7cmRT3yCSxii7AKlIfULkEz8Gh8tePH-fwG9kXiMS3cCPvMDTeoPTKZ0cU8w9Cybiz3tNb0ziL21TD6cEXIeCQt3ATkLZMOj-qzw")
                .build();
        RegisteredUser registeredUser2 = RegisteredUser.builder()
                .email("srigirit369@gmail.com")
                .token("dnEOnth5RLWMMm5adhOE63:APA91bHwV9A37Mjv_Nc0cDP-qQgX-vo98gPlArwRo_7bh_nFzZuTu_Qqnado3IkKEHqL2PnPh9PRLRMdUzxfCHArVg4ntthz0yATWgRDEROLZTTbzB5f_vaReZ-VSerxYNGW-ZV0xOAN")
                .build();
        RegisteredUser registeredUser3 = RegisteredUser.builder()
                .email("sugankpms@gmail.com")
                .token("dBzuzDL6Tg6ESsMjze41s9:APA91bGIqqwAaPt3B0EjgF961IITNK2S0INhwZlUL2bBbNGuKP3Zru6k1yS8P-lke-UAM7gTnt-JBcUlFk1dZW4NruqvziEeFUqLVTwIClPRDROcI4ZOpJH7cl5lbXpiLw-aM4h5mIyL")
                .build();

        RegisteredUser registeredUser4 = RegisteredUser.builder()
                .email("20eucs137@skcet.ac.in")
                .token("fySS3NlRTaSQ1D1-u_SCN4:APA91bFABQpz2cRhJim-8Zw4TYz0YvI86GbDnaLPcpPbTfgIXa8Vbj6qqKMIDxfbX-NBMXU88orrRJYuHHJEqyOeZ_kpfzACyvqoyxrji27MMrCKE9xqiiesQOiBKh-AzXPzOMUr3h6E")
                .build();
        registeredUserService.save(registeredUser);
        registeredUserService.save(registeredUser2);
        registeredUserService.save(registeredUser3);
        registeredUserService.save(registeredUser4);




    }
}
