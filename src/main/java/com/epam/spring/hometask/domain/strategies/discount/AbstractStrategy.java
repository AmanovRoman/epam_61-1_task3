package com.epam.spring.hometask.domain.strategies.discount;

/**
 * @author Roman_Amanov
 *
 * Common part of strategies, stores discount value
 */
public abstract class AbstractStrategy {
    private double discountValue;

    AbstractStrategy(double discountValue) {
        this.discountValue = discountValue;
    }

    double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

}
