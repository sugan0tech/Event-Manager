package in.ac.skcet.event_manager.teacher;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Staff implements Serializable {
    @Id
    private String staffId;
    private String name;
    private String mail;
    private String mobile;
}