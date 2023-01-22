package in.ac.skcet.event_manager.models;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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
    private Boolean isHosteler;
    private String mail;
    private String mobile;
    private String classCodes;

    @OneToMany
    private Set<Event> events = new HashSet<>();

    public void addEvent(Event event){
        if(this.events == null){
            this.events = new HashSet<>();
            this.events.add(event);
            return;
        }
        this.events.add(event);
    }

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
                '}';
    }
}
