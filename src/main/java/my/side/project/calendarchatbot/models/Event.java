package my.side.project.calendarchatbot.models;

/**
 * the event model of firestore
 */
public class Event {

    String id;
    String title;
    String userId;
    String startTime;
    String endTime;

    public String getId() {
        return id;
    }

    public Event setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Event setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Event setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public Event setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public Event setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }
}
