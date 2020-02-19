package com.epam.spring.hometask.service;


import com.epam.spring.hometask.domain.utils.DiscountInformation;

import java.util.List;
import java.util.Map;

/**
 * @author Roman_Amanov
 */


public interface DiscountInfoService {
    int saveInfo(DiscountInformation info);

    Map<Integer, DiscountInformation> findByStrategyName(String discount);

    default DiscountInformation filterByStrategyName(List<DiscountInformation> list, String name) {
        return (DiscountInformation)list.stream().filter((discountInfo) -> discountInfo.getStrategyName().equals(name)).findFirst().orElse(null);
    }
    int increaseCounter(DiscountInformation info);

    String getTextInfo();
}
