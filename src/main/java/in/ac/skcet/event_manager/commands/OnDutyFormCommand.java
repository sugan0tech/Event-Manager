package in.ac.skcet.event_manager.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.*;

@ToString
@AllArgsConstructor
@Data
public class OnDutyFormCommand {
    private String description;
    private byte[] document;
    private final List<String> studentRollNoList = new ArrayList<>();
    private final List<String> mentorNameList = new ArrayList<>();
    private String fromDate;
    private String endDate;
}
