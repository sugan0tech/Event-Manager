package in.ac.skcet.event_manager.student;

import in.ac.skcet.event_manager.attendance.Attendance;
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

    public Map<Attendance, BitSet> getAttendanceBitSetMap() {
        if(attendanceBitSetMap == null){
            new HashMap<>();
        }
        return attendanceBitSetMap;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Map<Attendance, BitSet> attendanceBitSetMap = new HashMap<>();

    public void addEvent(Event event){
        if(this.events == null){
            this.events = new HashSet<>();
            this.events.add(event);
            return;
        }
        this.events.add(event);
    }


    public void addAttendance(Attendance attendance, BitSet bitSet){
        if(this.attendanceBitSetMap == null){
            this.attendanceBitSetMap = new HashMap<>();
            this.attendanceBitSetMap.put(attendance, bitSet);
            return;
        }
        if(attendanceBitSetMap.containsKey(attendance)){
            bitSet.or(attendanceBitSetMap.get(attendance));
            attendanceBitSetMap.put(attendance, bitSet);
        }else{
            log.info(bitSet.toString());
            this.attendanceBitSetMap.put(attendance, bitSet);
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
