package com.epam.spring.hometask.service.domain;


import com.epam.spring.hometask.domain.utils.DiscountInformation;

import java.util.List;

/**
 * @author Roman_Amanov
 */

public interface DiscountInfoDao extends AbstractDomainObjectDao<DiscountInformation> {
    List<DiscountInformation> getByUserId(int userId);

    List<DiscountInformation> getByDiscountName(String name);

    boolean update(DiscountInformation info);
}
