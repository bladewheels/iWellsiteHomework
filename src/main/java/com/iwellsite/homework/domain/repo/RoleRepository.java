package com.iwellsite.homework.domain.repo;

import com.iwellsite.homework.domain.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mshields on 2017-06-19.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
