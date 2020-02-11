package com.epam.spring.hometask.service.repository;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

/**
 * @author Roman_Amanov
 */

public interface TicketServiceDao extends AbstractDomainObjectService<Ticket> {

    List<Ticket> getTicketsByUser(User user);

    double getTicketsPrice(Set<Ticket> tickets);

    Set<Ticket> bookTickets(ScheduledEvents scheduledEvent, User user, Set<Integer> seats);

    List<Ticket> filterTicketsByScheduled(List<Ticket> tickets, int scheduledId);

    default double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
