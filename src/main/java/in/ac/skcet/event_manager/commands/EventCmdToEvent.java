package in.ac.skcet.event_manager.commands;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import in.ac.skcet.event_manager.models.Event;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class EventCmdToEvent implements Converter<EventCommand, Event> {
    @Override
    public Event convert(EventCommand eventCommand) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd z hh:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));

        if(eventCommand == null)
            return null;

        try {
            Date fromDate = simpleDateFormat.parse(eventCommand.getFromDate());
            Date endDate = simpleDateFormat.parse(eventCommand.getEndDate());
            if(endDate.compareTo(fromDate) < 0 || endDate.compareTo(new Date()) < 0)
                return null;
            return Event.builder()
                    .title(eventCommand.getTitle())
                    .classCodes(eventCommand.getClassCode())
                    .description(eventCommand.getDescription())
                    .fromDate(fromDate)
                    .endDate(endDate).build();
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
