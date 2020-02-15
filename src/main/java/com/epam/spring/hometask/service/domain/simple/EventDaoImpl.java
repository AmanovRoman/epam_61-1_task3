package com.epam.spring.hometask.service.domain.simple;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.service.domain.EventDao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Roman_Amanov
 *
 * @see EventDao
 */

@Component
public class EventDaoImpl extends DBconnector implements EventDao {

    @Override
    public Event getByName(String name) {
        return getConnection().
                getEvents().
                values().
                stream().
                filter(event1 -> event1.getName().equals(name)).
                findFirst().get();
    }

    @Override
    public int save(Event event) {
        event.setId(EventIdHolder.getId());
        getConnection().getEvents().put(event.getId(), event);
        return event.getId();
    }

    @Override
    public Event remove(int eventId) {
        return getConnection().getEvents().remove(eventId);
    }

    @Override
    public Event getById(int id) {
        return getConnection().getEvents().get(id);
    }

    @Override
    public List<Event> getAll() {
        return new ArrayList<>(getConnection().getEvents().values());
    }

    private static class EventIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }


}
