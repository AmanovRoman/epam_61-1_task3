package com.epam.spring.hometask.service.domain.simple;


import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.utils.DiscountInformation;
import com.epam.spring.hometask.service.domain.DiscountInfoDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Roman_Amanov
 */

@Repository
public class DiscountInfoDaoImpl extends DBconnector implements DiscountInfoDao {


    @Override
    public List<DiscountInformation> getByUserId(int userId) {
        return DBconnector.getConnection().getDiscountInfo().values().stream().filter(info -> info.getUserId() == userId).collect(Collectors.toList());
    }

    @Override
    public List<DiscountInformation> getByDiscountName(String name) {
        return DBconnector.getConnection().getDiscountInfo().values().stream().filter(info -> info.getStrategyName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public boolean update(DiscountInformation info) {
        return DBconnector.getConnection().getDiscountInfo().put(info.getStrategyName(), info) != null;
    }

    @Override
    public int save(DiscountInformation info) {
        DBconnector.getConnection().getDiscountInfo().put(info.getStrategyName(), info);
        return 0;
    }

    @Override
    public DiscountInformation remove(int id) {
        return null;
    }

    @Override
    public DiscountInformation getById(int id) {
        return null;
    }

    @Override
    public List<DiscountInformation> getAll() {
        return new ArrayList<>(DBconnector.getConnection().getDiscountInfo().values());
    }
}
