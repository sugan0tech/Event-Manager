package in.ac.skcet.event_manager.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventId;
    private String description;
    private Date fromDate;
    private Date endDate;
    @ManyToMany
    @ToString.Exclude
    private Set<Student> studentSet;
}
