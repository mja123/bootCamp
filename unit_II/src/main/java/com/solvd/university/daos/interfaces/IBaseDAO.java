package com.solvd.university.daos.interfaces;

import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;

public interface IBaseDAO<T> {
  T getEntityByID(Long id) throws ElementNotFoundException;

  void saveEntity(T entity);

  void updateEntity(T entity) throws ElementNotFoundException;

  void removeEntity(Long id) throws ElementNotFoundException;
}
