package com.epam.spring.hometask.domain;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * @author Yuriy_Tkach
 */
public class Auditorium extends DomainId {

    private String name;

    private long numberOfSeats;

    private Set<Long> vipSeats;

    private double vipSeatsMultiplier;

    public Auditorium(String name, long numberOfSeats, Set<Long> vipSeats, double vipSeatsMultiplier) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
        this.vipSeatsMultiplier = vipSeatsMultiplier;
    }

    public String getName() {
        return name;
    }

    public long getNumberOfSeats() {
        return numberOfSeats;
    }

    public Set<Long> getVipSeats() {
        return vipSeats;
    }

    public double getVipSeatsMultiplier() {
        return vipSeatsMultiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auditorium)) return false;
        Auditorium that = (Auditorium) o;
        return getNumberOfSeats() == that.getNumberOfSeats() &&
                Double.compare(that.getVipSeatsMultiplier(), getVipSeatsMultiplier()) == 0 &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getVipSeats(), that.getVipSeats());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNumberOfSeats(), getVipSeats(), getVipSeatsMultiplier());
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", vipSeats=" + vipSeats +
                '}';
    }
}
