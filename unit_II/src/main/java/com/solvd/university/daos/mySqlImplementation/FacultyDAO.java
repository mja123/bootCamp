package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.interfaces.IFacultyDAO;
import com.solvd.university.model.Faculty;

public class FacultyDAO extends BaseDAO<Faculty> implements IFacultyDAO {
    public FacultyDAO(String tableName, String className) {
        super(tableName, className);
    }
}
