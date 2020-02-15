package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.Auditorium;

import java.util.List;
import java.util.Set;

/**
 * @author Roman_Amanov
 */

public interface AuditoriumServiceDao {

    Auditorium getAuditoriumById(int id);

    List<Auditorium> getAllAuditoriums();

    Set<Integer> getSeats(int audId, List<Integer> seats);

    int addNewAuditorium(String name, Integer numberOfSeats, Set<Integer> vipSeats, Double vipSeatsMultiplier);

    int addNewAuditorium(Auditorium auditorium);
}
