package my.side.project.calendarchatbot.modelmappers;

import com.google.cloud.Timestamp;
import my.side.project.calendarchatbot.models.Event;
import my.side.project.calendarchatbot.models.EventDataObject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.modelmapper.ModelMapper;

public class Mapper {

    ModelMapper modelMapper = new ModelMapper();
    DateTimeFormatter parser = ISODateTimeFormat.dateTimeParser();


    public Event toEntity(String id, EventDataObject eventDataObject) {
        Event event = modelMapper.map(eventDataObject, Event.class);
        event.setId(id);
        event.setStartTime(toISODateTime(eventDataObject.getStartTime()));
        event.setEndTime(toISODateTime(eventDataObject.getEndTime()));
        return event;
    }

    public EventDataObject toDataObject(Event event) {
        EventDataObject eventDataObject = modelMapper.map(event, EventDataObject.class);
        eventDataObject.setStartTime(toTimestamp(event.getStartTime()));
        eventDataObject.setEndTime(toTimestamp(event.getEndTime()));
        return eventDataObject;
    }

    public Timestamp toTimestamp(String iso) {
        if (iso == null) {
            return null;
        }
        Long micro = parser.parseDateTime(iso).getMillis() * 1000;
        return Timestamp.ofTimeMicroseconds(micro);
    }

    public String toISODateTime(Timestamp t) {
        if (t == null) {
            return null;
        }
        DateTime dateTime = new DateTime(t.toDate().getTime());
        return dateTime.toString(ISODateTimeFormat.dateTime());
    }
}
