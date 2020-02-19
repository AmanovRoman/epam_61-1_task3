package com.epam.spring.hometask.dao.impl;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.dao.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Roman_Amanov
 * @see TicketDao
 */

@Component
public class TicketDaoImpl implements TicketDao {
    @Autowired
    private ApplicationContext context;

    private DBconnector connector;

    @Autowired
    public TicketDaoImpl(DBconnector connector) {
        this.connector = connector;
    }

    @Override
    public int save(Ticket ticket) {
        ticket.setId(TicketIdHolder.getId());
        connector.getConnection().getTickets().put(ticket.getId(), ticket);
        return ticket.getId();
    }

    @Override
    public Ticket remove(int ticketId) {
        return connector.getConnection().getTickets().remove(ticketId);
    }

    @Override
    public Ticket getById(int id) {
        return connector.getConnection().getTickets().get(id);
    }

    @Override
    public List<Ticket> getAll() {
        return new ArrayList<>(connector.getConnection().getTickets().values());
    }

    @Override
    public Ticket getNewTicket() {
        Ticket ticket = (Ticket) context.getBean("ticket");
        ticket.setId(TicketDaoImpl.TicketIdHolder.getNext());
        return ticket;
    }

    private static class TicketIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }

        public static int getNext() {
            return counter;
        }

    }

}
