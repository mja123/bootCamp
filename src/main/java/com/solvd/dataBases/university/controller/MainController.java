package com.solvd.dataBases.university.controller;

import com.solvd.dataBases.university.daos.mySqlImplementation.StudentDAO;
import com.solvd.dataBases.university.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MainController {
    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
  public static void main(String[] args) {
      StudentDAO students = new StudentDAO();
      Student student = new Student("Esteban", "estebannnnn@gmail.com", 24, 4);
      StudentDAO.setCONNECTION();
      //students.saveEntity(student);
      Long id = student.getId();
      //LOGGER.info(students.updateEntity(new Student(id, "Esteban", "est@gmail.com", 22, 4)));
      LOGGER.info(students.getEntityByID(student.getId()).getName());
    //TODO: FIX THE ID PROBLEMS
  }
}
