package com.solvd.university.service;

import com.solvd.university.daos.mySqlImplementation.TeacherDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Teacher;

public class TeacherService {
  public static void main(String[] args) {
      TeacherDAO teacherDAO = new TeacherDAO("teachers", "com.solvd.university.model.Teacher");
      Teacher teacher = new Teacher(2l, "Wilkinson", "wilkinson@gmail.com", 20);

      try {
          teacherDAO.updateEntity(teacher);
          //teacherDAO.removeEntity(2L);
      } catch (ElementNotFoundException e) {
          e.printStackTrace();
      }
  }





}
