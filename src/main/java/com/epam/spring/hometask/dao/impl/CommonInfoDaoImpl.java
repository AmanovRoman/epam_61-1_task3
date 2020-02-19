package com.epam.spring.hometask.dao.impl;


import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.utils.CommonInformation;
import com.epam.spring.hometask.dao.CommonInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman_Amanov
 */

@Repository
public class CommonInfoDaoImpl implements CommonInfoDao {

    private DBconnector connector;

    @Autowired
    public CommonInfoDaoImpl(DBconnector connector) {
        this.connector = connector;
    }

    @Override
    public CommonInformation getByEvent(int eventId) {
        return connector.getConnection().getCommonInfo().get(eventId);
    }

    @Override
    public boolean update(CommonInformation info) {
        return (connector.getConnection().getCommonInfo().put(info.getEventId(), info) != null);
    }

    @Override
    public int save(CommonInformation info) {
        info.setId(IdHolder.getId());
        connector.getConnection().getCommonInfo().put(info.getEventId(), info);
        return info.getId();
    }

    @Override
    public CommonInformation remove(int eventId) {
        return connector.getConnection().getCommonInfo().remove(eventId);
    }

    @Override
    public CommonInformation getById(int eventId) {
        return connector.getConnection().getCommonInfo().get(eventId);
    }

    @Override
    public List<CommonInformation> getAll() {
        return new ArrayList<>(connector.getConnection().getCommonInfo().values());
    }

    private static class IdHolder {
        private static int counter = 1;

        public static int getId() {
            return counter++;
        }
    }
}
