package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.domain.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Roman_Amanov
 * <p>
 * Event strategy. Works when inbound event eq condition
 */

@Component
public class EventStrategy extends AbstractStrategy implements DiscountStrategy {

    @Value("${discount.events.rule}")
    private int exactValue;

    @Autowired
    private EventDao eventService;

    public EventStrategy(@Value("${discount.events.value}") double discountValue) {
        super(discountValue, "Event discount");
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        Event event = eventService.getById(scheduler.getEventId());
        Event exact = eventService.getById(exactValue);
        setUser(user);
        if (event.equals(exact))
            return getDiscountValue();
        return -1;
    }

    @Override
    public String getDiscountTitle() {
        return this.getStrategyName();
    }

    @Override
    public double getDiscountValue() {
        return super.getDiscountValue();
    }

    @Override
    public User getLastUser() {
        return getUser();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventStrategy)) return false;
        EventStrategy that = (EventStrategy) o;
        return this.getStrategyName().equals(that.getStrategyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStrategyName());
    }
}