package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Teacher;

public class TeacherDAO extends BaseDAO<Teacher> {

    public TeacherDAO(String tableName, String className) {
        super(tableName, className);
    }

    @Override
    public Teacher getEntityByID(Long id) throws ElementNotFoundException {
        return null;
    }

}
