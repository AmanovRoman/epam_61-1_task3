package com.epam.spring.hometask.service;


import com.epam.spring.hometask.domain.utils.CommonInformation;

import java.util.List;

/**
 * @author Roman_Amanov
 */

public interface CommonInfoService {
    int saveInfo(CommonInformation var1);

    CommonInformation findByEventId(int eventId);

    int increaseAccessedByName(CommonInformation info);

    int increasePriceQueried(CommonInformation info);

    int increaseTicketBooked(CommonInformation info);

    List<CommonInformation> getAllCommonInformation();

    String getTextInfo(List<CommonInformation> list);
}