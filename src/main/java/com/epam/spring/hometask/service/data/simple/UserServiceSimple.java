package com.epam.spring.hometask.service.data.simple;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.repository.UserServiceDao;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Roman_Amanov
 *
 * @see com.epam.spring.hometask.service.repository.UserServiceDao
 */
public class UserServiceSimple extends DBconnector implements UserServiceDao {

    @Override
    public User getUserByEmail(String email) {
        return getConnection().getUsers().values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int save(User user) {
        user.setId(UserIdHolder.getId());
        getConnection().getUsers().put(user.getId(), user);
        return user.getId();
    }

    @Override
    public User remove(int userId) {
        return getConnection().getUsers().remove(userId);
    }

    @Override
    public User getById(int id) {
        return getConnection().getUsers().get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(getConnection().getUsers().values());
    }

    private static class UserIdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }
}
