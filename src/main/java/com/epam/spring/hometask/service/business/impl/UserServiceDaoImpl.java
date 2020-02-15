package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.enums.UserType;
import com.epam.spring.hometask.service.business.UserServiceDao;
import com.epam.spring.hometask.service.domain.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author Roman_Amanov
 */

@Component
public class UserServiceDaoImpl implements UserServiceDao {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationContext context;

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public int addNewUser(String fName, String sName, String email, int userType) throws IllegalArgumentException{
        UserType type = UserType.values()[userType-1];
        if (type == null)
            throw new IllegalArgumentException("Wrong user type");
        User user = (User) context.getBean("user");
        user.setFirstName(fName);
        user.setLastName(sName);
        user.setEmail(email);
        user.setUserType(userType);
        return addNewUser(user);
    }

    @Override
    public int addNewUser(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public void setUserBirthday(int userId, String birthday) {
        LocalDate localDate = LocalDate.parse(birthday);
        User user = getUserById(userId);
        if (Objects.nonNull(user)) user.setBirthDate(localDate);
    }

    @Override
    public User deleteUserById(int id) {
        return userDao.remove(id);
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getById(userId);
    }
}
