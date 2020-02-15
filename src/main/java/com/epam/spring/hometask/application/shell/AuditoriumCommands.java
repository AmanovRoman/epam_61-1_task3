package com.epam.spring.hometask.application.shell;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.service.business.AuditoriumServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author Roman_Amanov
 * <p>
 * Class uses for Auditorium controlls
 * Type 'help' in shell to see all commands
 */

@ShellComponent
public class AuditoriumCommands {

    @Autowired
    AuditoriumServiceDao auditoriumService;

    @ShellMethod(value = "Show whole auditorium list", key = "auds")
    public void showAuditories() {
        System.out.println("\nAUDITORIUMS:\n----------------------------------------");
        auditoriumService.getAllAuditoriums().forEach(System.out::println);
    }

    @ShellMethod(value = "Show auditorium by ID (id)", key = "aud get")
    public Auditorium showAuditoryById(int id) {
        return auditoriumService.getAuditoriumById(id);
    }

}
