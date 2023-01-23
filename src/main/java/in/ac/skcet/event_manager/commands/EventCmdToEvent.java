package in.ac.skcet.event_manager.commands;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import in.ac.skcet.event_manager.models.Event;

import java.util.Date;

public class EventCmdToEvent implements Converter<EventCommand, Event> {
    @Override
    public Event convert(EventCommand eventCommand) {
        if(eventCommand == null)
            return null;
        return Event.builder()
                .eventId(eventCommand.getEventId())
                .classCodes(eventCommand.getClassCode())
                .description(eventCommand.getDescription())
                .fromDate(new Date())
                .endDate(new Date()).build();
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
