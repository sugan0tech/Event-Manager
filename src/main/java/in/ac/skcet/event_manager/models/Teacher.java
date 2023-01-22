package in.ac.skcet.event_manager.models;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Teacher extends Staff {
    private String classAndSection;
}
