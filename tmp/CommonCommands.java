package com.epam.spring.hometask.application.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author Roman_Amanov
 *
 * Class uses for Common usable controlls
 * Type 'help' in shell to see all commands
 */

@ShellComponent
public class CommonCommands {
    @ShellMethod(value = "Automatic fill database", key = "prepare")
    public static void autoFill() {
        System.out.println(UserCommands.addUser("Vasiliy", "Pupkin", "vasya_p@yaplakal.ru", 2));
        System.out.println(UserCommands.setUserBirthday(1, "2010-01-01"));
        System.out.println(UserCommands.addUser("Admin", "Adminych", "megapyhar@admins.com", 1));
        System.out.println(EventsCommands.scheduleEvent(2, 3, "2020-01-01 20:10", 1.5, 2));

        System.out.println(TicketCommands.buyTicketsRegistered(1, 1, new Integer[]{1, 3, 5, 7, 9, 0, 0, 0, 0, 0}));
    }
}
