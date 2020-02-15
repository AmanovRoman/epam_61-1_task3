package com.epam.spring.hometask.data;

/**
 * @author Roman_Amanov
 * <p>
 * Simple fake DB connection getter
 */

public class DBconnector {
    public static FakeDatabase getConnection() {

        return FakeDatabase.getDatabase();
    }
}
