package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.Ticket;

/**
 * @author Roman_Amanov
 */

public interface TicketDao extends AbstractDomainObjectDao<Ticket> {
    Ticket getNewTicket();
}
