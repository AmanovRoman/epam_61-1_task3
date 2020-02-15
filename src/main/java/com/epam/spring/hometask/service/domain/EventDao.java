package com.epam.spring.hometask.service.domain;

import com.epam.spring.hometask.domain.Event;

/**
 * @author Roman_Amanov
 */

/**
 * @author Yuriy_Tkach
 */
public interface EventDao extends AbstractDomainObjectDao<Event> {

    Event getByName(String name);

}
