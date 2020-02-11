package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;

/**
 * @author Roman_Amanov
 *
 * Every Nth ticket strategy. Works when ticket ID multiple to condition
 */
public class TicketsEveryNthStrategy extends AbstractStrategy implements DiscountStrategy {
    private int condition;

    public TicketsEveryNthStrategy(double discountValue, int condition) {
        super(discountValue);
        this.condition = condition;
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        if (ticket.getId() % condition == 0)
            return getDiscountValue();
        return 0;
    }
}
