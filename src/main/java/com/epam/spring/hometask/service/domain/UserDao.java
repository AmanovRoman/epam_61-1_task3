package com.epam.spring.hometask.service.domain;

import com.epam.spring.hometask.domain.User;

/**
 * @author Roman_Amanov
 */

/**
 * @author Yuriy_Tkach
 */

public interface UserDao extends AbstractDomainObjectDao<User> {

    User getUserByEmail(String email);

}
