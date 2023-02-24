package in.ac.skcet.event_manager.commands;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import in.ac.skcet.event_manager.exception.studentnotfoundexception;
import in.ac.skcet.event_manager.models.OnDutyForm;
import in.ac.skcet.event_manager.models.Student;
import in.ac.skcet.event_manager.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

@Component
@AllArgsConstructor
public class OnDutyFormCommandToOnDutyForm implements Converter<OnDutyFormCommand, OnDutyForm> {
    StudentService studentService;

    @Override
    public OnDutyForm convert(OnDutyFormCommand onDutyFormCommand) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd z hh:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        Set<Student> studentSet = new HashSet<>();

        onDutyFormCommand.getStudentRollNoList().forEach(rollNo -> {
            try {
                studentSet.add(studentService.findByID(rollNo));
            } catch (studentnotfoundexception e) {
                throw new RuntimeException(e);
            }
        });
        try {
            return OnDutyForm.builder()
                    .description(onDutyFormCommand.getDescription())
                    .studentSet(studentSet)
                    .fromDate(simpleDateFormat.parse(onDutyFormCommand.getFromDate()))
                    .endDate(simpleDateFormat.parse(onDutyFormCommand.getEndDate()))
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
