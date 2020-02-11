package com.epam.spring.hometask.application.shell;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.service.repository.AuditoriumServiceDao;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import utils.ContextReceiver;

/**
 * @author Roman_Amanov
 *
 * Class uses for Auditorium controlls
 * Type 'help' in shell to see all commands
 */

@ShellComponent
public class AuditoriumCommands {

    @ShellMethod(value = "Show whole auditorium list", key = "auds")
    public static String showAuditories() {
        StringBuilder out = new StringBuilder("Auditoriums:\n-------------------------------\n");
        AuditoriumServiceDao auditoriumService = (AuditoriumServiceDao) ContextReceiver.getContext().getBean("auditoriumService");
        for (Auditorium auditorium : auditoriumService.getAll()) {
            out.append(auditorium).append("\n");
        }
        return out.append("-------------------------------\n").toString();
    }

    @ShellMethod(value = "Show auditorium by ID (id)", key = "aud get")
    public static Auditorium showAuditoryById(int id) {
        AuditoriumServiceDao auditoriumService = (AuditoriumServiceDao) ContextReceiver.getContext().getBean("auditoriumService");
        return auditoriumService.getById(id);
    }

}
