package in.ac.skcet.event_manager.student;

import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.attendance.PeriodSet;
import in.ac.skcet.event_manager.event.Event;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    @ToString.Exclude
    private Set<Event> events = new HashSet<>();

    public Map<Attendance, Integer> getAttendancePeriodSet() {
        if(attendancePeriodSetMap == null){
            new HashMap<>();
        }
        return attendancePeriodSetMap;
    }

    @ElementCollection
    private Map<Attendance, Integer> attendancePeriodSetMap = new HashMap<>();

    public void addEvent(Event event){
        if(this.events == null){
            this.events = new HashSet<>();
            this.events.add(event);
            return;
        }
        this.events.add(event);
    }


    public void addAttendance(Attendance attendance, PeriodSet periodSet){
        if(this.attendancePeriodSetMap == null){
            this.attendancePeriodSetMap = new HashMap<>();
            this.attendancePeriodSetMap.put(attendance, periodSet.getValue());
            return;
        }
        if(attendancePeriodSetMap.containsKey(attendance)){
            periodSet.or(new PeriodSet(attendancePeriodSetMap.get(attendance)));
            attendancePeriodSetMap.put(attendance, periodSet.getValue());
        }else{
            log.info(periodSet.toString());
            this.attendancePeriodSetMap.put(attendance, periodSet.getValue());
        }
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
                ", onDuty='" + onDuty + '\'' +
                '}';
    }
}
