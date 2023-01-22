package in.ac.skcet.event_manager.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    private String rollNo;
    private String name;
    private String classCode;
    private Date dateOfBirth;
    private boolean isHosteler;
    private String mail;
    private String mobile;
}
