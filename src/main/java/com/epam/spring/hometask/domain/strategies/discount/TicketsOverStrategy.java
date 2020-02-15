package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Roman_Amanov
 * <p>
 * Ticket over Nth startegy. Works when amount of ordered tickets by 1 time over than condition
 */

@Component
public class TicketsOverStrategy extends AbstractStrategy implements DiscountStrategy {
    @Value("${discount.tickets.over.rule}")
    private int everyOver;

    public TicketsOverStrategy(@Value("${discount.tickets.over.value}") double discountValue) {
        super(discountValue, "Mass tickets discount");
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        setUser(user);
        if (ticketsAmount > everyOver)
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
        if (!(o instanceof TicketsOverStrategy)) return false;
        TicketsOverStrategy that = (TicketsOverStrategy) o;
        return this.getStrategyName().equals(that.getStrategyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStrategyName());
    }
}
