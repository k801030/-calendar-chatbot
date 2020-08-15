package my.side.project.calendarchatbot.models;

import com.google.cloud.Timestamp;

/**
 * the event model of firestore
 */
public class EventDataObject {

    String title;
    String userId;
    Timestamp startTime;
    Timestamp endTime;
    Object createdAt;
    Object updatedAt;

    public String getTitle() {
        return title;
    }

    public EventDataObject setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public EventDataObject setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public EventDataObject setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public EventDataObject setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public EventDataObject setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public EventDataObject setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
