package com.epam.spring.hometask.service.domain;

import com.epam.spring.hometask.domain.Ticket;

/**
 * @author Roman_Amanov
 */

/**
 * @author Roman_Amanov
 */

public interface TicketDao extends AbstractDomainObjectDao<Ticket> {
    Ticket getNewTicket();
}
