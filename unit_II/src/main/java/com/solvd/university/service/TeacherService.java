package com.solvd.university.service;

import com.solvd.university.daos.interfaces.ITeacherDAO;
import com.solvd.university.daos.mySqlImplementation.BaseDAO;
import com.solvd.university.daos.mySqlImplementation.TeacherDAO;
import com.solvd.university.model.Student;
import com.solvd.university.model.Teacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TeacherService implements IService {
  private static final Logger LOGGER = LogManager.getLogger(TeacherService.class);
  private ITeacherDAO teacherDAO;

    public TeacherService(String dao) {
        setDAO(dao);
    }

    @Override
    public void setDAO(String dao) {
        switch (dao) {
            case "myBatis":
                teacherDAO = SqlSessionFactoryReference.getINSTANCE()
                        .getSessionFactory()
                        .openSession()
                        .getMapper(ITeacherDAO.class);
                break;
            case "BaseDAO":
                teacherDAO = new TeacherDAO("teachers", "com.solvd.university.model.Teacher");
                break;
        }
    }

}
