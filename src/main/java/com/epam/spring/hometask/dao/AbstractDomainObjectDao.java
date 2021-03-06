package com.epam.spring.hometask.dao;

import java.util.List;

/**
 * @author Yuriy_Tkach
 */
public interface AbstractDomainObjectDao<T> {

    /**
     * Saving new object to storage or updating existing one
     *
     * @param object Object to save
     * @return saved object with assigned id
     */
    int save(T object);

    /**
     * Removing object from storage
     */
    T remove(int id);

    /**
     * Getting object by id from storage
     *
     * @param id id of the object
     * @return Found object or <code>null</code>
     */
    T getById(int id);

    /**
     * Getting all objects from storage
     *
     * @return collection of objects
     */
    List<T> getAll();
}
