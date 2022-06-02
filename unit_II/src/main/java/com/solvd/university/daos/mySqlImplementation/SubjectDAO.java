package com.solvd.university.daos.mySqlImplementation;


import com.solvd.university.daos.interfaces.ISubjectDAO;

import com.solvd.university.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SubjectDAO extends BaseDAO<Subject> implements ISubjectDAO {
    private static final Logger LOGGER = LogManager.getLogger(Subject.class);

    public SubjectDAO(String tableName, String className) {
        super(tableName, className);
    }
}
