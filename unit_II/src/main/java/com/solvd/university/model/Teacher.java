package com.solvd.university.model;

import java.sql.Date;

public class Teacher {
    private Long id;
    private String name;
    private String email;
    private Integer yearsOfExperience;
    private Date createdAt;
    private Date deletedAt;

    public Teacher() {
    }

    public Teacher(Long id, String name, String email, Integer yearsOfExperience) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Teacher(String name, String email, Integer yearsOfExperience) {
        this.name = name;
        this.email = email;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Teacher(Long id, String name, String email, Integer yearsOfExperience, Date createdAt, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.yearsOfExperience = yearsOfExperience;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
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

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
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
