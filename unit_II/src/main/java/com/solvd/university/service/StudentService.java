package com.solvd.university.service;

import com.solvd.university.daos.interfaces.IBaseDAO;
import com.solvd.university.daos.mySqlImplementation.BaseDAO;
import com.solvd.university.model.Student;
import com.solvd.university.service.SqlSessionFactoryReference.*;
import com.solvd.university.daos.interfaces.IStudentDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class StudentService implements IService{
  private static final Logger LOGGER = LogManager.getLogger(StudentService.class);
  private IBaseDAO<Student> studentDAO;

  public StudentService(String dao) {
    setDAO(dao);
  }

  @Override
  public void setDAO(String dao) {
    switch (dao) {
      case "myBatis":
        studentDAO = SqlSessionFactoryReference.getINSTANCE()
                .getSessionFactory()
                .openSession()
                .getMapper(IStudentDAO.class);
        break;
      case "BaseDAO":
        studentDAO = new BaseDAO<Student>("students", "com.solvd.university.model.Student");
        break;
    }
  }

  public void getEntityById(Long id) {
    try {
      LOGGER.info(studentDAO.getEntityByID(id));
    } catch (ElementNotFoundException e) {
      LOGGER.error(e);
    }
  }

  public void saveEntity(Student student) {
    studentDAO.saveEntity(student);
    System.out.println("call");
  }

  public void removeEntity(Long id) {
    try {
      studentDAO.removeEntity(id);
    } catch (ElementNotFoundException e) {
      LOGGER.error(e);
    }
  }

  public void updateEntity(Student student) {
    try {
      studentDAO.updateEntity(student);
    } catch (ElementNotFoundException e) {
      LOGGER.error(e);
    }
  }
}
