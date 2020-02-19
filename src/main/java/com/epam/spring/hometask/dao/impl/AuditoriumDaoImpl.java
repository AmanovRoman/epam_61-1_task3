package com.epam.spring.hometask.dao.impl;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.dao.AuditoriumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Roman_Amanov
 *
 * Implementation of AuditoriumDao
 * @see AuditoriumDao
 */

@Component
public class AuditoriumDaoImpl implements AuditoriumDao {

    private DBconnector connector;

    @Autowired
    public AuditoriumDaoImpl(DBconnector connector) {
        this.connector = connector;
    }

    public int save(Auditorium auditorium) {
        auditorium.setId(AuditoriumIdHolder.getId());
        connector.getConnection().getAuditoriums().put(auditorium.getId(), auditorium);
        return auditorium.getId();
    }

    @Override
    public List<Auditorium> getAll() {
        return new ArrayList<>(connector.getConnection().getAuditoriums().values());
    }

    @Override
    public Auditorium getById(int id) {
        return connector.getConnection().getAuditoriums().get(id);
    }



    private static class AuditoriumIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }
}
