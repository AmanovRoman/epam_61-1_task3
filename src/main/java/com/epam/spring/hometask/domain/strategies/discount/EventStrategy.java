package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.repository.EventServiceDao;
import utils.ContextReceiver;

/**
 * @author Roman_Amanov
 *
 * Event strategy. Works when inbound event eq condition
 */
public class EventStrategy extends AbstractStrategy implements DiscountStrategy {
    private int exactValue;

    public EventStrategy(double discountValue, int eventId) {
        super(discountValue);
        exactValue = eventId;
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        EventServiceDao eventService = (EventServiceDao) ContextReceiver.getContext().getBean("eventService");
        Event event = eventService.getById(scheduler.getEventId());
        Event exact = eventService.getById(exactValue);
        if (event.equals(exact))
            return getDiscountValue();
        return 0;
    }
}