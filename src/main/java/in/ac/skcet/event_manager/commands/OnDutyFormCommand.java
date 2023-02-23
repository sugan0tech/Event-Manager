package in.ac.skcet.event_manager.commands;

import in.ac.skcet.event_manager.models.Staff;
import in.ac.skcet.event_manager.models.Student;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OnDutyFormCommand {
    private String description;
    private byte[] document;
    private Set<String> studentRollNoSet = new HashSet<>();
    private Set<String> mentorNameSet = new HashSet<>();
    private Date fromDate;
    private Date endDate;
}
