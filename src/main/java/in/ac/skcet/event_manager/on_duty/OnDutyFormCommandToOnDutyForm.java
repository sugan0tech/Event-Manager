package in.ac.skcet.event_manager.on_duty;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

@Component
@AllArgsConstructor
@Slf4j
public class OnDutyFormCommandToOnDutyForm implements Converter<OnDutyFormCommand, OnDutyForm> {
    StudentService studentService;

    @Override
    public OnDutyForm convert(OnDutyFormCommand onDutyFormCommand) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd z hh:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));

        Set<String> studentSet = new HashSet<>(onDutyFormCommand.getStudentRollNoList());
        try {
            return OnDutyForm.builder()
                    .description(onDutyFormCommand.getDescription())
                    .document(new Binary(onDutyFormCommand.getDocument().getBytes()))
                    .studentSet(studentSet)
                    .fromDate(simpleDateFormat.parse(onDutyFormCommand.getFromDate()))
                    .endDate(simpleDateFormat.parse(onDutyFormCommand.getEndDate()))
                    .canceledBy(onDutyFormCommand.getCanceledBy())
                    .mentorSet(onDutyFormCommand.getMentorNameList())
                    .signatures(onDutyFormCommand.getSignatures())
                    .canceledBy(onDutyFormCommand.getCanceledBy())
                    .studentSet(onDutyFormCommand.getStudentRollNoList())
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("unable to bind the input file");
        }
        return null;
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
