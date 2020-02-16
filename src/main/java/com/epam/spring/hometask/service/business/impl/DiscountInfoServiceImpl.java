package com.epam.spring.hometask.service.business.impl;


import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.strategies.discount.AbstractStrategy;
import com.epam.spring.hometask.domain.utils.DiscountInformation;
import com.epam.spring.hometask.service.business.DiscountInfoServiceDao;
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

    public int saveInfo(DiscountInformation info) {
        return discountInfo.save(info);
    }

    public List<DiscountInformation> findByUserId(int userId) {
        return discountInfo.getByUserId(userId);
    }

    public List<DiscountInformation> findByStrategyName(String name) {
        return discountInfo.getByDiscountName(name);
    }

    public int increaseCounter(DiscountInformation info) {
        info.setUserDiscountCounter(info.getUserDiscountCounter()+1);
        discountInfo.update(info);
        return info.getUserDiscountCounter();
    }

    public String getTextInfo(List<DiscountInformation> list) {
        StringBuilder text = new StringBuilder();
        discountInfo.getAll().forEach(info -> {
            text.append(info.getStrategyName()).
                    append(" (total " + value.getTotatlStrings() + ")").
                    append("\n");
            for (Map.Entry<User, Integer> userIntegerEntry : value.getUserDiscountCounter().entrySet()) {
                User user = userIntegerEntry.getKey();
                text.append("\t").
                        append(user == null ? "Not registered user" : user.getFirstName()).
                        append(" - ").
                        append(userIntegerEntry.getValue()).append("\n");
            }
        });
    }
}
