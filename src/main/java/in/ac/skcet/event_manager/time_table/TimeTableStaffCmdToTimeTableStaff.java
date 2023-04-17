package in.ac.skcet.event_manager.time_table;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TimeTableStaffCmdToTimeTableStaff implements Converter<TimeTableStaffCommand, TimeTableStaff> {
    private TeacherService teacherService;

    @Override
    public TimeTableStaff convert(TimeTableStaffCommand value) {
        try {
            return TimeTableStaff.builder()
                    .staff(teacherService.findById(value.getStaffId()))
                    .dayOne(value.getDayOne())
                    .dayTwo(value.getDayTwo())
                    .dayThree(value.getDayThree())
                    .dayFour(value.getDayFour())
                    .dayFive(value.getDayFive())
                    .build();
        } catch (TeacherNotFoundException e) {
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
