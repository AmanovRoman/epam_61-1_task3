package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Roman_Amanov
 *
 * Birthday startegy. Depend on birthday and before/after days of current date.
 */

@Component
public class BirthdayStrategy extends AbstractStrategy implements DiscountStrategy {

    @Value("${discount.birthday.rule}")
    private int beforeAfter;

    public BirthdayStrategy(@Value("${discount.birthday.value}") double discountValue) {
        super(discountValue, "Birthday discount");
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        if (Objects.isNull(user)) return -1;
        if (user.getBirthDate() == null) return -1;
        setUser(user);
        LocalDate nowDate = LocalDate.now().minusYears(LocalDate.now().getYear());
        LocalDate bDate = user.getBirthDate().minusYears(user.getBirthDate().getYear());
        LocalDate compare1 = bDate.minusDays(beforeAfter);
        LocalDate compare2 = bDate.plusDays(beforeAfter);
        boolean a1 = compare1.isBefore(nowDate);
        boolean a2 = compare2.isAfter(nowDate);

        if (a1 && a2)
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
        if (!(o instanceof BirthdayStrategy)) return false;
        BirthdayStrategy that = (BirthdayStrategy) o;
        return this.getStrategyName().equals(that.getStrategyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStrategyName());
    }
}
