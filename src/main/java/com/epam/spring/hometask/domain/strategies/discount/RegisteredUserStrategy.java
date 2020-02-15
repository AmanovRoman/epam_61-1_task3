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
 *Registered user strategy. Works only if user is registered
 */
@Component
public class RegisteredUserStrategy extends AbstractStrategy implements DiscountStrategy {

    private int hashValue = 67132864;
    public RegisteredUserStrategy(@Value("${discount.registeredUser.value}") double discountValue) {
        super(discountValue, "Registered user discount");
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        if (Objects.isNull(user)) return -1;
        setUser(user);
        return getDiscountValue();
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
        if (!(o instanceof RegisteredUserStrategy)) return false;
        RegisteredUserStrategy that = (RegisteredUserStrategy) o;
        return this.getStrategyName().equals(that.getStrategyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStrategyName());
    }
}
