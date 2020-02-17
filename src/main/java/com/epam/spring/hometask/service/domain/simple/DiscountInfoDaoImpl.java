package com.epam.spring.hometask.service.domain.simple;


import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.utils.DiscountInformation;
import com.epam.spring.hometask.service.domain.DiscountInfoDao;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author Roman_Amanov
 */

@Repository
public class DiscountInfoDaoImpl extends DBconnector implements DiscountInfoDao {

    @Override
    public Map<Integer, DiscountInformation> getByDiscountName(String name) {
        return DBconnector.getConnection().getDiscountInfo().get(name);
    }

    @Override
    public boolean update(DiscountInformation info) {
        return save(info) > 0;
    }

    @Override
    public int save(DiscountInformation info) {
        Map<Integer, DiscountInformation> list = DBconnector.getConnection().getDiscountInfo().get(info.getStrategyName());
        if (list == null)
            list = new HashMap<>();
        DiscountInformation r = list.put(info.getUserId(), info);
        DBconnector.getConnection().getDiscountInfo().put(info.getStrategyName(), list);
        return (r == null)?0:r.getUserId();
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
//        return Arrays.asList(DBconnector.getConnection().getDiscountInfo().values().stream().toArray());
        return null;
    }

    @Override
    public Set<String> getAllDiscountNames() {
        return DBconnector.getConnection().getDiscountInfo().keySet();
    }

    @Override
    public long countByName(String discountName) {
        Map<Integer, DiscountInformation> list = DBconnector.getConnection().getDiscountInfo().get(discountName);
        return list.values().stream().mapToInt(DiscountInformation::getUserDiscountCounter).sum();
    }

}
