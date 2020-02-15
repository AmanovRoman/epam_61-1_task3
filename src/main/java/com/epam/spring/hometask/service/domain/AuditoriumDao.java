package com.epam.spring.hometask.service.domain;

import com.epam.spring.hometask.domain.Auditorium;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Roman_Amanov
 */

/**
 * @author Yuriy_Tkach
 * @author Roman_Amanov
 */
public interface AuditoriumDao {

    int save (Auditorium auditorium);

    List<Auditorium> getAll();

    Auditorium getById(int id);

}
