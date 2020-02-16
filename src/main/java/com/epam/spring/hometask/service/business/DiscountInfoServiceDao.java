package com.epam.spring.hometask.service.business;


import com.epam.spring.hometask.domain.utils.DiscountInformation;

import java.util.List;

/**
 * @author Roman_Amanov
 */

public interface DiscountInfoServiceDao {
    int saveInfo(DiscountInformation var1);

    List<DiscountInformation> findByUserId(int var1);

    List<DiscountInformation> findByStrategyName(String var1);

    default DiscountInformation filterByStrategyName(List<DiscountInformation> list, String name) {
        return (DiscountInformation)list.stream().filter((discountInfo) -> {
            return discountInfo.getStrategyName().equals(name);
        }).findFirst().orElse(null);
    }

    int increaseCounter(DiscountInformation var1);

    String getTextInfo(List<DiscountInformation> var1);
}
