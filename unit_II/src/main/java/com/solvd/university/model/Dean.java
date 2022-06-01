package com.solvd.university.model;

import java.sql.Date;

public class Dean {
  private Long id;
  private String name;
  private Integer age;
  private String profession;
  private Date createdAt;
  private Date deletedAt;
  private Long facultiesId;
  public Dean() {}

  public Dean(String name, Integer age, String profession, Long facultiesId) {
    this.name = name;
    this.age = age;
    this.profession = profession;
    this.facultiesId = facultiesId;
  }

  public Dean(Long id, String name, Integer age, String profession, Long facultiesId) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.profession = profession;
    this.facultiesId = facultiesId;
  }

  public Dean(
      Long id, String name, Integer age, String profession, Date createdAt, Date deletedAt, Long facultiesId) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.profession = profession;
    this.createdAt = createdAt;
    this.deletedAt = deletedAt;
    this.facultiesId = facultiesId;
  }

  // region Getters and Setters
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

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
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

  public Long getFacultiesId() {
    return facultiesId;
  }

  public void setFacultiesId(Long facultiesId) {
    this.facultiesId = facultiesId;
  }

  // endregion


  @Override
  public String toString() {
    return "Dean{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", profession='" + profession + '\'' +
            ", createdAt=" + createdAt +
            ", deletedAt=" + deletedAt +
            ", facultiesId=" + facultiesId +
            '}';
  }
}
