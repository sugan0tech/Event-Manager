package in.ac.skcet.event_manager.time_table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class TimeTableStaffCommand {
    private String staffId;
    private List<String> dayOne;
    private List<String> dayTwo;
    private List<String> dayThree;
    private List<String> dayFour;
    private List<String> dayFive;
}