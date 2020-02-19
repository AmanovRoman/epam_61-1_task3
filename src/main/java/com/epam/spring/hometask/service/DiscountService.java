package com.epam.spring.hometask.service;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;

/**
 * @author Yuriy_Tkach
 * @author Roman_Amanov
 */
public interface DiscountService {

    DiscountStrategy getDiscount(User user, ScheduledEvents schedule, int numberOfTickets, Ticket ticket);

}
