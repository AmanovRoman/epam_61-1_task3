package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Roman_Amanov
 *
 */
@Component
@Lazy
public class LuckyWinnerStrategy extends AbstractStrategy implements DiscountStrategy {

    private static LuckyWinnerStrategy instance;

    private LuckyWinnerStrategy() {
        super(100, "Lucky Winner discount");
    }

    public static LuckyWinnerStrategy getInstance() {
        if (instance == null)
            instance = new LuckyWinnerStrategy();
        return instance;
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
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
        if (!(o instanceof LuckyWinnerStrategy)) return false;
        LuckyWinnerStrategy that = (LuckyWinnerStrategy) o;
        return this.getStrategyName().equals(that.getStrategyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStrategyName());
    }
}
