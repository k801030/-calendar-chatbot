package my.side.project.calendarchatbot;

import static my.side.project.calendarchatbot.utils.LogLevel.ERROR;

import my.side.project.calendarchatbot.datastores.EventDataStore;
import my.side.project.calendarchatbot.datastores.EventFirestore;
import my.side.project.calendarchatbot.datastores.FirestoreFactory;
import my.side.project.calendarchatbot.modelmappers.Mapper;
import my.side.project.calendarchatbot.models.Event;
import my.side.project.calendarchatbot.utils.Output;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * This class is used for demonstration
 */
public class Demo {

    private static final Output OUTPUT = Output.getOutput(Demo.class.getName());

    public static void main(String[] args) {
        try {
            EventDataStore eventDataStore = new EventFirestore(
                FirestoreFactory.create(),
                new Mapper()
            );

            DateTime dateTime = DateTime.now();
            String now = dateTime.toString(ISODateTimeFormat.dateTime());
            String oneMinLater = dateTime.plusMinutes(1).toString(ISODateTimeFormat.dateTime());
            String oneHourLater = dateTime.plusHours(1).toString(ISODateTimeFormat.dateTime());
            String oneDayLater = dateTime.plusDays(1).toString(ISODateTimeFormat.dateTime());
            String twoDayLater = dateTime.plusDays(2).toString(ISODateTimeFormat.dateTime());

            // create
            Event event = new Event()
                .setTitle("A party")
                .setUserId("vison")
                .setStartTime(oneMinLater)
                .setEndTime(oneDayLater);
            String id = eventDataStore.create(event);

            // query with order and limit
            event.setEndTime(twoDayLater);
            eventDataStore.create(event);
            eventDataStore.queryByUserAndStartTime("vison", now, 5);

            // get
            eventDataStore.get("404-id");
            eventDataStore.get(id);

            // update
            eventDataStore.create(event);
            eventDataStore.update(id, new Event().setUserId("not vison"));
            eventDataStore.get(id);
            eventDataStore.update(id, new Event().setStartTime(oneDayLater));
            eventDataStore.get(id);

            // delete
            eventDataStore.delete(id);
            eventDataStore.get(id);

        } catch (IOException e) {
            OUTPUT.print(ERROR, "Failed to create firestore, message={}", e.getMessage());
            e.printStackTrace();
        }
    }
}
