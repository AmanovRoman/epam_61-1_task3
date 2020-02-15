package com.epam.spring.hometask.service.business;

/**
 * @author Roman_Amanov
 */

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.enums.UserType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Roman_Amanov
 */

public interface ScheduledServiceDao {

    int setNewEventSchedule(ScheduledEvents scheduledEvents, UserType userType);

    int setNewEventSchedule(int eventId, int auditoriumId, String eventTime, double ticketPriceMultiplier, int userId);

    int setNewEventSchedule(Event event, Auditorium auditorium, LocalDateTime eventTime, double ticketPriceMultiplier, UserType userType);

    List<ScheduledEvents> getScheduledByEventId(int eventId);

    List<ScheduledEvents> getAllScheduled();

    List<ScheduledEvents> getScheduledByDate(LocalDateTime time);

    ScheduledEvents getScheduledById(int scheduledId);

    List<ScheduledEvents> getScheduledByAuditorium(int auditoriumId);

    List<ScheduledEvents> filterByEventId(List<ScheduledEvents> list, int eventId);

    List<ScheduledEvents> filterByDate(List<ScheduledEvents> list, LocalDateTime dateTime);

    List<ScheduledEvents> filterByAuditorium(List<ScheduledEvents> list, int auditoriumId);
}
