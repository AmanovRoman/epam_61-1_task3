package com.epam.spring.hometask.data;

import com.epam.spring.hometask.domain.*;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import com.epam.spring.hometask.domain.utils.CommonInformation;
import com.epam.spring.hometask.domain.utils.DiscountInformation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Roman_Amanov
 * <p>
 * Fake database representation
 */

@Component
public class FakeDatabase {

    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Ticket> tickets = new HashMap<>();
    private Map<Integer, Event> events = new HashMap<>();
    private Map<Integer, Auditorium> auditoriums = new HashMap<>();
    private Map<Integer, ScheduledEvents> scheduledEvents = new HashMap<>();
    private Map<Integer, CommonInformation> commonInfo = new HashMap<>();
    private Map<String, Map<Integer, DiscountInformation>> discountInfo = new HashMap<>();
    private static FakeDatabase db;

    private FakeDatabase() {
    }

    @PostConstruct
    private void init() {
        db = new FakeDatabase();
    }

    public static FakeDatabase getDatabase() {
        return db;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Map<Integer, Ticket> getTickets() {
        return tickets;
    }

    public Map<Integer, Event> getEvents() {
        return events;
    }

    public Map<Integer, Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public Map<Integer, ScheduledEvents> getScheduledEvents() {
        return scheduledEvents;
    }

    public Map<Integer, CommonInformation> getCommonInfo() {
        return commonInfo;
    }

    public Map<String, Map<Integer, DiscountInformation>> getDiscountInfo() {
        return discountInfo;
    }
}
