package com.epam.spring.hometask.service.business;


import com.epam.spring.hometask.domain.utils.DiscountInformation;

import java.util.List;
import java.util.Map;

/**
 * @author Roman_Amanov
 */

public interface DiscountInfoServiceDao {
    int saveInfo(DiscountInformation var1);

    Map<Integer, DiscountInformation> findByStrategyName(String var1);

    default DiscountInformation filterByStrategyName(List<DiscountInformation> list, String name) {
        return (DiscountInformation)list.stream().filter((discountInfo) -> {
            return discountInfo.getStrategyName().equals(name);
        }).findFirst().orElse(null);
    }

    int increaseCounter(DiscountInformation info);

    String getTextInfo();
}
