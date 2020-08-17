package my.side.project.calendarchatbot.models;

import com.google.cloud.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * the event model of firestore
 */
@Getter
@Setter
@Accessors(chain = true)
public class EventDataObject {

    String title;
    String userId;
    Timestamp startTime;
    Timestamp endTime;
    Object createdAt;
    Object updatedAt;
}
