package com.huynguyen.bbqrestaurantmanagement.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID extends Serializable> {

    T save(T entity);
    T update(ID id, T updatedEntity);
    void deleteById(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
    boolean existsById(ID id);
}
