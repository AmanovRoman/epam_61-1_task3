package com.epam.spring.hometask.domain;

import java.time.LocalDateTime;

public class ScheduledEvents extends DomainId {
    private int eventId;
    private int auditoriumId;
    private LocalDateTime eventTime;
    private double ticketPriceMultiplier;

    public ScheduledEvents() {
    }

    public ScheduledEvents(int eventId, int auditoriumId, LocalDateTime eventTime, double ticketPriceMultiplier) {
        this.eventId = eventId;
        this.auditoriumId = auditoriumId;
        this.eventTime = eventTime;
        this.ticketPriceMultiplier = ticketPriceMultiplier;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(int auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public double getTicketPriceMultiplier() {
        return ticketPriceMultiplier;
    }

    public void setTicketPriceMultiplier(double ticketPriceMultiplier) {
        this.ticketPriceMultiplier = ticketPriceMultiplier;
    }

    @Override
    public String toString() {
        return "ScheduledEvents{" +
                "Id=" + getId() +
                ", eventId=" + eventId +
                ", auditoriumId=" + auditoriumId +
                ", eventTime=" + eventTime +
                ", ticketPriceMultiplier=" + ticketPriceMultiplier +
                '}';
    }
}
