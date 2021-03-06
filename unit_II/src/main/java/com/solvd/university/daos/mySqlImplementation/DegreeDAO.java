package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.interfaces.IDegreeDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Degree;

public class DegreeDAO extends BaseDAO<Degree> implements IDegreeDAO {

    public DegreeDAO(String tableName, String className) {
        super(tableName, className);
    }
}
