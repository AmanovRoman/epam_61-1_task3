package com.epam.spring.hometask.dao.impl;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.dao.ScheduleEventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman_Amanov
 */

@Component
@Lazy

public class ScheduleEventDaoImpl implements ScheduleEventDao {

    private DBconnector connector;

    @Autowired
    public ScheduleEventDaoImpl(DBconnector connector) {
        this.connector = connector;
    }

    @Override
    public int save(ScheduledEvents scheduledEvent) {
        scheduledEvent.setId(ScheduledEventIdHolder.getId());
        connector.getConnection().getScheduledEvents().put(scheduledEvent.getId(), scheduledEvent);
        return scheduledEvent.getId();
    }

    @Override
    public ScheduledEvents remove(int id) {
        return connector.getConnection().getScheduledEvents().remove(id);
    }

    @Override
    public ScheduledEvents getById(int id) {
        return connector.getConnection().getScheduledEvents().get(id);
    }

    @Override
    public List<ScheduledEvents> getAll() {
        return new ArrayList<>(connector.getConnection().getScheduledEvents().values());
    }

    private static class ScheduledEventIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }
}
