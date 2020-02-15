package com.epam.spring.hometask.application.shell;

import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.repository.AuditoriumServiceDao;
import com.epam.spring.hometask.service.repository.EventServiceDao;
import com.epam.spring.hometask.service.repository.TicketServiceDao;
import com.epam.spring.hometask.service.repository.UserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Roman_Amanov
 *
 * Class uses for Tickets controlls
 * Type 'help' in shell to see all commands
 */


@ShellComponent
public class TicketCommands {

    private static ApplicationContext context;

    @Autowired
    public TicketCommands(ApplicationContext context) {
        TicketCommands.context = context;
    }

    @ShellMethod(value = "Show whole tickets list", key = "tickets")
    public static String showTickets() {
        StringBuilder out = new StringBuilder("Sold tickets:\n-------------------------------\n");
        TicketServiceDao ticketService = (TicketServiceDao) context.getBean("ticketService");
        for (Ticket ticket : ticketService.getAll()) {
            out.append(ticket).append("\n");
        }
        return out.append("-------------------------------\n").toString();
    }


    @ShellMethod(value = "Buy ticket (Registered user) (userId, scheduledEventId, seats[10])", key = "ticket buy_r")
    public static String buyTicketsRegistered(int userId, int scheduledId, @ShellOption(arity=10) Integer[] seats) {
        return getTickets(userId, scheduledId, seats);
    }

    @ShellMethod(value = "Buy ticket (Not registered user) (scheduledEventId, seats[10])", key = "ticket buy")
    public static String buyTickets(int scheduledId, @ShellOption(arity=10) Integer[] seats) {
        return getTickets(0, scheduledId, seats);
    }

    private static String getTickets(int userId, int scheduledId, Integer[] seats) {
        List<Integer> seatList = Arrays.stream(seats).collect(Collectors.toList());
//        System.out.println(seatList);
        TicketServiceDao ticketService = (TicketServiceDao) context.getBean("ticketService");
        AuditoriumServiceDao audService = (AuditoriumServiceDao) context.getBean("auditoriumService");
        EventServiceDao eventService = (EventServiceDao) context.getBean("eventService");
        UserServiceDao userService = (UserServiceDao) context.getBean("userService");

        Set<Integer> seatsForBook = audService.getSeats(
                eventService.getScheduledById(scheduledId).getAuditoriumId(), seatList);

        User user = (userId > 0) ? userService.getById(userId) : null;
        Set<Ticket> ticketsForBook = ticketService.bookTickets(eventService.getScheduledById(scheduledId), user, seatsForBook);
        StringBuilder answer = new StringBuilder("Buyed tickets:\n-------------------------------------\n");
        for (Ticket ticket : ticketsForBook) {
            answer.append(ticket).append("\n");
        }
        answer.append("-----------------------------------\nTotal cost: ").append(ticketService.getTicketsPrice(ticketsForBook));
        return answer.toString();
    }

    // showUserTickets
    @ShellMethod(value = "Show user tickets list by scheduled (userId, scheduledId)", key = "tickets user")
    public static String showUserScheduledTickets(int userId, int scheduledId) {
        StringBuilder out = new StringBuilder();
        TicketServiceDao ticketService = (TicketServiceDao) context.getBean("ticketService");
        UserServiceDao userService = (UserServiceDao) context.getBean("userService");

        List<Ticket> tickets = ticketService.filterTicketsByScheduled(ticketService.getTicketsByUser(userService.getById(userId)), scheduledId);
        double ticketsPrice = ticketService.getTicketsPrice(new HashSet<>(tickets));
        for (Ticket ticket : tickets) {
            out.append(ticket).append("\n");
        }
        out.append("-----------------------------------\n").append("Total cost: ").append(ticketsPrice).append("\n");
        return out.toString();
    }

    @ShellMethod(value = "Show scheduled event tickets count (scheduledEventId)", key = "tickets sum")
    public static String showTicketsByScheduled(int scheduledId) {
        TicketServiceDao ticketService = (TicketServiceDao) context.getBean("ticketService");
        return "Total tickets: " + ticketService.filterTicketsByScheduled(ticketService.getAll(), scheduledId).size();
    }

}
