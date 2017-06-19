package com.iwellsite.homework.services;

import java.util.List;

/**
 * Created by mshields on 2017-06-19.
 */
public interface CRUDService<T> {

    void delete(Integer id);
    T getById(Integer id);
    List<?> listAll();
    T saveOrUpdate(T domainObject);
}
