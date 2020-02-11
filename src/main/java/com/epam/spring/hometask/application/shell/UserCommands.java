package com.epam.spring.hometask.application.shell;

import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.repository.UserServiceDao;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import utils.ContextReceiver;

import java.time.LocalDate;

/**
 * @author Roman_Amanov
 *
 * Class uses for User controlls
 * Type 'help' in shell to see all commands
 */

@ShellComponent
public class UserCommands {

    @ShellMethod(value = "Show whole users list", key = "users")
    public static String showUsers() {
        StringBuilder out = new StringBuilder("Registered users:\n-------------------------------\n");
        UserServiceDao userService = (UserServiceDao) ContextReceiver.getContext().getBean("userService");
        for (User user : userService.getAll()) {
            out.append(user).append("\n");
        }
        return out.append("-------------------------------\n").toString();
    }

    @ShellMethod(value = "Create new user (fName, sName, Email, userType = 1-admin, 2-visitor)", key = "user add")
    public static String addUser(String firstName, String lastName, String email, int userType) {
        UserServiceDao userService = (UserServiceDao) ContextReceiver.getContext().getBean("userService");
        User user = new User(firstName, lastName, email, userType);
        userService.save(user);
        return "User added: " + user;
    }

    @ShellMethod(value = "Get user by ID (id)", key = "user get")
    public static String getUserById(int id) {
        UserServiceDao userService = (UserServiceDao) ContextReceiver.getContext().getBean("userService");
        return userService.getById(id).toString();
    }

    @ShellMethod(value = "Get user by email (email)", key = "user search")
    public static User getUserByEmail(String email) {
        UserServiceDao userService = (UserServiceDao) ContextReceiver.getContext().getBean("userService");
        return userService.getUserByEmail(email);
    }

    @ShellMethod(value = "Delete user (id)", key = "user remove")
    public static String userRemove(int id) {
        UserServiceDao userService = (UserServiceDao) ContextReceiver.getContext().getBean("userService");
        return "User removed:" + userService.remove(id);
    }

    @ShellMethod(value = "Add user birthday (userId, birthday='yyyy-MM-dd')", key = "user birth")
    public static User setUserBirthday(int id, String birthday) {
        LocalDate lt = LocalDate.parse(birthday);
        UserServiceDao userService = (UserServiceDao) ContextReceiver.getContext().getBean("userService");
        userService.getById(id).setBirthDate(lt);
        return userService.getById(id);
    }

}
