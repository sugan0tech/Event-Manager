package in.ac.skcet.event_manager.models;

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
    private byte[] document;

    @OneToMany
    @ToString.Exclude
    private Set<Student> studentSet = new HashSet<>();

    @OneToMany
    @ToString.Exclude
    private Set<Staff> mentorSet = new HashSet<>();

    private Date fromDate;

    private Date endDate;
}
