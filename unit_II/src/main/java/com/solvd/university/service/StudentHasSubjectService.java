package com.solvd.university.service;

import com.solvd.university.daos.interfaces.IStudentDAO;
import com.solvd.university.daos.interfaces.IStudentHasSubjectDAO;
import com.solvd.university.daos.mySqlImplementation.BaseDAO;
import com.solvd.university.daos.mySqlImplementation.StudentHasSubjectDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.EntityBuilder;
import com.solvd.university.model.Student;
import com.solvd.university.model.StudentHasSubject;
import com.solvd.university.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StudentHasSubjectService implements IService{
  private static final Logger LOGGER = LogManager.getLogger(StudentHasSubjectService.class);
  private IStudentHasSubjectDAO studentHasSubjectsDAO;

  public StudentHasSubjectService(String dao) {
    this.setDAO(dao);
  }
  /*
  private BaseDAO<StudentHasSubject> studentHasSubjectDAO =
        new BaseDAO<>("students_has_subjects", "com.solvd.university.model.StudentHasSubject");
    StudentHasSubject studentHasSubject = EntityBuilder.studentHasSubject();


    try {
      studentHasSubjectDAO.updateEntity(studentHasSubject);
    } catch (ElementNotFoundException e) {
      LOGGER.error(e.getMessage());
    }
*/

  public void getEntity(Long id) {
    try {
      studentHasSubjectsDAO.getEntityByID(id);
    } catch (ElementNotFoundException e) {
      LOGGER.error(e);
    }
  }
  @Override
  public void setDAO(String dao) {
    switch (dao) {
      case "myBatis":
        studentHasSubjectsDAO = SqlSessionFactoryReference.getINSTANCE()
                .getSessionFactory()
                .openSession()
                .getMapper(IStudentHasSubjectDAO.class);
        break;
      case "BaseDAO":
        studentHasSubjectsDAO = new StudentHasSubjectDAO("students", "com.solvd.university.model.Student");
        break;
    }
  }

}
