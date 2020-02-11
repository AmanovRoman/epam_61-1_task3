package com.epam.spring.hometask.service.repository;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;

/**
 * @author Yuriy_Tkach
 * @author Roman_Amanov
 */
public interface DiscountServiceDao {

    double getDiscount(User user, ScheduledEvents schedule, int numberOfTickets, Ticket ticket);

}
