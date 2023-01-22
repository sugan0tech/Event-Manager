package in.ac.skcet.event_manager.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    private String rollNo;
    private String name;
    private String classCode;
    private Date dateOfBirth;
    private boolean isHosteler;
    private String mail;
    private String mobile;
    @ManyToMany(mappedBy = "studentSet")
    private Set<Event> eventSet;

    @Override
    public String toString() {
        return "Student{" +
                "rollNo='" + rollNo + '\'' +
                ", name='" + name + '\'' +
                ", classCode='" + classCode + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isHosteler=" + isHosteler +
                ", mail='" + mail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", eventSet=" + eventSet +
                '}';
    }
}
