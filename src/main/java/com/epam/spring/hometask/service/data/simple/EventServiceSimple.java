package com.epam.spring.hometask.service.data.simple;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.service.repository.EventServiceDao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Roman_Amanov
 *
 * @see com.epam.spring.hometask.service.repository.EventServiceDao
 */

public class EventServiceSimple extends DBconnector implements EventServiceDao {

    public EventServiceSimple() {
    }

    public EventServiceSimple(Event... events) {
        for (Event event : events) {
            save(event);
        }
    }

    @Override
    public Event getByName(String name) {
        return getConnection().
                getEvents().
                entrySet().
                stream().
                filter(eventEntry -> eventEntry.getValue().getName().equals(name)).
                findFirst().get().getValue();
    }

    @Override
    public ScheduledEvents scheduleEvent(ScheduledEvents scheduledEvent) {
        scheduledEvent.setId(ScheduledEventIdHolder.getId());
        getConnection().getScheduledEvents().put(scheduledEvent.getId(), scheduledEvent);
        return scheduledEvent;
    }

    @Override
    public List<ScheduledEvents> getScheduledByEvent(int eventId) {
        return getConnection().
                getScheduledEvents().
                values().
                stream().filter(scheduled -> scheduled.getEventId() == eventId).
                collect(Collectors.toList());
    }

    @Override
    public ScheduledEvents getScheduledByEventAndDate(int eventId, LocalDateTime time) {
        return getConnection().
                getScheduledEvents().
                values().
                stream().
                filter(scheduler -> (scheduler.getEventId() == eventId) && (scheduler.getEventTime().equals(time))).
                findFirst().get();
    }

    @Override
    public ScheduledEvents getScheduledById(int scheduledId) {
        return getConnection().getScheduledEvents().get(scheduledId);
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

    private static class ScheduledEventIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }
}
