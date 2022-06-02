package com.solvd.university.service;

public class ServiceFactory {

    public IService getService(String service, String dao) {
        switch (service) {
          case "Student":
            return new StudentService(dao);
          case "Subject":
            return new SubjectService(dao);
          case "Teacher":
            return new TeacherService(dao);
          case "Degree":
            return new DegreeService(dao);
          case "Dean":
            return new DeanService(dao);
          case "Faculty":
            return new FacultyService(dao);
          case "StudentHasSubject":
            return new StudentHasSubjectService(dao);
          default:
            return null;
        }
    }
}
