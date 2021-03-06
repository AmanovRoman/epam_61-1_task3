package com.epam.spring.hometask.service.impl;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.service.AuditoriumService;
import com.epam.spring.hometask.dao.AuditoriumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Roman_Amanov
 */

@Service
public class AuditoriumServiceImpl implements AuditoriumService {
    @Autowired
    ApplicationContext context;

    @Autowired
    AuditoriumDao auditoriumDao;

    public AuditoriumServiceImpl() {
    }

    @Override
    public Auditorium getAuditoriumById(int id) {
        return auditoriumDao.getById(id);
    }

    @Override
    public List<Auditorium> getAllAuditoriums() {
        return auditoriumDao.getAll();
    }

    @Override
    public Set<Integer> getSeats(int audId, List<Integer> seats) {

        Auditorium auditorium = auditoriumDao.getById(audId);
        return seats.
                stream().
                filter(seat -> (seat <= auditorium.getNumberOfSeats()) && (seat > 0)).
                collect(Collectors.toSet());
    }

    @Override
    public int addNewAuditorium(String name, Integer numberOfSeats, Set<Integer> vipSeats, Double vipSeatsMultiplier) {
        return auditoriumDao.save(new Auditorium(name, numberOfSeats, vipSeats, vipSeatsMultiplier));
    }

    @Override
    public int addNewAuditorium(Auditorium auditorium) {
        return auditoriumDao.save(auditorium);
    }

}
