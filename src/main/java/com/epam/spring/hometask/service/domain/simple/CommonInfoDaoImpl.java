package com.epam.spring.hometask.service.domain.simple;


import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.utils.CommonInformation;
import com.epam.spring.hometask.service.domain.CommonInfoDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman_Amanov
 */

@Repository
public class CommonInfoDaoImpl extends DBconnector implements CommonInfoDao {


    @Override
    public CommonInformation getByEvent(int eventId) {
        return DBconnector.getConnection().getCommonInfo().get(eventId);
    }

    @Override
    public boolean update(CommonInformation info) {
        return (DBconnector.getConnection().getCommonInfo().put(info.getEventId(), info) != null);
    }

    @Override
    public int save(CommonInformation info) {
        info.setId(IdHolder.getId());
        DBconnector.getConnection().getCommonInfo().put(info.getEventId(), info);
        return info.getId();
    }

    @Override
    public CommonInformation remove(int eventId) {
        return DBconnector.getConnection().getCommonInfo().remove(eventId);
    }

    @Override
    public CommonInformation getById(int eventId) {
        return DBconnector.getConnection().getCommonInfo().get(eventId);
    }

    @Override
    public List<CommonInformation> getAll() {
        return new ArrayList<>(DBconnector.getConnection().getCommonInfo().values());
    }

    private static class IdHolder {
        private static int counter = 1;
        public static int getId() {
            return counter++;
        }
    }
}
