package in.ac.skcet.event_manager.commands;

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
    private String fromDate;
    private String endDate;


}
