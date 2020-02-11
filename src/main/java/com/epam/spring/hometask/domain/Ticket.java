package com.epam.spring.hometask.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Yuriy_Tkach
 */
public class Ticket extends DomainId {

    private int userId;

    private int scheduledEventId;

    private long seat;

    private double price;

    public Ticket() {
    }

    public Ticket(int userId, int scheduledEventId, long seat) {
        this.userId = userId;
        this.scheduledEventId = scheduledEventId;
        this.seat = seat;
    }

    public long getSeat() {
        return seat;
    }

    public void setSeat(long seat) {
        this.seat = seat;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScheduledEventId() {
        return scheduledEventId;
    }

    public void setScheduledEventId(int scheduledEventId) {
        this.scheduledEventId = scheduledEventId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getUserId() == ticket.getUserId() &&
                getScheduledEventId() == ticket.getScheduledEventId() &&
                getSeat() == ticket.getSeat();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getScheduledEventId(), getSeat());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Id=" + getId() +
                ", userId=" + userId +
                ", scheduledEventId=" + scheduledEventId +
                ", seat=" + seat +
                '}';
}
}
