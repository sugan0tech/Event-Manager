package in.ac.skcet.event_manager.on_duty;

import in.ac.skcet.event_manager.event.Event;
import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@ToString
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OnDutyForm {
    @Id
    private String id;

    private String description;

    private Binary document;

    private Set<String> studentSet;

    private Set<String> mentorSet;

    private Set<String> signatures = new HashSet<>();

    private String canceledBy;

    private Date fromDate;

    private Date endDate;
}
