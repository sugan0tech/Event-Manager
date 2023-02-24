package in.ac.skcet.event_manager.teacher;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HOD extends Staff {
    private String classCode;

    @Builder
    public HOD(String staffId, String name, String mail, String mobile, String classCode) {
        super(staffId, name, mail, mobile);
        this.classCode = classCode;
    }

}
