package in.ac.skcet.event_manager.teacher;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Teacher extends Staff {
    private String classCode;

    @Builder
    public Teacher(String staffId, String name, String mail, String mobile, String classCode, boolean present) {
        super(staffId, name, mail, mobile, present);
        this.classCode = classCode;
    }

}
