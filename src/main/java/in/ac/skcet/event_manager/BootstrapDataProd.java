package in.ac.skcet.event_manager;

import com.github.javafaker.Faker;
import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.attendance.AttendanceRepository;
import in.ac.skcet.event_manager.attendance.AttendanceService;
import in.ac.skcet.event_manager.attendance.AttendanceStudentService;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.on_duty.OnDutyFormService;
import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.teacher.Teacher;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Component
@AllArgsConstructor
@Profile("prod")
@Order
@Transactional
public class BootstrapDataProd implements CommandLineRunner {
    private StudentService studentService;
    private OnDutyFormService onDutyFormService;
    private TeacherService teacherService;
    private StudentService mongoService;


    @Override
    public void run(String... args) throws Exception {
        var ods = onDutyFormService.findAll();
        ods.forEach(onDutyForm -> {
            if(onDutyForm.getDocument() == null || onDutyForm.getDocument().length() == 0){
                onDutyFormService.cancel(onDutyForm, "sugankpms");
            }
        });
//        studentService.findAll().forEach(sugan -> {
//            Student studentMongo = Student.builder()
//                    .rollNo(sugan.getRollNo())
//                    .name(sugan.getName())
//                    .attendancePeriodSetMap(sugan.getAttendancePeriodSetMap().entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey().getId(), Map.Entry::getValue)))
//                    .dateOfBirth(sugan.getDateOfBirth()).events(sugan.getEvents())
//                    .classCode(sugan.getClassCode())
//                    .mail(sugan.getMail())
//                    .onDuty(false)
//                    .mobile(sugan.getMobile())
//                    .build();
//
//            log.info(studentMongo.toString());
//            mongoService.save(studentMongo);
//        });

//        onDutyFormService.findAll().forEach(onDutyForm -> {
//            if(onDutyForm.getEndDate().before(new Date())){
//                onDutyForm.getStudentSet().forEach(student -> {
//                    student.setOnDuty(false);
//                    studentService.save(student);
//                });
//            }
//        });
//        var faker = new Faker();
//        for ( int i = 65; i <= 123; i++){
//            if ( i == 91 ){
//                continue;
//            }
//
//        }

        Teacher teacher = Teacher.builder().staffId("srigirit369").classCode("CSE").mail("srigirit369@gmail.com").mobile("9344953235").build();
        try {
            teacherService.findById(teacher.getStaffId());
        }catch (TeacherNotFoundException e){
            teacherService.save(teacher);
            log.error(e.toString());
        }
    }

}
