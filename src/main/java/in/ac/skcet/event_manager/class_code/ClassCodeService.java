package in.ac.skcet.event_manager.class_code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ClassCodeService {
    private List<String> departments;
    private List<String> years;
    private List<String> sections;
    public ClassCodeService(){
        this.departments = new ArrayList<>(Arrays.asList("CSE", "ECE", "EEE", "AIDS"));
        this.years = new ArrayList<>(Arrays.asList("I", "II", "III", "IV", "V"));
        this.sections = new ArrayList<>(Arrays.asList("A", "B", "C"));
    }
    public boolean compareCodes(String lowPriority, String highPriority){
        if(lowPriority.length() < highPriority.length())
            return false;

        if(highPriority.charAt(0) == 'I' && lowPriority.charAt(0) == 'I'){
            String[] classCodeAStr = lowPriority.split(" ");
            StringBuilder classCodeAWithoutYear = new StringBuilder();
            String[] classCodeBStr = highPriority.split(" ");
            StringBuilder classCodeBWithoutYear = new StringBuilder();
            if(!classCodeAStr[0].equals(classCodeBStr[0]))
                return false;

            for(int i = 1; i < classCodeAStr.length; i++)
                classCodeAWithoutYear.append(classCodeAStr[i]);

            for(int i = 1; i < classCodeBStr.length; i++)
                classCodeBWithoutYear.append(classCodeBStr[i]);

            return classCodeAWithoutYear.toString().contains(classCodeBWithoutYear.toString());
        }

        return lowPriority.contains(highPriority);
    }
}
