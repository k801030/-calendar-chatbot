package my.side.project.calendarchatbot;

import static my.side.project.calendarchatbot.utils.LogLevel.ERROR;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import my.side.project.calendarchatbot.datastores.EventDataStore;
import my.side.project.calendarchatbot.datastores.EventFirestore;
import my.side.project.calendarchatbot.datastores.FirestoreFactory;
import my.side.project.calendarchatbot.models.Event;
import my.side.project.calendarchatbot.utils.Output;

import java.io.IOException;
import java.util.Date;

/**
 * This class is used for demonstration
 */
public class Demo {

    private static final Output OUTPUT = Output.getOutput(Demo.class.getName());

    public static void main(String[] args) {
        try {
            Firestore db = FirestoreFactory.create();
            EventDataStore eventDataStore = new EventFirestore(db);

            Date date = new Date();
            Long nowTs = date.getTime();
            Timestamp now = Timestamp.ofTimeMicroseconds(nowTs * 1000);
            Timestamp oneHourLater = Timestamp.ofTimeMicroseconds((nowTs + 3600 * 1000) * 1000);
            Timestamp oneDayLater = Timestamp.ofTimeMicroseconds((nowTs + 86400 * 1000) * 1000);
            Timestamp twoDayLater = Timestamp.ofTimeMicroseconds((nowTs + 2 * 86400 * 1000) * 1000);

            // create
            Event event = new Event()
                .setTitle("A party")
                .setUserId("vison")
                .setStartTime(oneHourLater)
                .setEndTime(oneDayLater)
                .setCreatedAt(FieldValue.serverTimestamp())
                .setUpdatedAt(FieldValue.serverTimestamp());
            String id = eventDataStore.create(event);

            // query with order anㄒㄨㄠd limit

            event.setEndTime(twoDayLater);
            eventDataStore.create(event);
            eventDataStore.queryByUserAndStartTime("vison", now, 5);

            // get
            eventDataStore.get("404-id");
            eventDataStore.get(id);

            // update
            eventDataStore.update(id, new Event().setUserId("not vison"));
            eventDataStore.get(id);

            // delete
            eventDataStore.delete(id);
            eventDataStore.get(id);

        } catch (IOException e) {
            OUTPUT.print(ERROR, "Failed to create firestore, message={}", e.getMessage());
        }
    }
}
