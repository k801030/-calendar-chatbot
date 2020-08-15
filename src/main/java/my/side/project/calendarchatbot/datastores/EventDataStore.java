package my.side.project.calendarchatbot.datastores;

import com.google.cloud.Timestamp;
import my.side.project.calendarchatbot.models.Event;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnull;

public interface EventDataStore {
    String create(@Nonnull Event event) throws IOException;
    Event get(@Nonnull String id) throws IOException;
    List<Event> queryByUserAndStartTime(@Nonnull String userId, @Nonnull Timestamp startTime, @Nonnull Integer limit) throws IOException;
    void update(@Nonnull String id, @Nonnull Event event)  throws IOException;
    void delete(@Nonnull String id)  throws IOException;
}
