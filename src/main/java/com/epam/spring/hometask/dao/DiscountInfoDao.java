package com.epam.spring.hometask.dao;


import com.epam.spring.hometask.domain.utils.DiscountInformation;

import java.util.Map;
import java.util.Set;

/**
 * @author Roman_Amanov
 */

public interface DiscountInfoDao extends AbstractDomainObjectDao<DiscountInformation> {
//    List<DiscountInformation> getByUserId(int userId);

    Map<Integer, DiscountInformation> getByDiscountName(String name);

    boolean update(DiscountInformation info);

    Set<String> getAllDiscountNames();

    long countByName(String discountName);
}
