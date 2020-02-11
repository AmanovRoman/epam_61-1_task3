package com.epam.spring.hometask.service.repository;

import com.epam.spring.hometask.domain.Auditorium;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Yuriy_Tkach
 * @author Roman_Amanov
 */
public interface AuditoriumServiceDao {

    List<Auditorium> getAll();

    Auditorium getById(int id);

    Set<Integer> getSeats (int audId, List<Integer> seats);

    List<Auditorium> getAuditoriesByEvent(int eventId);

    List<Auditorium> getAuditoriesByTime(LocalDateTime time);

    Auditorium getAudByEventAndTime(int eventId, LocalDateTime time);

}
