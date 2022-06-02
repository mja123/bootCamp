package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.interfaces.IStudentHasSubjectDAO;
import com.solvd.university.model.StudentHasSubject;

public class StudentHasSubjectDAO extends BaseDAO<StudentHasSubject> implements IStudentHasSubjectDAO {
    public StudentHasSubjectDAO(String tableName, String className) {
      super(tableName, className);
    }

}
