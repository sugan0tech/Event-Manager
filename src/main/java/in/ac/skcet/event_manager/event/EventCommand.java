package in.ac.skcet.event_manager.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
public class EventCommand {
    private String title;
    private String description;
    private String classCode;
    private Location location;
    private String fromDate;
    private String endDate;


}
