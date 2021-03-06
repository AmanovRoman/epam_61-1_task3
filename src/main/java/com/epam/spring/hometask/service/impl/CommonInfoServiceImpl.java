package com.epam.spring.hometask.service.impl;


import com.epam.spring.hometask.domain.utils.CommonInformation;
import com.epam.spring.hometask.service.CommonInfoService;
import com.epam.spring.hometask.dao.CommonInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Roman_Amanov
 */

@Service
@Primary
public class CommonInfoServiceImpl implements CommonInfoService {
    @Autowired
    CommonInfoDao commonInfo;

    public int saveInfo(CommonInformation info) {
        return this.commonInfo.save(info);
    }

    public CommonInformation findByEventId(int eventId) {
        return commonInfo.getByEvent(eventId);
    }

    public int increaseAccessedByName(CommonInformation info) {
        info.setAccessedByNameCounter(info.getAccessedByNameCounter() + 1);
        commonInfo.update(info);
        return info.getAccessedByNameCounter();
    }

    public int increasePriceQueried(CommonInformation info) {
        info.setPriceQueriedCounter(info.getPriceQueriedCounter() + 1);
        commonInfo.update(info);
        return info.getPriceQueriedCounter();
    }

    public int increaseTicketBooked(CommonInformation info) {
        info.setTicketsBookedCounter(info.getTicketsBookedCounter() + 1);
        commonInfo.update(info);
        return info.getTicketsBookedCounter();
    }

    public List<CommonInformation> getAllCommonInformation() {
        return commonInfo.getAll();
    }

    public String getTextInfo(List<CommonInformation> list) {
        StringBuilder text = new StringBuilder();
        getAllCommonInformation().forEach(info -> {
            text.append("Event id #").append(info.getEventId()).
                    append("\n");
            text.append("\tEvent access by name count: ").append(info.getAccessedByNameCounter()).append("\n");
            text.append("\tEvent price queried count: ").append(info.getPriceQueriedCounter()).append("\n");
            text.append("\tEvent booked tickets count: ").append(info.getTicketsBookedCounter()).append("\n");
        });
        return text.toString();
    }
}