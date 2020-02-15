package com.epam.spring.hometask.service.utils;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.strategies.discount.AbstractStrategy;

import java.util.Map;

/**
 * @author Roman_Amanov
 */

public class InfoProvider extends DBconnector {
    public static String getDiscountSummary() {
        StringBuilder info = new StringBuilder("\nDISCOUNT USAGE SUMMARY:\n---------------------------------------\n");
        getConnection().getDiscountInfo().forEach((key, value) -> {
            info.append(((AbstractStrategy) key).getStrategyName()).
                    append(" (total " + value.getTotatlStrings() + ")").
                    append("\n");
            for (Map.Entry<User, Integer> userIntegerEntry : value.getUserDiscountCounter().entrySet()) {
                User user = userIntegerEntry.getKey();
                info.append("\t").
                        append(user == null ? "Not registered user" : user.getFirstName()).
                        append(" - ").
                        append(userIntegerEntry.getValue()).append("\n");
            }
        });
        return info.toString();
    }

    public static String getCommonSummary() {
        StringBuilder info = new StringBuilder("\nEVENTS COMMON SUMMARY:\n---------------------------------------\n");
        getConnection().getCommonInfo().forEach((key, value) -> {
            info.append("Event - ").append((key).getName()).
                    append("\n");
            info.append("\tEvent access by name count: ").append(value.getAccessedByNameCounter()).append("\n");
            info.append("\tEvent price queried count: ").append(value.getPriceQueriedCounter()).append("\n");
            info.append("\tEvent booked tickets count: ").append(value.getTicketsBookedCounter()).append("\n");
        });
        return info.toString();
    }

}
