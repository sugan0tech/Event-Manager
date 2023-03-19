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
    public boolean compareCodes(String target, String given){
        if(target.length() < given.length())
            return false;

        if(given.charAt(0) == 'I' && target.charAt(0) == 'I'){

            String[] targetArray = target.split(" ");
            StringBuilder targetWithoutYear = new StringBuilder();
            String[] givenArray = given.split(" ");
            StringBuilder givenWithoutYear = new StringBuilder();

            if(!targetArray[0].equals(givenArray[0]))
                return false;

            for(int i = 1; i < targetArray.length; i++)
                targetWithoutYear.append(targetArray[i]);

            for(int i = 1; i < givenArray.length; i++)
                givenWithoutYear.append(givenArray[i]);

            return targetWithoutYear.toString().contains(givenWithoutYear.toString());
        }

        return target.contains(given);
    }
}
