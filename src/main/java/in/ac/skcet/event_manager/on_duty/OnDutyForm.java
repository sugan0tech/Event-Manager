package in.ac.skcet.event_manager.on_duty;

import in.ac.skcet.event_manager.teacher.Staff;
import in.ac.skcet.event_manager.student.Student;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class OnDutyForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @Lob
    @Column(length = 20971520)
    private byte[] document;

    @ManyToMany
    @ToString.Exclude
    private Set<Student> studentSet = new HashSet<>();

    @ManyToMany
    @ToString.Exclude
    private Set<Staff> mentorSet = new HashSet<>();

    private Date fromDate;

    private Date endDate;
}
