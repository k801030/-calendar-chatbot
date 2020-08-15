package my.side.project.calendarchatbot.datastores;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import my.side.project.calendarchatbot.models.Event;
import my.side.project.calendarchatbot.utils.LogLevel;
import my.side.project.calendarchatbot.utils.Output;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

public class EventFirestore implements EventDataStore {

    private static final Output OUTPUT = Output.getOutput(EventDataStore.class.getName());
    private static final Gson GSON = new GsonBuilder().create();
    private static final String COLLECTION_NAME = "events";
    private Firestore db;

    public EventFirestore(@Nonnull Firestore firestore) {
        this.db = firestore;
    }

    @Override
    public String create(@Nonnull Event event) throws IOException {
        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
        OUTPUT.print(LogLevel.DEBUG, "create a id={}", docRef.getId());

        // asynchronously write data
        ApiFuture<WriteResult> future = docRef.set(event);

        // result.get() blocks on response
        try {
            WriteResult result = future.get();
            OUTPUT.print(LogLevel.DEBUG, "Succeed to create data, with updateTime={}",
                         result.getUpdateTime().toString());
            return docRef.getId();
        } catch (Exception e) {
            OUTPUT.print(LogLevel.ERROR, "Failed to create data, message={}", e.getMessage());
            throw new IOException("Failed to create data", e);
        }
    }

    @Override
    public Event get(@Nonnull String id) throws IOException {
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                Event event = document.toObject(Event.class);
                OUTPUT.print(LogLevel.DEBUG, "Document data={}", document.getData().toString());
                return event;
            } else {
                OUTPUT.print(LogLevel.WARN, "No such document");
            }
        } catch (Exception e) {
            OUTPUT.print(LogLevel.ERROR, "Failed to get results, message={}", e.getMessage());
            throw new IOException("Failed to get data", e);
        }
        return null;
    }

    @Override
    public List<Event> queryByUserAndStartTime(@Nonnull String userId, @Nonnull Timestamp startTime,
                                               @Nonnull Integer limit) throws IOException {
        // Create a reference to the events collection
        CollectionReference events = db.collection(COLLECTION_NAME);
        // Create a query against the collection.
        Query query = events
            .whereEqualTo("userId", userId)
            .whereGreaterThanOrEqualTo("startTime", startTime)
            .orderBy("startTime")
            .orderBy("endTime")
            .limit(limit);
        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> future = query.get();

        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            OUTPUT.print(LogLevel.DEBUG, "get total documents: {}", Integer.toString(documents.size()));
            OUTPUT.print(LogLevel.DEBUG, "-----------");
            for (DocumentSnapshot document : documents) {
                OUTPUT.print(LogLevel.DEBUG, document.getData().toString());
            }
            OUTPUT.print(LogLevel.DEBUG, "-----------");
            return documents.stream().map(doc -> doc.toObject(Event.class)).collect(Collectors.toList());
        } catch (Exception e) {
            OUTPUT.print(LogLevel.ERROR, "Failed to query data, message={}", e.getMessage());
            throw new IOException("Failed to query data", e);
        }
    }

    @Override
    public void update(@Nonnull String id, @Nonnull Event event) throws IOException {
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        ApiFuture<WriteResult> future = docRef.update(toMap(event));
        try {
            WriteResult result = future.get();
            OUTPUT.print(LogLevel.DEBUG, "Succeed to update data, with updateTime={}",
                         result.getUpdateTime().toString());
        } catch (Exception e) {
            OUTPUT.print(LogLevel.ERROR, "Failed to update data, message={}", e.getMessage());
            throw new IOException("Failed to update data", e);
        }
    }

    @Override
    public void delete(@Nonnull String id) throws IOException {
        // asynchronously delete a document
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        ApiFuture<WriteResult> future = docRef.delete();
        try {
            WriteResult result = future.get();
            OUTPUT.print(LogLevel.DEBUG, "Succeed to delete data, with updateTime={}",
                         result.getUpdateTime().toString());
        } catch (Exception e) {
            OUTPUT.print(LogLevel.ERROR, "Failed to delete data, message={}", e.getMessage());
            throw new IOException("Failed to delete data", e);
        }
    }

    private static Map<String, Object> toMap(Event event) {
        String json = GSON.toJson(event);
        Map<String, Object> result = new Gson().fromJson(json, Map.class);
        return result;
    }

}
