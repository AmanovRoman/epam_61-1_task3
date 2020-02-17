package com.epam.spring.hometask.service.business.impl;


import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.utils.DiscountInformation;
import com.epam.spring.hometask.service.business.DiscountInfoServiceDao;
import com.epam.spring.hometask.service.business.UserServiceDao;
import com.epam.spring.hometask.service.domain.DiscountInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Roman_Amanov
 */

@Repository
public class DiscountInfoServiceImpl implements DiscountInfoServiceDao {
    @Autowired
    DiscountInfoDao discountInfo;

    @Autowired
    UserServiceDao userService;

    @Override
    public int saveInfo(DiscountInformation info) {
        return discountInfo.save(info);
    }

    @Override
    public Map<Integer, DiscountInformation> findByStrategyName(String name) {
        return discountInfo.getByDiscountName(name);
    }

    @Override
    public int increaseCounter(DiscountInformation info) {
        info.setUserDiscountCounter(info.getUserDiscountCounter()+1);
        discountInfo.update(info);
        return info.getUserDiscountCounter();
    }

    @Override
    public String getTextInfo() {
        StringBuilder text = new StringBuilder();
        discountInfo.getAllDiscountNames().forEach(discountName -> {
            text.append(discountName).append(" (total ").append(discountInfo.countByName(discountName)).append(")").
                    append("\n");
            for (DiscountInformation value : discountInfo.getByDiscountName(discountName).values()) {
                User user = userService.getUserById(value.getUserId());
                text.append("\t").
                        append(user == null ? "Not registered user" : user.getFirstName()).
                        append(" - ").
                        append(value.getUserDiscountCounter()).append("\n");
            }
        });
        return text.toString();
    }
}
