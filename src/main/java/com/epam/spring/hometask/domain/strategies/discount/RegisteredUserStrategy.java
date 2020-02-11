package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;

import java.util.Objects;

/**
 * @author Roman_Amanov
 *
 *Registered user strategy. Works only if user is registered
 */
public class RegisteredUserStrategy extends AbstractStrategy implements DiscountStrategy {

    public RegisteredUserStrategy(double discountValue) {
        super(discountValue);
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        if (Objects.isNull(user)) return 0;
        return getDiscountValue();
    }

}
