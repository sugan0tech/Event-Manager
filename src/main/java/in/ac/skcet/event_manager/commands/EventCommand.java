package in.ac.skcet.event_manager.commands;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class EventCommand {
    private String description;
    private String classCode;
}
