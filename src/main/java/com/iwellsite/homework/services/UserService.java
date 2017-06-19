package com.iwellsite.homework.services;

import com.iwellsite.homework.domain.model.User;

/**
 * Created by mshields on 2017-06-19.
 */
public interface UserService extends CRUDService<User> {

    User findByUsername(String username);

}
