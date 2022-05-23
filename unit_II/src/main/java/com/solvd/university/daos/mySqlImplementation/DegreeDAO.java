package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Degree;

public class DegreeDAO extends BaseDAO<Degree> {

    public DegreeDAO(String tableName, String className) {
        super(tableName, className);
    }

    @Override
    public Degree getEntityByID(Long id) throws ElementNotFoundException {
        return null;
    }
}
