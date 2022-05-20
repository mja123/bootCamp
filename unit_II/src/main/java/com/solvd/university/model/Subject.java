package com.solvd.university.model;

import java.sql.Date;

public class Subject {
    private Long id;
    private String name;
    private Integer year;
    private Boolean biannual;
    private Date createdAt;
    private Date deletedAt;

    public Subject() {
    }

    public Subject(String name, Integer year, Boolean biannual) {
        this.name = name;
        this.year = year;
        this.biannual = biannual;
    }
    public Subject(Long id, String name, Integer year, Boolean biannual) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.biannual = biannual;
    }

    public Subject(Long id, String name, Integer year, Boolean biannual, Date createdAt, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.biannual = biannual;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    //region Getter and setters
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getBiannual() {
        return biannual;
    }

    public void setBiannual(Boolean biannual) {
        this.biannual = biannual;
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
    //endregion

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", biannual=" + biannual +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
