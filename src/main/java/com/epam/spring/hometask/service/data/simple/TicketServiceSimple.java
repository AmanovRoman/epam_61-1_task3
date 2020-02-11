package com.epam.spring.hometask.service.data.simple;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.*;
import com.epam.spring.hometask.service.repository.DiscountServiceDao;
import com.epam.spring.hometask.service.repository.TicketServiceDao;
import utils.ContextReceiver;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Roman_Amanov
 *
 * @see com.epam.spring.hometask.service.repository.TicketServiceDao
 */

public class TicketServiceSimple extends DBconnector implements TicketServiceDao {

    @Override
    public int save(Ticket ticket) {
        ticket.setId(TicketIdHolder.getId());
        getConnection().getTickets().put(ticket.getId(), ticket);
        return ticket.getId();
    }

    @Override
    public Ticket remove(int ticketId) {
        return getConnection().getTickets().remove(ticketId);
    }

    @Override
    public Ticket getById(int id) {
        return getConnection().getTickets().get(id);
    }

    @Override
    public List<Ticket> getAll() {
        return new ArrayList<>(getConnection().getTickets().values());
    }

    @Override
    public List<Ticket> getTicketsByUser(User user) {
        return getConnection().getTickets().values().stream().filter(ticket -> ticket.getUserId() == user.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Ticket> filterTicketsByScheduled(List<Ticket> tickets, int scheduledId) {
        return tickets.stream().filter(ticket -> ticket.getScheduledEventId() == scheduledId).collect(Collectors.toList());
    }

    @Override
    public double getTicketsPrice(Set<Ticket> tickets) {
        return tickets.stream().mapToDouble(Ticket::getPrice).sum();
    }

    private double calculateTicketPrice(Ticket ticket, int ticketsAmount) {
        ScheduledEvents scheduledEvent = getConnection().getScheduledEvents().get(ticket.getScheduledEventId());
        Auditorium auditorium = getConnection().getAuditoriums().get(scheduledEvent.getAuditoriumId());

        Event event = getConnection().getEvents().get(scheduledEvent.getEventId());
        double vipMultiplier = (auditorium.getVipSeats().contains(ticket.getSeat()))?auditorium.getVipSeatsMultiplier():1;

        User user = getConnection().getUsers().get(ticket.getUserId());
        DiscountServiceDao discountService = (DiscountServiceDao) ContextReceiver.getContext().getBean("discountService");
        double maxDiscount = discountService.getDiscount(user, scheduledEvent, ticketsAmount, ticket);
        return (event.getBasePrice() * scheduledEvent.getTicketPriceMultiplier() * vipMultiplier) * ((100 - maxDiscount) / 100);
    }

    @Override
    public Set<Ticket> bookTickets(ScheduledEvents scheduledEvent, User user, Set<Integer> seats) {
        Set<Ticket> tickets = new HashSet<>();
        seats.forEach(seat -> {
            Ticket ticket = (Ticket) ContextReceiver.getContext().getBean("ticket");
            ticket.setId(TicketIdHolder.getNext());
            ticket.setUserId(Objects.nonNull(user)?user.getId():0); /******************************************/
            ticket.setSeat(seat);
            ticket.setScheduledEventId(scheduledEvent.getId());
            ticket.setPrice(round(calculateTicketPrice(ticket, seats.size()), 2));
            int id = save(ticket);
            tickets.add(ticket);
        });
        return tickets;
    }

    private static class TicketIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
        public static int getNext() { return counter; }

    }

}
