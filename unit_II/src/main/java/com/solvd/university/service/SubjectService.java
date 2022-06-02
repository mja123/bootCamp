package com.solvd.university.service;

import com.solvd.university.daos.interfaces.IBaseDAO;
import com.solvd.university.daos.interfaces.ISubjectDAO;
import com.solvd.university.daos.mySqlImplementation.SubjectDAO;
import com.solvd.university.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SubjectService implements IService{
  private static final Logger LOGGER = LogManager.getLogger(SubjectService.class);
  private IBaseDAO<Subject> subjectDAO;

  public SubjectService(String dao) {
    this.setDAO(dao);
  }

  @Override
  public void setDAO(String dao) {
    switch (dao) {
      case "myBatis":
        subjectDAO = SqlSessionFactoryReference.getINSTANCE()
                .getSessionFactory()
                .openSession()
                .getMapper(ISubjectDAO.class);
        break;
      case "BaseDAO":
        subjectDAO = new SubjectDAO("subjects", "com.solvd.university.model.Subject");
        break;
    }
  }
}
