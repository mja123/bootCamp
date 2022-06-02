package com.solvd.university.service;

import com.solvd.university.daos.interfaces.IBaseDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;

public interface IService {
     void setDAO(String dao);

}
