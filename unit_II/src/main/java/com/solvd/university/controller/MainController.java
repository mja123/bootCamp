package com.solvd.university.controller;

import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.daos.mySqlImplementation.DeanDAO;
import com.solvd.university.model.Dean;

import com.solvd.university.model.EntityBuilder;
import com.solvd.university.service.DeanService;
import com.solvd.university.service.DegreeService;
import com.solvd.university.service.ServiceFactory;
import com.solvd.university.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

    //TODO:
    // 1) FIX THE PROBLEM IN THE SAVE METHOD, THE ID IS AUTOINCREMENT BUT THE RECORD DOESN'T APPEAR
    // 2) ADD BASE CRUD METHODS IN THE ISERVICE.
    // 3) IN THE MODELS AND BASEDAO CLASS, CHANGE THE FOREIGN KEY FOR THE CLASS OBJECT
    // 4) CREATE THE REST OF THE MAPPERS
    // 5) CREATE THE ABSTRACT FACTORY
    // 6) IMPLEMENT THE REST OF THE METHODS IN THE DAOS.
public class MainController {

  private static final Logger LOGGER = LogManager.getLogger(MainController.class);

  public static void main(String[] args) {

    ServiceFactory serviceFactory = new ServiceFactory();
    StudentService studentService = (StudentService) serviceFactory.getService("Student", "BaseDAO");
    studentService.getEntityById(9L);


  }
}
