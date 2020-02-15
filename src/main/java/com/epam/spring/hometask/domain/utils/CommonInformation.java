package com.epam.spring.hometask.domain.utils;

import com.epam.spring.hometask.domain.Event;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Roman_Amanov
 */

@Component
@Scope("prototype")
public class CommonInformation {
    private Event event;
    private int accessedByNameCounter = 0;
    private int priceQueriedCounter = 0;
    private int ticketsBookedCounter = 0;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void increaseAccessedByName() {
        accessedByNameCounter++;
    }

    public void increasePriceQueried() {
        priceQueriedCounter++;
    }

    public void increaseTicketBooked() {
        ticketsBookedCounter++;
    }


    public int getAccessedByNameCounter() {
        return accessedByNameCounter;
    }

    public int getPriceQueriedCounter() {
        return priceQueriedCounter;
    }

    public int getTicketsBookedCounter() {
        return ticketsBookedCounter;
    }
}
