package com.epam.spring.hometask.domain;

import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Yuriy_Tkach
 */
@Component
@Scope("prototype")
public class Ticket extends DomainId {

    private int userId;

    private int scheduledEventId;

    private int seat;

    private double price;

    private String discount;
    private double discountValue;

    public Ticket() {
    }

    public Ticket(int userId, int scheduledEventId, int seat) {
        this.userId = userId;
        this.scheduledEventId = scheduledEventId;
        this.seat = seat;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountStrategy discount) {
        this.discount = discount.getDiscountTitle();
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
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
                " Id=" + getId() +
                ", userId=" + userId +
                ", scheduledEventId=" + scheduledEventId +
                ", seat=" + seat +
                ", price=" + price +
                ", discount='" + discount + '\'' +
                ", discountValue=" + discountValue +
                '}';
    }
}
