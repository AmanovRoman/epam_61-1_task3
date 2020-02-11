package com.epam.spring.hometask.service.repository;

import com.epam.spring.hometask.domain.User;

/**
 * @author Yuriy_Tkach
 */
public interface UserServiceDao extends AbstractDomainObjectService<User> {

    User getUserByEmail(String email);

}
