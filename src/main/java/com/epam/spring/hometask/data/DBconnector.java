package com.epam.spring.hometask.data;

import org.springframework.stereotype.Component;

/**
 * @author Roman_Amanov
 * <p>
 * Simple fake DB connection getter
 */
@Component
public class DBconnector {
    public FakeDatabase getConnection() {

        return FakeDatabase.getDatabase();
    }
}
