package com.solvd.dataBases.university.controller;

import com.solvd.dataBases.university.daos.mySqlImplementation.StudentDAO;
import com.solvd.dataBases.university.model.Student;
import com.solvd.solvdPractice.collections.exceptions.ElementNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainController {
  private static final Logger LOGGER = LogManager.getLogger(MainController.class);

  public static void main(String[] args) {
    StudentDAO students = new StudentDAO();
   /* Student student = new Student("Franco", "franco@gmail.com", 32, 3);
    students.saveEntity(student);

    Long id = student.getId();
    LOGGER.info(id);
*/
    try {
      LOGGER.info(students.getEntityByID(5L).toString());
    } catch (ElementNotFound e) {
      LOGGER.error(e.getMessage());
    }
   /* LOGGER.info(student.getId());
    try {
      students.removeEntity(new Student(28L, "Franco", "franco@gmail.com", 32, 3));
    } catch (ElementNotFound e) {
      LOGGER.error(e.getMessage());
    }*/

  }
}
