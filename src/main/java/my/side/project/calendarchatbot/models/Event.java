package my.side.project.calendarchatbot;

import com.google.cloud.Timestamp;

/**
 * the event model of firestore
 */
public class Event {
    String title;
    String userId;
    Timestamp startTime;
    Timestamp endTime;

    Timestamp createdAt;
    Timestamp updatedAt;

}
