package com.epam.spring.hometask.dao.impl;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.dao.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Roman_Amanov
 * @see EventDao
 */

@Component
public class EventDaoImpl implements EventDao {
    private DBconnector connector;

    @Autowired
    public EventDaoImpl(DBconnector connector) {
        this.connector = connector;
    }

    @Override
    public Event getByName(String name) {
        return connector.getConnection().
                getEvents().
                values().
                stream().
                filter(event1 -> event1.getName().equals(name)).
                findFirst().get();
    }

    @Override
    public int save(Event event) {
        event.setId(EventIdHolder.getId());
        connector.getConnection().getEvents().put(event.getId(), event);
        return event.getId();
    }

    @Override
    public Event remove(int eventId) {
        return connector.getConnection().getEvents().remove(eventId);
    }

    @Override
    public Event getById(int id) {
        return connector.getConnection().getEvents().get(id);
    }

    @Override
    public List<Event> getAll() {
        return new ArrayList<>(connector.getConnection().getEvents().values());
    }

    private static class EventIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }


}
