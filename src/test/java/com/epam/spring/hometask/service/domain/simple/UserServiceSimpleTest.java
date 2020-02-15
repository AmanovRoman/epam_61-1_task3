package com.epam.spring.hometask.service.domain.simple;

import com.epam.spring.hometask.data.FakeDatabase;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.enums.UserType;
import com.epam.spring.hometask.service.domain.UserDao;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(locations = "/spring.xml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceSimpleTest  {

    @Autowired
    private UserDao userService;

    @BeforeAll
    void init() {

        User user1 = new User("Vasya", "Pupkin", "vasya_pupkin@email.com", UserType.VISITOR.getType());
        User user2 = new User("Admin", "Adminych", "megaadmin@email.com", UserType.ADMIN.getType());
        FakeDatabase.getDatabase().getUsers().put(user1.getId(), user1);
        FakeDatabase.getDatabase().getUsers().put(user2.getId(), user2);
    }

    @Test
    void getUserByEmailTest() {
        assertNotNull(userService.getUserByEmail("vasya_pupkin@email.com"));

    }

    @Test
    @Order(1)
    void saveTest() {
        assertEquals(3, userService.save(new User("Vasiliy", "Alibabayevich", "alibaba1857@mail.ru", UserType.VISITOR.getType())));
    }

    @Order(2)
    @Test
    void removeTest() {
        assertNotNull(userService.remove(3));
        userService.getAll().forEach(System.out::println);
    }

    @Test
    void getByIdTest() {
        assertEquals("Admin", userService.getById(2).getFirstName());
    }

    @Test
    @Order(5)
    void getAllTest() {
        userService.getAll().forEach(System.out::println);
        assertEquals(2, userService.getAll().size());
    }
}