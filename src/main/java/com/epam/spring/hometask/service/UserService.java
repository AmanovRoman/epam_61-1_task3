package com.epam.spring.hometask.service;

import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.enums.UserType;

import java.util.List;

/**
 * @author Roman_Amanov
 */

public interface UserService {
    User getUserByEmail(String email);

    User getUserById(int userId);

    int addNewUser (String fName, String sName, String email, int userType);

    int addNewUser(User user);

    List<User> getAllUsers();

    void setUserBirthday(int userId, String birthday);

    User deleteUserById(int id);
}
