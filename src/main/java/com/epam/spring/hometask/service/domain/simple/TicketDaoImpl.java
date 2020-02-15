package com.epam.spring.hometask.service.domain.simple;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.*;
import com.epam.spring.hometask.service.business.DiscountServiceDao;
import com.epam.spring.hometask.service.domain.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Roman_Amanov
 *
 * @see TicketDao
 */

@Component
public class TicketDaoImpl extends DBconnector implements TicketDao {
    @Autowired
    private ApplicationContext context;

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
    public Ticket getNewTicket() {
        Ticket ticket = (Ticket) context.getBean("ticket");
        ticket.setId(TicketDaoImpl.TicketIdHolder.getNext());
        return  ticket;
    }

    private static class TicketIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
        public static int getNext() { return counter; }

    }

}
