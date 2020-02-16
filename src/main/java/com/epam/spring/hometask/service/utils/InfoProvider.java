package com.epam.spring.hometask.service.utils;

import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.strategies.discount.AbstractStrategy;
import com.epam.spring.hometask.service.business.CommonInfoServiceDao;
import com.epam.spring.hometask.service.business.DiscountInfoServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Roman_Amanov
 */

@Component
public class InfoProvider {

    @Autowired
    static CommonInfoServiceDao commonInfoService;
    @Autowired
    static DiscountInfoServiceDao discountInfoService;

    public static String getDiscountSummary() {
        StringBuilder info = new StringBuilder("\nDISCOUNT USAGE SUMMARY:\n---------------------------------------\n");

        return info.toString();
    }

    public static String getCommonSummary() {
        StringBuilder info = new StringBuilder("\nEVENTS COMMON SUMMARY:\n---------------------------------------\n");
        commonInfoService.getTextInfo(commonInfoService.getAllCommonInformation());
        return info.toString();
    }

}
