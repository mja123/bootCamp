package com.solvd.university.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement(name = "Student")
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

  public Student(Long id, String name, String email, Integer age, Integer yearsInDegree) {
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

  @XmlElement
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @XmlElement
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @XmlElement
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

  @Override
  public String toString() {
    return "Student{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", email='"
        + email
        + '\''
        + ", age="
        + age
        + ", yearsInDegree="
        + yearsInDegree
        + ", createdAt="
        + createdAt
        + ", deletedAt="
        + deletedAt
        + '}';
  }
}
