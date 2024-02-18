package in.ac.skcet.event_manager.student;

import com.google.firebase.database.annotations.NotNull;
import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.attendance.PeriodSet;
import in.ac.skcet.event_manager.event.Event;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@Document(collection = "students")
public class StudentMongo {
    @Id
    private String rollNo;
    @NotNull
    private String name;
    @NotNull
    private String classCode;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @NotNull
    private String mail;
    private String mobile;
    private Boolean onDuty = false;
    private Set<Event> events = new HashSet<>();

    public Map<Long, Integer> getAttendancePeriodSet() {
        if(attendancePeriodSetMap == null){
            new HashMap<>();
        }
        return attendancePeriodSetMap;
    }

    private Map<Long, Integer> attendancePeriodSetMap = new HashMap<>();

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
            this.attendancePeriodSetMap.put(attendance.getId(), periodSet.getValue());
            return;
        }
        if(attendancePeriodSetMap.containsKey(attendance.getId())){
            periodSet.or(new PeriodSet(attendancePeriodSetMap.get(attendance.getId())));
            attendancePeriodSetMap.put(attendance.getId(), periodSet.getValue());
        }else{
            log.info(periodSet.toString());
            this.attendancePeriodSetMap.put(attendance.getId(), periodSet.getValue());
        }
        log.info(this.attendancePeriodSetMap.toString());
        log.info("" + periodSet.getValue());
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
