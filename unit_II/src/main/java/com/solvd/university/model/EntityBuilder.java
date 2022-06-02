package com.solvd.university.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class EntityBuilder {
  private static final LocalTime UNIQUE_DATA = LocalTime.now();
  private static String name;

  public static Dean dean() {
    name = "entity built " + UNIQUE_DATA;
    return (new Dean(name, 22, "Programmer", 1L));
  }

  public static Degree degree() {
    name = "entity built " + UNIQUE_DATA;
    return (new Degree(name, 4, 1L));
  }

  public static Faculty faculty() {
    name = "entity built " + UNIQUE_DATA;
    return (new Faculty(name));
  }

  public static Student student() {
    name = "entity built " + UNIQUE_DATA;
    String email = "entityBuilt" + UNIQUE_DATA + "@gmail.com";
    return (new Student(name, email, 29, 2));
  }

  public static Subject subject() {
    name = "entity built " + UNIQUE_DATA;
    return (new Subject(name, 3, true));
  }

  public static Teacher teacher() {
    name = "entity built " + UNIQUE_DATA;
    String email = "entityBuilt" + UNIQUE_DATA + "@gmail.com";
    return (new Teacher(name, email, 10));
  }

  public static StudentHasSubject studentHasSubject() {
    return new StudentHasSubject(9L, 1L);
  }
}
