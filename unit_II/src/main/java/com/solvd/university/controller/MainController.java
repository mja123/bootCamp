package com.solvd.university.controller;

import com.solvd.university.daos.exceptions.ElementNotFoundException;
import com.solvd.university.daos.mySqlImplementation.StudentDAO;
import com.solvd.university.model.Student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainController {
  private static final Logger LOGGER = LogManager.getLogger(MainController.class);

  public static void main(String[] args) {
    StudentDAO students = new StudentDAO();
  /*  Student student = new Student("German", "german@gmail.com", 32, 3);
    students.saveEntity(student);

    Long id = student.getId();
    LOGGER.info(id);

    try {
      LOGGER.info(students.getEntityByID(id).toString());
    } catch (ElementNotFoundException e) {
      LOGGER.error(e.getMessage());
    }
    LOGGER.info(student.getId());*/
    try {
      students.removeEntity(new Student(30L, "Franco", "franco@gmail.com", 32, 3));
    } catch (ElementNotFoundException e) {
      LOGGER.error(e.getMessage());
    }

  }
}
