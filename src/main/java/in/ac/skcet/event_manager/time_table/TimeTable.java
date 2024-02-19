package in.ac.skcet.event_manager.time_table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTable {
    /**
     * should reference single class i.e III CSE C ( No the Mapper Ones )
     */
    @Id
    private String classCode;

    private List<String> dayOne;
    private List<String> dayTwo;
    private List<String> dayThree;
    private List<String> dayFour;
    private List<String> dayFive;
}
