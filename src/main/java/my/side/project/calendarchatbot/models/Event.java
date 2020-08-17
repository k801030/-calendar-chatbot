package my.side.project.calendarchatbot.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * the event model of firestore
 */
@Getter
@Setter
@Accessors(chain = true)
public class Event {
    String id;
    String title;
    String userId;
    String startTime;
    String endTime;
}
