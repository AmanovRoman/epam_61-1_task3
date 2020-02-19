package com.epam.spring.hometask.dao.impl;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Roman_Amanov
 * @see UserDao
 */
@Component
public class UserDaoImpl implements UserDao {

    private DBconnector connector;

    @Autowired
    public UserDaoImpl(DBconnector connector) {
        this.connector = connector;
    }

    @Override
    public User getUserByEmail(String email) {
        return connector.getConnection().getUsers().values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int save(User user) {
        user.setId(UserIdHolder.getId());
        connector.getConnection().getUsers().put(user.getId(), user);
        return user.getId();
    }

    @Override
    public User remove(int userId) {
        return connector.getConnection().getUsers().remove(userId);
    }

    @Override
    public User getById(int id) {
        return connector.getConnection().getUsers().get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(connector.getConnection().getUsers().values());
    }

    private static class UserIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }
}
