package in.ac.skcet.event_manager.commands;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import in.ac.skcet.event_manager.models.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class EventCmdToEvent implements Converter<EventCommand, Event> {
    @Override
    public Event convert(EventCommand eventCommand) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd z hh:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));

        if(eventCommand == null)
            return null;
        try {
            return Event.builder()
                    .classCodes(eventCommand.getClassCode())
                    .description(eventCommand.getDescription())
                    .fromDate(simpleDateFormat.parse(eventCommand.getFromDate()))
                    .endDate(simpleDateFormat.parse( eventCommand.getEndDate() )).build();
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
