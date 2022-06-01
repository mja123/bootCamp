package com.solvd.university.service;

import com.solvd.university.daos.mySqlImplementation.BaseDAO;
import com.solvd.university.daos.mySqlImplementation.DegreeDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Degree;
import com.solvd.university.model.EntityBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DegreeService implements Runnable {
  private static final Logger LOGGER = LogManager.getLogger(DegreeService.class);

  @Override
  public void run() {
    BaseDAO<Degree> degreeDAO = new BaseDAO<>("degrees", "com.solvd.university.model.Degree");
    Degree degree = EntityBuilder.degree();

    /*    try {
      System.out.println(degreeDAO.getEntityByID(5L));
    } catch (ElementNotFoundException e) {
      LOGGER.error(e);
    }*/
    // degreeDAO.saveEntity(new Degree("Computer Science", 5, 1L));

    try {
      // degreeDAO.saveEntity(new Degree("Economics", 5, 1L));
      degreeDAO.updateEntity(new Degree(8L, "Economics Sciences", 5, 1L));
      System.out.println(degreeDAO.getEntityByID(8L));
      degreeDAO.removeEntity(8L);
      System.out.println(degreeDAO.getEntityByID(8L));
    } catch (ElementNotFoundException e) {
      LOGGER.error(e.getMessage());
    }
  }
}
