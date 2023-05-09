package in.ac.skcet.event_manager.on_duty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import in.ac.skcet.event_manager.exception.OdFormNotFoundException;
import in.ac.skcet.event_manager.teacher.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.teacher.Staff;

@ExtendWith(MockitoExtension.class)
public class OnDutyFormTest {


    @Test
    public void testCreateOnDutyForm() {
        OnDutyForm createdForm = OnDutyForm.builder()
                .id(1L)
                .description("On duty form")
                .document(new byte[1024])
                .fromDate(new Date())
                .endDate(new Date())
                .studentSet(new HashSet<>(Arrays.asList(
                        Student.builder().rollNo("12345").build(),
                        Student.builder().rollNo("67890").build()
                )))
                .mentorSet(new HashSet<>(Arrays.asList(
                        Teacher.builder().staffId("T123").build(),
                        Teacher.builder().staffId("T456").build()
                )))
                .build();


        assertThat(createdForm.getId()).isNotNull();
        assertThat(createdForm.getDescription()).isEqualTo("On duty form");
        assertThat(createdForm.getDocument()).hasSize(1024);
        assertThat(createdForm.getFromDate()).isBeforeOrEqualTo(createdForm.getEndDate());
        assertThat(createdForm.getStudentSet()).hasSize(2);
        assertThat(createdForm.getStudentSet()).extracting(Student::getRollNo).containsExactly("12345", "67890");
        assertThat(createdForm.getMentorSet()).hasSize(2);
//        assertThat(createdForm.getMentorSet()).extracting(Staff::getStaffId).containsExactly("T456", "T123");
    }

}
