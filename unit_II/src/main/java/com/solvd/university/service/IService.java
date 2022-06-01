package com.solvd.university.service;

import com.solvd.university.daos.interfaces.IBaseDAO;

public interface IService <T> {
    IBaseDAO<T> setDao();
}
