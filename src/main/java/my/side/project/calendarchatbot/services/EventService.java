package my.side.project.calendarchatbot.services;

import com.google.cloud.Timestamp;
import my.side.project.calendarchatbot.datastores.EventDataStore;
import my.side.project.calendarchatbot.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Nonnull;

@Service
public class EventService {
    EventDataStore dataStore;

    @Autowired
    public EventService(EventDataStore dataStore) throws Exception {
        this.dataStore = dataStore;
    }

    public String create(@Nonnull Event event) throws Exception {
        return dataStore.create(event);
    }

    public Event get(@Nonnull String id) throws Exception {
        return dataStore.get(id);
    }

    public List<Event> queryByUserAndStartTime(@Nonnull String userId,
                                        @Nonnull Timestamp startTime,
                                        @Nonnull Integer limit) throws Exception {
        return dataStore.queryByUserAndStartTime(userId, startTime, limit);
    }

    public void update(@Nonnull String id, @Nonnull Event event) throws Exception {
        dataStore.update(id, event);
    }

    public void delete(@Nonnull String id) throws Exception {
        dataStore.delete(id);
    }
}
