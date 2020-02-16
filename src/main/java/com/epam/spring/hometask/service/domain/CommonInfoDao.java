package com.epam.spring.hometask.service.domain;

import com.epam.spring.hometask.domain.utils.CommonInformation;

/**
 * @author Roman_Amanov
 */

public interface CommonInfoDao extends AbstractDomainObjectDao<CommonInformation> {
    CommonInformation getByEvent(int eventId);

    boolean update(CommonInformation info);
}
