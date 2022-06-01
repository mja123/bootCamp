package com.solvd.university.model;

public class EntityBuilder {
  private static Integer counter = 0;
  private static String name;

  public static Dean dean() {
    name = "entity built " + counter;
    counter++;
    return (new Dean(name, 22, "Programmer", 1L));
  }

  public static Degree degree() {
    name = "entity built " + counter;
    counter++;
    return (new Degree(name, 4, 1L));
  }

  public static Faculty faculty() {
    name = "entity built " + counter;
    counter++;
    return (new Faculty(name));
  }

  public static Student student() {
    name = "entity built " + counter;
    String email = "entityBuilt" + counter + "@gmail.com";
    counter++;
    return (new Student(name, email, 29, 2));
  }

  public static Subject subject() {
    name = "entity built " + counter;
    counter++;
    return (new Subject(name, 3, true));
  }

  public static Teacher teacher() {
    name = "entity built " + counter;
    String email = "entityBuilt" + counter + "@gmail.com";
    counter++;
    return (new Teacher(name, email, 10));
  }
}
