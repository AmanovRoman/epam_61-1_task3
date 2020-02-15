package com.epam.spring.hometask.service.domain.simple;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.service.domain.ScheduleEventDao;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman_Amanov
 */

@Component
@Lazy

public class ScheduleEventDaoImpl extends DBconnector implements ScheduleEventDao {
    @Override
    public int save(ScheduledEvents scheduledEvent) {
        scheduledEvent.setId(ScheduledEventIdHolder.getId());
        getConnection().getScheduledEvents().put(scheduledEvent.getId(), scheduledEvent);
        return scheduledEvent.getId();
    }

    @Override
    public ScheduledEvents remove(int id) {
        return getConnection().getScheduledEvents().remove(id);
    }

    @Override
    public ScheduledEvents getById(int id) {
        return getConnection().getScheduledEvents().get(id);
    }

    @Override
    public List<ScheduledEvents> getAll() {
        return new ArrayList<>(getConnection().getScheduledEvents().values());
    }

    private static class ScheduledEventIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }
}
