package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Roman_Amanov
 *
 * Every Nth ticket strategy. Works when ticket ID multiple to condition
 */
@Component
public class TicketsEveryNthStrategy extends AbstractStrategy implements DiscountStrategy {
    @Value("${discount.tickets.everyNth.rule}")
    private int condition;

    public TicketsEveryNthStrategy(@Value("${discount.tickets.everyNth.value}") double discountValue) {
        super(discountValue, "Every Nth ticket discount");
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        setUser(user);
        if (ticket.getId() % condition == 0)
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
        if (!(o instanceof TicketsEveryNthStrategy)) return false;
        TicketsEveryNthStrategy that = (TicketsEveryNthStrategy) o;
        return this.getStrategyName().equals(that.getStrategyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStrategyName());
    }
}
