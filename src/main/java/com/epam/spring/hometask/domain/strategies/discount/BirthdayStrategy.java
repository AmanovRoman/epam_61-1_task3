package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Roman_Amanov
 *
 * Birthday startegy. Depend on birthday and before/after days of current date.
 */
public class BirthdayStrategy extends AbstractStrategy implements DiscountStrategy {

    private int beforeAfter;

    public BirthdayStrategy(double discountValue, int daysBeforeAfter) {
        super(discountValue);
        beforeAfter = daysBeforeAfter;
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        if (Objects.isNull(user)) return 0;
        if (user.getBirthDate() == null) return 0;
        LocalDate nowDate = LocalDate.now().minusYears(LocalDate.now().getYear());
        LocalDate bDate = user.getBirthDate().minusYears(user.getBirthDate().getYear());
        LocalDate compare1 = bDate.minusDays(beforeAfter);
        LocalDate compare2 = bDate.plusDays(beforeAfter);
        boolean a1 = compare1.isBefore(nowDate);
        boolean a2 = compare2.isAfter(nowDate);

        if (a1 && a2)
            return getDiscountValue();
        return 0;
    }

}
