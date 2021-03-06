package com.solvd.university.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Timestamp;

public class Degree {
  @JsonProperty private Long id;
  @JsonProperty private String name;
  @JsonProperty private Integer duration;

  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh-mm-ss")
  private Timestamp createdAt;

  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh-mm-ss")
  private Timestamp deletedAt;

  @JsonProperty private Long facultiesId;

  public Degree() {}

  public Degree(String name, Integer duration, Long facultiesId) {
    this.name = name;
    this.duration = duration;
    this.facultiesId = facultiesId;
  }

  public Degree(Long id, String name, Integer duration, Long facultiesId) {
    this.id = id;
    this.name = name;
    this.duration = duration;
    this.facultiesId = facultiesId;
  }

  public Degree(
      Long id, String name, Integer duration, Timestamp createdAt, Timestamp deletedAt, Long facultiesId) {
    this.id = id;
    this.name = name;
    this.duration = duration;
    this.createdAt = createdAt;
    this.deletedAt = deletedAt;
    this.facultiesId = facultiesId;
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

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
  }

  public Long getFacultiesId() {
    return facultiesId;
  }

  public void setFacultiesId(Long facultiesId) {
    this.facultiesId = facultiesId;
  }

  @Override
  public String toString() {
    return "Degree{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", duration=" + duration +
            ", createdAt=" + createdAt +
            ", deletedAt=" + deletedAt +
            ", facultiesId=" + facultiesId +
            '}';
  }
}
