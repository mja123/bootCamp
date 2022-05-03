package com.solvd.dataBases.university.daos.interfaces;

public interface IBaseDAO <T> {
    T getEntityByID(Long id);
    void saveEntity(T entity);
    void updateEntity(T entity);
    void removeEntity(T entity);
}
