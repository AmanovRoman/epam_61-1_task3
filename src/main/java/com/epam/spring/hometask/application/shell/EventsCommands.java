package com.epam.spring.hometask.application.shell;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.enums.UserType;
import com.epam.spring.hometask.service.repository.EventServiceDao;
import com.epam.spring.hometask.service.repository.UserServiceDao;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import utils.ContextReceiver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Roman_Amanov
 *
 * Class uses for Events controlls
 * Type 'help' in shell to see all commands
 */


@ShellComponent
public class EventsCommands {

    @ShellMethod(value = "Show whole events list", key = "events")
    public static String showEvents() {
        StringBuilder out = new StringBuilder("Registered events:\n-------------------------------\n");
        EventServiceDao eventService = (EventServiceDao) ContextReceiver.getContext().getBean("eventService");
        for (Event event : eventService.getAll()) {
            out.append(event).append("\n");
        }
        return out.append("-------------------------------\n").toString();
    }

    @ShellMethod(value = "Show event by ID (id)", key = "event get")
    public static Event getEventById(int id) {
        EventServiceDao eventService = (EventServiceDao) ContextReceiver.getContext().getBean("eventService");
        return eventService.getById(id);
    }

    @ShellMethod(value = "Show event by name (name)", key = "event search")
    public static Event getEventByName(String name) {
        EventServiceDao eventService = (EventServiceDao) ContextReceiver.getContext().getBean("eventService");
        return eventService.getByName(name);
    }

    @ShellMethod(value = "New event creation (name, basePrice, eventRating = 1-LOW, 2 - MID, 3 - HIGH, userId)", key = "event new")
    public static String newEvent(String name, double basePrice, int rating, int userId) {
        UserServiceDao userService = (UserServiceDao) ContextReceiver.getContext().getBean("userService");

        if (UserType.values()[userService.getById(userId).getUserType()-1] == UserType.ADMIN) {
            EventServiceDao eventService = (EventServiceDao) ContextReceiver.getContext().getBean("eventService");
            Event event = new Event(name, basePrice, rating);
            eventService.save(event);
            return "Event added: " + event;
        }
        return "User have no permissions for this operation";
    }

    @ShellMethod(value = "Remove event (id, userId)", key = "event del")
    public static String removeEvent(int id, int userId) {
        UserServiceDao userService = (UserServiceDao) ContextReceiver.getContext().getBean("userService");
        if (UserType.values()[userService.getById(userId).getUserType()-1] == UserType.ADMIN) {
            EventServiceDao eventService = (EventServiceDao) ContextReceiver.getContext().getBean("eventService");
            Event event = eventService.remove(id);
            return "Deleted: " + event;
        }
        return "User have no permissions for this operation";
    }

    @ShellMethod(value = "Schedule event (eventId, auditoryId, time='yyyy-MM-dd HH:mm', multiplier (i.e: 1.3), userId)", key = "event bind")
    public static String scheduleEvent(int eventId, int audId, String time, double mult, int userId) {
        UserServiceDao userService = (UserServiceDao) ContextReceiver.getContext().getBean("userService");
        if (UserType.values()[userService.getById(userId).getUserType()-1] == UserType.ADMIN) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            EventServiceDao eventService = (EventServiceDao) ContextReceiver.getContext().getBean("eventService");
            return "Event scheduled as: " + eventService.scheduleEvent(new ScheduledEvents(eventId, audId, dateTime, mult));
        }
        return "User have no permissions for this operation";
    }

    @ShellMethod(value = "Show scheduled events by ID (eventId)", key = "scheduled")
    public static String showScheduledEvents(int eventId) {
        StringBuilder out = new StringBuilder();
        EventServiceDao eventService = (EventServiceDao) ContextReceiver.getContext().getBean("eventService");
        for (ScheduledEvents event : eventService.getScheduledByEvent(eventId)) {
            out.append(event).append("\n");
        }
        return out.toString();
    }

}
