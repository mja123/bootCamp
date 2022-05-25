package com.solvd.university.service;

import com.solvd.university.daos.mySqlImplementation.DegreeDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Degree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DegreeService {
  private static final Logger LOGGER = LogManager.getLogger(DegreeService.class);

  public static void main(String[] args) {
    DegreeDAO degreeDAO = new DegreeDAO("degrees", "com.solvd.university.model.Degree");
    Degree degree = new Degree(1L, "Informatics", 4, 1L);

    System.out.println(degreeDAO.getResultSetById(1L));
    //degreeDAO.saveEntity(new Degree("Computer Science", 5, 1L));

/*   try {
     degreeDAO.updateEntity(new Degree(4L,"Design", 5, 1L));
     degreeDAO.removeEntity(4L);
   } catch (ElementNotFoundException e) {
     LOGGER.error(e.getMessage());
   }*/
  }
}
