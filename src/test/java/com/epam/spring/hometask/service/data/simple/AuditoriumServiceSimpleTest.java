package com.epam.spring.hometask.service.data.simple;

import com.epam.spring.hometask.service.repository.AuditoriumServiceDao;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(locations = "/spring.xml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuditoriumServiceSimpleTest {

    @Autowired
    AuditoriumServiceDao auditoriumService;

    @BeforeAll
    void init() {
    }

    @Test
    void getByIdTest() {
        assertNotNull(auditoriumService.getById(2));
    }

    @Test
    void getAuditoriesByEventTest() {

    }

    @Test
    void getAuditoriesByTimeTest() {
    }

    @Test
    void getAudByEventAndTimeTest() {

    }

}