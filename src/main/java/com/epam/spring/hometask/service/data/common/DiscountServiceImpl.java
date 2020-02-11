package com.epam.spring.hometask.service.data.common;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import com.epam.spring.hometask.service.repository.DiscountServiceDao;

import java.util.Comparator;
import java.util.List;


/**
 * @author Roman_Amanov
 *
 * Discount service
 */

public class DiscountServiceImpl implements DiscountServiceDao {
    private List<DiscountStrategy> strategies;

    public DiscountServiceImpl(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public double getDiscount(User user, ScheduledEvents schedule, int numberOfTickets, Ticket ticket) {
        return strategies.
                stream().
                max(Comparator.comparingDouble(
                        o -> o.calculate(user, schedule, numberOfTickets, ticket))
                ).
                get().
                calculate(user, schedule, numberOfTickets, ticket);
    }

}
