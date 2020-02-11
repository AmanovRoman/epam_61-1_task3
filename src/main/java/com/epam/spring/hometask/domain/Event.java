package com.epam.spring.hometask.domain;

import com.epam.spring.hometask.domain.enums.EventRating;

import java.util.Objects;

/**
 * @author Yuriy_Tkach
 */
public class Event extends DomainId {

    private String name;

    private double basePrice;

    private int rating;

    public Event() {
    }

    public Event(String name, double basePrice, int eventRating) {
        this.name = name;
        this.basePrice = basePrice;
        this.rating = eventRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Double.compare(event.getBasePrice(), getBasePrice()) == 0 &&
                getRating() == event.getRating() &&
                Objects.equals(getName(), event.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBasePrice(), getRating());
    }

    @Override
    public String toString() {
        return "Event{ id=" + getId() +
                ", name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", rating=" + EventRating.values()[rating-1] +
                '}';
    }
}
