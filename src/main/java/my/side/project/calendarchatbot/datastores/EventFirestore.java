package my.side.project.calendarchatbot;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventFirestore implements EventDataSource {

    private static final Output output = Output.getOutput(EventDataSource.class.getName());
    private Firestore db;

    public EventFirestore(Firestore firestore) {
        this.db = firestore;
    }

    @Override
    public void create() {
        DocumentReference docRef = db.collection("events").document();
        output.print(LogLevel.DEBUG, "create a id={}", docRef.getId());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("title", "A Party");
        data.put("userId", "Vison");

        Date date = new Date();
        Long start = date.getTime();
        Long end = start + 86400 * 1000;

        data.put("startTime", Timestamp.ofTimeMicroseconds(start));
        data.put("endTime", Timestamp.ofTimeMicroseconds(end));

        // asynchronously write data
        ApiFuture<WriteResult> future = docRef.set(data);

        // result.get() blocks on response
        try {
            WriteResult result = future.get();
            output.print(LogLevel.INFO, "Succeed to create a data, with updateTime={}",
                         result.getUpdateTime().toString());
        } catch (Exception e) {
            output.print(LogLevel.ERROR, "Failed to get results, message={}", e.getMessage());
        }
    }

    @Override
    public void get() {
        DocumentReference docRef = db.collection("events").document("vgLHEBazW1SIORz2WaL9");
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                output.print(LogLevel.INFO, "Document data={}", document.getData().toString());
            } else {
                output.print(LogLevel.WARN, "No such document");
            }
        } catch (Exception e) {
            output.print(LogLevel.ERROR, "Failed to get results, message={}", e.getMessage());
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

}
