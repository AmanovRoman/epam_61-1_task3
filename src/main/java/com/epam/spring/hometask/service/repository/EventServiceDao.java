package com.epam.spring.hometask.service.repository;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yuriy_Tkach
 */
public interface EventServiceDao extends AbstractDomainObjectService<Event> {


    Event getByName(String name);

    ScheduledEvents scheduleEvent(ScheduledEvents scheduledEvents);

    List<ScheduledEvents> getScheduledByEvent(int eventId);

    ScheduledEvents getScheduledByEventAndDate(int eventId, LocalDateTime time);

    ScheduledEvents getScheduledById(int scheduledId);

}
