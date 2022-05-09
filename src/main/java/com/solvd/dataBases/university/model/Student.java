package com.solvd.dataBases.university.model;

import java.sql.Date;

public class Student {
  private Long id;
  private String name;
  private String email;
  private Integer age;
  private Integer yearsInDegree;
  private Date createdAt;
  private Date deletedAt;

  public Student() {}

  public Student(String name, String email, Integer age, Integer yearsInDegree) {
    this.name = name;
    this.email = email;
    this.age = age;
    this.yearsInDegree = yearsInDegree;
  }

  public Student(
      Long id,
      String name,
      String email,
      Integer age,
      Integer yearsInDegree) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.age = age;
    this.yearsInDegree = yearsInDegree;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getYearsInDegree() {
    return yearsInDegree;
  }

  public void setYearsInDegree(Integer yearsInDegree) {
    this.yearsInDegree = yearsInDegree;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
  }
}
