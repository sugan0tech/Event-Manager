package in.ac.skcet.event_manager.student;

import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.event.Event;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Lazy(false)
public class Student {
    @Id
    private String rollNo;
    private String name;
    private String classCode;
    @Basic
    private Date dateOfBirth;
    private String mail;
    private String mobile;
    private Boolean onDuty = false;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Event> events = new HashSet<>();

    @ManyToMany
    private Set<Attendance> attendanceSet = new HashSet<>();

    public void addEvent(Event event){
        if(this.events == null){
            this.events = new HashSet<>();
            this.events.add(event);
            return;
        }
        this.events.add(event);
    }

    public void addAttendance(Attendance attendance){
        if(this.attendanceSet == null){
            this.attendanceSet = new HashSet<>();
            this.attendanceSet.add(attendance);
            return;
        }
        this.attendanceSet.add(attendance);
    }
    @Override
    public String toString() {
        return "Student{" +
                "rollNo='" + rollNo + '\'' +
                ", name='" + name + '\'' +
                ", classCode='" + classCode + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", mail='" + mail + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
