package com.iwellsite.homework.domain.repo;

import com.iwellsite.homework.domain.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mshields on 2017-06-19.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
