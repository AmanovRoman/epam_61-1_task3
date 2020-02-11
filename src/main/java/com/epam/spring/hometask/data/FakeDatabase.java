package com.epam.spring.hometask.data;

import com.epam.spring.hometask.domain.*;

import java.util.*;

/**
 * @author Roman_Amanov
 *
 * Fake database representation
 */
public class FakeDatabase {

    private Map<Integer, User> users;
    private Map<Integer, Ticket> tickets;
    private Map<Integer, Event> events;
    private Map<Integer, Auditorium> auditoriums;
    private Map<Integer, ScheduledEvents> scheduledEvents;

    private static FakeDatabase db;

    private FakeDatabase() {
        this.users = new HashMap<>();
        this.tickets = new HashMap<>();
        this.events = new HashMap<>();
        this.auditoriums = new HashMap<>();
        this.scheduledEvents = new HashMap<>();
    }

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

}
