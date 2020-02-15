package com.epam.spring.hometask.service.domain.simple;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.service.domain.AuditoriumDao;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Roman_Amanov
 *
 * Implementation of AuditoriumDao
 * @see AuditoriumDao
 */

@Component
public class AuditoriumDaoImpl extends DBconnector implements AuditoriumDao {

    public int save(Auditorium auditorium) {
        auditorium.setId(AuditoriumIdHolder.getId());
        getConnection().getAuditoriums().put(auditorium.getId(), auditorium);
        return auditorium.getId();
    }

    @Override
    public List<Auditorium> getAll() {
        return new ArrayList<>(getConnection().getAuditoriums().values());
    }

    @Override
    public Auditorium getById(int id) {
        return getConnection().getAuditoriums().get(id);
    }



    private static class AuditoriumIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }
}
