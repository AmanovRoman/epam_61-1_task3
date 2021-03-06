package com.epam.spring.hometask.domain.utils;

import com.epam.spring.hometask.domain.DomainId;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Roman_Amanov
 */

@Component
@Scope("prototype")
public class DiscountInformation extends DomainId {

    private int userId;
    private int userDiscountCounter;
    private String strategyName;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserDiscountCounter() {
        return this.userDiscountCounter;
    }

    public void setUserDiscountCounter(int userDiscountCounter) {
        this.userDiscountCounter = userDiscountCounter;
    }

    public String getStrategyName() {
        return this.strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

}
