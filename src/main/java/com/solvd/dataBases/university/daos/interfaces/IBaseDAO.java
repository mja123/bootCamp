package com.solvd.dataBases.university.daos.interfaces;

import com.solvd.solvdPractice.collections.exceptions.ElementNotFound;

public interface IBaseDAO<T> {
  T getEntityByID(Long id) throws ElementNotFound;

  void saveEntity(T entity);

  void updateEntity(T entity) throws ElementNotFound;

  void removeEntity(T entity) throws ElementNotFound;
}
