package com.solvd.university.controller;

import com.solvd.university.daos.exceptions.ElementNotFoundException;
import com.solvd.university.daos.mySqlImplementation.DeanDAO;
import com.solvd.university.daos.mySqlImplementation.StudentDAO;
import com.solvd.university.model.Dean;
import com.solvd.university.model.Student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainController {
  private static final Logger LOGGER = LogManager.getLogger(MainController.class);

  public static void main(String[] args) {
    //region StudentDAO
  /*
    StudentDAO students = new StudentDAO();
    try {
      LOGGER.info(students.getEntityByID(16L).toString());
    } catch (ElementNotFoundException e) {
      LOGGER.error(e.getMessage());
    }
   Student student = new Student("German", "german@gmail.com", 32, 3);
    students.saveEntity(student);

    Long id = student.getId();
    LOGGER.info(id);
    try {
      students.removeEntity(30L);
    } catch (ElementNotFoundException e) {
      LOGGER.error(e.getMessage());
      */
    //endregion

    //region DeansDAO
    DeanDAO deans = new DeanDAO();

    try {
      LOGGER.info(deans.getEntityByID(1L));
      deans.updateEntity(new Dean(3L, "Sandez", 50, "Electronic engineer", 1L));
      deans.removeEntity(3L);
    } catch (ElementNotFoundException e) {
      LOGGER.error(e.getMessage());
    }
    //endregion


  }
}
