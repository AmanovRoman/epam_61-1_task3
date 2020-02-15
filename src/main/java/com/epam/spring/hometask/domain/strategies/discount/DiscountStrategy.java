package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;

/**
 * @author Roman_Amanov
 *
 */
public interface DiscountStrategy {
    double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket);
    String getDiscountTitle();
    double getDiscountValue();
    User getLastUser();
}
