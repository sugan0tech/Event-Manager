package in.ac.skcet.event_manager.time_table;

import in.ac.skcet.event_manager.teacher.Staff;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class TimeTableStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Staff staff;

    @ElementCollection
    private List<String> dayOne = new ArrayList<>(Arrays.asList("Free","Free","Free","Free","Free","Free","Free"));
    @ElementCollection
    private List<String> dayTwo = new ArrayList<>(Arrays.asList("Free","Free","Free","Free","Free","Free","Free"));
    @ElementCollection
    private List<String> dayThree = new ArrayList<>(Arrays.asList("Free","Free","Free","Free","Free","Free","Free"));
    @ElementCollection
    private List<String> dayFour = new ArrayList<>(Arrays.asList("Free","Free","Free","Free","Free","Free","Free"));
    @ElementCollection
    private List<String> dayFive = new ArrayList<>(Arrays.asList("Free","Free","Free","Free","Free","Free","Free"));


}
