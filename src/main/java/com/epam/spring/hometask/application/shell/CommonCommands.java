package com.epam.spring.hometask.application.shell;

import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.service.*;
import com.epam.spring.hometask.dao.AuditoriumDao;
import com.epam.spring.hometask.service.utils.InfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author Roman_Amanov
 * <p>
 * Class uses for Common usable controlls
 * Type 'help' in shell to see all commands
 */

@ShellComponent
public class CommonCommands {
    @Autowired
    ApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ScheduledService scheduledService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private AuditoriumDao auditoriumDao;

    @Autowired
    private TicketService ticketService;

    @Autowired
    InfoProvider infoProvider;

    @ShellMethod(value = "Automatic fill database", key = "auto")
    public void autoFill() {

        System.out.println("\nUSERS:\n----------------------------------------");
        userService.setUserBirthday(
                userService.addNewUser("User", "Userovich", "user@mail.to", 2), "1986-02-21");
        userService.getAllUsers().forEach(System.out::println);

        System.out.println("\nAUDITORIUMS:\n----------------------------------------");
        auditoriumService.getAllAuditoriums().forEach(System.out::println);

        System.out.println("\nEVENTS:\n----------------------------------------");
        eventService.addNewEvent("The Hobbit", 11, 3, 1);
        eventService.getAllEvents().forEach(System.out::println);
        eventService.getEventByName("The Hobbit");

        System.out.println("\nSCHEDULED EVENTS:\n----------------------------------------");
        scheduledService.setNewEventSchedule(1, 1, "2020-01-01 20:10", 1.5, 1);
        scheduledService.setNewEventSchedule(1, 2, "2020-01-02 20:10", 1.9, 1);
        scheduledService.getAllScheduled().forEach(System.out::println);

        System.out.println("\nTICKETS:\n----------------------------------------");
        List<Ticket> tickets = ticketService.bookTickets(1, 1, new HashSet<>(Arrays.asList(1, 3, 5, 7)));
        List<Ticket> tickets2 = ticketService.bookTickets(2, 0, new HashSet<>(Arrays.asList(1, 2, 3, 10, 15, 20)));
        List<Ticket> tickets3 = ticketService.bookTickets(2, 0, new HashSet<>(Arrays.asList(8)));
        ticketService.getAllSoldTickets().forEach(System.out::println);
        System.out.println("TOTAL COST (User 1): " + ticketService.getTicketsTotalCost(tickets));
        System.out.println("TOTAL COST (User 2): " + ticketService.getTicketsTotalCost(tickets2));

    }

    @ShellMethod(value = "Show discount information", key = "info discount")
    public String showDiscountInfo() {
        return infoProvider.getDiscountSummary();
    }

    @ShellMethod(value = "Show event information", key = "info event")
    public String showEventInfo() {
        return infoProvider.getCommonSummary();
    }


}
