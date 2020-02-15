package com.epam.spring.hometask.application.shell;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.service.business.EventServiceDao;
import com.epam.spring.hometask.service.business.ScheduledServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author Roman_Amanov
 * <p>
 * Class uses for Events controlls
 * Type 'help' in shell to see all commands
 */


@ShellComponent
public class EventsCommands {
    @Autowired
    EventServiceDao eventService;

    @Autowired
    ScheduledServiceDao scheduledService;

    @ShellMethod(value = "Show whole events list", key = "events")
    public void showEvents() {
        System.out.println("\nEVENTS:\n----------------------------------------");
        eventService.getAllEvents().forEach(System.out::println);
    }

    @ShellMethod(value = "Show event by ID (id)", key = "event get")
    public Event showEventById(int id) {
        return eventService.getEventById(id);
    }

    @ShellMethod(value = "Show event by name (name)", key = "event search")
    public Event showEventByName(String name) {
        return eventService.getEventByName(name);
    }

    @ShellMethod(value = "New event creation (name, basePrice, eventRating = 1-LOW, 2 - MID, 3 - HIGH, userId)", key = "event new")
    public String newEvent(String name, double basePrice, int rating, int userId) {
        try {
            eventService.addNewEvent(name, basePrice, rating, userId);
        } catch (Exception e) {
            return "User have no permissions for this operation";
        }
        return "New event added";
    }

    @ShellMethod(value = "Schedule event (eventId, auditoryId, time='yyyy-MM-dd HH:mm', multiplier (i.e: 1.3), userId)", key = "event bind")
    public String scheduleEvent(int eventId, int audId, String time, double mult, int userId) {
        try {
            scheduledService.setNewEventSchedule(eventId, audId, time, mult, userId);
        } catch (NullPointerException | IllegalArgumentException e) {
            return "Wrong arguments or User have no permissions for this operation";
        }
        return "New scheduled event";
    }

    @ShellMethod(value = "Show scheduled events by ID (eventId)", key = "scheduled")
    public void showScheduledEvents(int eventId) {
        System.out.println("\nSCHEDULED EVENTS:\n----------------------------------------");
        scheduledService.getAllScheduled().forEach(System.out::println);
    }

}
