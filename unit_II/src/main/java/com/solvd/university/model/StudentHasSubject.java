package com.solvd.university.model;

import java.sql.Date;

public class StudentHasSubject {
    private Long id;
    private Long studentId;
    private Long subjectId;
    private Date createdAt;
    private Date deletedAt;

    public StudentHasSubject() {
    }

    public StudentHasSubject(Long studentId, Long subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public StudentHasSubject(Long studentId, Long subjectId, Date createdAt, Date deletedAt) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public StudentHasSubject(Long id, Long studentId, Long subjectId, Date createdAt, Date deletedAt) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
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
