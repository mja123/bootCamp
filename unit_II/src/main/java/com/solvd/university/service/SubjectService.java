package com.solvd.university.service;

import com.solvd.university.daos.mySqlImplementation.SubjectDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SubjectService {
  private static final Logger LOGGER = LogManager.getLogger(SubjectService.class);

  public static void main(String[] args) {
    SubjectDAO subjectDAO = new SubjectDAO("subjects", "com.solvd.university.model.Subject");
    Subject subject = new Subject(9L,"updated", 2, true);


    try {
      subjectDAO.updateEntity(subject);
      //LOGGER.info(subjectDAO.getEntityByID(2L));
      //subjectDAO.removeEntity(14L);
    } catch (ElementNotFoundException e) {
      LOGGER.error(e.getMessage());
    }
  }
}
