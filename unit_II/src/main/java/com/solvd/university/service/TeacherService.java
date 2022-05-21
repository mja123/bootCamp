package com.solvd.university.service;

import com.solvd.university.daos.mySqlImplementation.TeacherDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;

public class TeacherService {
  public static void main(String[] args) {
      TeacherDAO teacherDAO = new TeacherDAO("teachers", "com.solvd.university.model.Teacher");
      try {
          teacherDAO.removeEntity(2L);
      } catch (ElementNotFoundException e) {
          e.printStackTrace();
      }
  }





}
