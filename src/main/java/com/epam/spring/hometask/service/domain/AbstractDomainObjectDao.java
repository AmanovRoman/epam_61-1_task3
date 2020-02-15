package com.epam.spring.hometask.service.domain;

import com.epam.spring.hometask.domain.DomainId;

import java.util.Collection;
import java.util.List;

/**
 * @author Roman_Amanov
 */

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
