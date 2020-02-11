package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;

/**
 * @author Roman_Amanov
 *
 * Ticket over Nth startegy. Works when amount of ordered tickets by 1 time over than condition
 */

public class TicketsOverStrategy extends AbstractStrategy implements DiscountStrategy {
    private int everyOver;

    public TicketsOverStrategy(double discountValue, int over) {
        super(discountValue);
        everyOver = over;
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        if (ticketsAmount > everyOver)
            return getDiscountValue();
        return 0;

    }
}
