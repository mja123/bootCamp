package com.solvd.university.controller;

import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.daos.mySqlImplementation.DeanDAO;
import com.solvd.university.model.Dean;

import com.solvd.university.model.EntityBuilder;
import com.solvd.university.service.DeanService;
import com.solvd.university.service.DegreeService;
import com.solvd.university.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainController {
  //TODO: ADD LISTENER PATTERN AND TRY TO IMPLEMENT THE NOTIFY AND WAIT IN THE DAO.
  private static final Logger LOGGER = LogManager.getLogger(MainController.class);

  public static void main(String[] args) {

    StudentService studentService = new StudentService();

    studentService.getStudent(43L);
    //TODO: FIX THE PROBLEM IN THE SAVE METHOD, THE ID IS AUTOINCREMENT BUT THE RECORD DOESN'T APPEAR
    //region Concurrent tests
/*    DeanService deanService = new DeanService();
    DeanService deanService2 = new DeanService();
    DeanService deanService3 = new DeanService();
    DeanService deanService4 = new DeanService();
    DeanService deanService5 = new DeanService();
    DeanService deanService6 = new DeanService();
    DeanService deanService7 = new DeanService();
    DeanService deanService8 = new DeanService();
    DeanService deanService9 = new DeanService();
    DeanService deanService10 = new DeanService();

    deanService.run();
    deanService2.run();
    deanService3.run();
    deanService4.run();
    deanService5.run();
    deanService6.run();
    deanService7.run();
    deanService8.run();
    deanService9.run();
    deanService10.run();*/
    //endregion


  }
}
