package com.epam.spring.hometask.service.data.simple;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.service.repository.AuditoriumServiceDao;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Roman_Amanov
 *
 * Implementation of AuditoriumServiceDao
 * @see com.epam.spring.hometask.service.repository.AuditoriumServiceDao
 */

public class AuditoriumServiceSimple extends DBconnector implements AuditoriumServiceDao {

    public AuditoriumServiceSimple(Auditorium... audList) {
        for (Auditorium auditorium : audList) {
            save(auditorium);
        }
    }

    private void save(Auditorium auditorium) {
        auditorium.setId(AuditoriumIdHolder.getId());
        getConnection().getAuditoriums().put(auditorium.getId(), auditorium);
    }

    @Override
    public List<Auditorium> getAll() {
        return new ArrayList<>(getConnection().getAuditoriums().values());
    }

    @Override
    public Auditorium getById(int id) {
        return getConnection().getAuditoriums().get(id);
    }

    @Override
    public Set<Integer> getSeats(int audId, List<Integer> seats) {
        Auditorium auditorium = getConnection().getAuditoriums().get(audId);
        return seats.
                stream().
                filter(seat -> (seat <= auditorium.getNumberOfSeats()) && (seat > 0)).
                collect(Collectors.toSet());
    }

    @Override
    public List<Auditorium> getAuditoriesByEvent(int eventId) {
        List<Auditorium> auditoriumList = new ArrayList<>();
        List<ScheduledEvents> event = getConnection().
                getScheduledEvents().
                values().
                stream().
                filter(events -> events.getEventId() == eventId).
                collect(Collectors.toList());

        event.forEach(events -> auditoriumList.add(getConnection().getAuditoriums().get(events.getAuditoriumId())));
        return auditoriumList;
    }

    @Override
    public List<Auditorium> getAuditoriesByTime(LocalDateTime time) {
        List<Auditorium> auditoriumList = new ArrayList<>();
        List<ScheduledEvents> event = getConnection().
                getScheduledEvents().
                values().
                stream().
                filter(events -> Instant.from(time) == Instant.from(events.getEventTime())).
                collect(Collectors.toList());

        event.forEach(events -> auditoriumList.add(getConnection().getAuditoriums().get(events.getAuditoriumId())));
        return auditoriumList;
    }

    @Override
    public Auditorium getAudByEventAndTime(int eventId, LocalDateTime time) {
        ScheduledEvents event = getConnection().
                getScheduledEvents().
                values().
                stream().
                filter(events -> (events.getEventId() == eventId) && (Instant.from(time) == Instant.from(events.getEventTime()))).
                findFirst().orElse(null);
        if (event == null) return null;
        return getConnection().getAuditoriums().get(event.getAuditoriumId());
    }

    private static class AuditoriumIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }
}
