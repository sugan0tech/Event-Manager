package in.ac.skcet.event_manager.on_duty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.bson.types.Binary;

import java.util.*;

@ToString
@AllArgsConstructor
@Data
public class OnDutyFormCommand {
    private String description;
    private Binary document;
    private Set<String> studentRollNoList;
    private Set<String> mentorNameList;
    private Set<String> signatures;
    private String canceledBy;
    private String fromDate;
    private String endDate;
}
