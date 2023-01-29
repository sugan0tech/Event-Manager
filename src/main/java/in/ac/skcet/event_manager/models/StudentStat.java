package in.ac.skcet.event_manager.models;

import lombok.Data;

@Data
public class StudentStat {

    private String studentRollNo;
    private String name;
    private String mobile;
    private Boolean isCompleted;
    public StudentStat(Student student, Boolean status){
        this.studentRollNo = student.getRollNo();
        this.name = student.getName();
        this.mobile = student.getMobile();
        this.isCompleted = status;
    }

}
