package in.ac.skcet.event_manager.class_code;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClassCodeService {
    private List<String> departments;
    private List<String> years;
    private List<String> sections;
    public ClassCodeService(){
        this.departments = new ArrayList<>(Arrays.asList("CSE", "ECE", "EEE", "AIDS"));
        this.years = new ArrayList<>(Arrays.asList("I", "II", "III", "IV", "V"));
        this.sections = new ArrayList<>(Arrays.asList("A", "B", "C"));
    }
    public boolean compareCodes(String classCodeA, String classCodeB){
        if(classCodeA.length() < classCodeB.length())
            return false;

        if(classCodeB.charAt(0) == 'I' && classCodeA.charAt(0) == 'I'){
            String[] classCodeAStr = classCodeA.split(" ");
            StringBuilder classCodeAWithoutYear = new StringBuilder();
            String[] classCodeBStr = classCodeB.split(" ");
            StringBuilder classCodeBWithoutYear = new StringBuilder();
            if(!classCodeAStr[0].equals(classCodeBStr[0]))
                return false;

            for(int i = 1; i < classCodeAStr.length; i++)
                classCodeAWithoutYear.append(classCodeAStr[i]);

            for(int i = 1; i < classCodeBStr.length; i++)
                classCodeBWithoutYear.append(classCodeBStr[i]);

            return classCodeAWithoutYear.toString().contains(classCodeBWithoutYear.toString());
        }

        return classCodeA.contains(classCodeB);
    };
}
