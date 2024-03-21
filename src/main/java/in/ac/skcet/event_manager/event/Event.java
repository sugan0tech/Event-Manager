package in.ac.skcet.event_manager.event;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer eventId;
    private String title;
    private String description;
    private Location location = Location.DEFAULT;
    private Date fromDate;
    private Date endDate;
    private String classCode;
}
