package com.epam.spring.hometask.domain.utils;

import com.epam.spring.hometask.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Roman_Amanov
 */

@Component
@Scope("prototype")
public class DiscountInformation {

    private Map<User, Integer> userDiscountCounter = new HashMap<>();

    public Integer getUserDiscountCounter(User user) {
        return userDiscountCounter.getOrDefault(user, 0);
    }

    public Integer increaseUserCounter(User user) {
        Integer counter = userDiscountCounter.getOrDefault(user, 0);
        counter++;
        userDiscountCounter.put(user, counter);
        return counter;
    }

    public Map<User, Integer> getUserDiscountCounter() {
        return userDiscountCounter;
    }

    public int getTotatlStrings() {
        return userDiscountCounter.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
    }

}
