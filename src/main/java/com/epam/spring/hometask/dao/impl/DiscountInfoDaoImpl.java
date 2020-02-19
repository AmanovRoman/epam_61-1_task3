package com.epam.spring.hometask.dao.impl;


import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.utils.DiscountInformation;
import com.epam.spring.hometask.dao.DiscountInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Roman_Amanov
 */

@Repository
public class DiscountInfoDaoImpl implements DiscountInfoDao {

    private DBconnector connector;

    @Autowired
    public DiscountInfoDaoImpl(DBconnector connector) {
        this.connector = connector;
    }

    @Override
    public Map<Integer, DiscountInformation> getByDiscountName(String name) {
        return connector.getConnection().getDiscountInfo().get(name);
    }

    @Override
    public boolean update(DiscountInformation info) {
        return save(info) > 0;
    }

    @Override
    public int save(DiscountInformation info) {
        Map<Integer, DiscountInformation> list = connector.getConnection().getDiscountInfo().get(info.getStrategyName());
        if (list == null)
            list = new HashMap<>();
        DiscountInformation r = list.put(info.getUserId(), info);
        connector.getConnection().getDiscountInfo().put(info.getStrategyName(), list);
        return (r == null) ? 0 : r.getUserId();
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
        return connector.getConnection().getDiscountInfo().keySet();
    }

    @Override
    public long countByName(String discountName) {
        Map<Integer, DiscountInformation> list = connector.getConnection().getDiscountInfo().get(discountName);
        return list.values().stream().mapToInt(DiscountInformation::getUserDiscountCounter).sum();
    }

}
