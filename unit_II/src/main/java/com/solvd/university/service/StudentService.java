package com.solvd.university.service;

import com.solvd.university.daos.interfaces.IBaseDAO;
import com.solvd.university.model.Student;
import com.solvd.university.service.SqlSessionFactoryReference.*;
import com.solvd.university.daos.interfaces.IStudentDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class StudentService {
  private static final Logger LOGGER = LogManager.getLogger(StudentService.class);
  IBaseDAO<Student> studentDAO =
      studentDAO =
          SqlSessionFactoryReference.getINSTANCE()
              .getSessionFactory()
              .openSession()
              .getMapper(IStudentDAO.class);

  public StudentService() {}

  public void getStudent(Long id) {
    try {
      LOGGER.info(studentDAO.getEntityByID(id));
    } catch (ElementNotFoundException e) {
      LOGGER.error(e);
    }
  }

  public void createStudent(Student student) {
    studentDAO.saveEntity(student);
    System.out.println("call");
  }

  public void removeStudent(Long id) {
    try {
      studentDAO.removeEntity(id);
    } catch (ElementNotFoundException e) {
      LOGGER.error(e);
    }
  }

  public void updateStudent(Student student) {
    try {
      studentDAO.updateEntity(student);
    } catch (ElementNotFoundException e) {
      LOGGER.error(e);
    }
  }
}
