package com.solvd.dataBases.university.daos.mySqlImplementation;

import com.solvd.dataBases.university.daos.exceptions.ElementNotFound;
import com.solvd.dataBases.university.daos.interfaces.IStudentDAO;
import com.solvd.dataBases.university.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Result;

import static com.solvd.dataBases.university.daos.mySqlImplementation.EStudentAttributes.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class StudentDAO implements IStudentDAO {

  private static final Logger LOGGER = LogManager.getLogger(StudentDAO.class);
  private static Connection CONNECTION = null;
  private final String TABLE_NAME = "students";

  @Override
  public Student getEntityByID(Long id) {
    String selectOne = "SELECT * FROM " + this.TABLE_NAME + " WHERE id = " + id + ";";
    Student studentFound = null;

    try (PreparedStatement getStudent = CONNECTION.prepareStatement(selectOne)) {
      ResultSet result = getStudent.executeQuery();
      studentFound = this.covertInStudent(result);

      result.close();

    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    }
    return studentFound;
  }

  @Override
  public void saveEntity(Student entity) {
    String insertQuery =
        "INSERT into "
            + this.TABLE_NAME
            + " ("
            + NAME.getATTRIBUTE()
            + ", "
            + EMAIL.getATTRIBUTE()
            // + ", "
            // + AVERAGE
            + ", "
            + AGE.getATTRIBUTE()
            + ", "
            + YEARS_IN_DEGREE.getATTRIBUTE()
            + ") "
            + "VALUES ( ?, ?, ?, ?);";

    try (PreparedStatement insertStudent = CONNECTION.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
      CONNECTION.setAutoCommit(false);
      insertStudent.setString(1, entity.getName());
      insertStudent.setString(2, entity.getEmail());
      insertStudent.setInt(3, entity.getAge());
      insertStudent.setInt(4, entity.getYearsInDegree());

      insertStudent.executeUpdate();

      ResultSet keys = insertStudent.getGeneratedKeys();
      if (keys.next()) {
        entity.setId(keys.getLong(1));
      } else {
        throw new ElementNotFound("The id wasn't generated");
      }

      keys.close();

      CONNECTION.commit();

    } catch (SQLException | ElementNotFound throwables) {
      LOGGER.error(throwables.getMessage());
    }
  }

  @Override
  public void updateEntity(Student entity) {

    String update =
        "UPDATE "
            + this.TABLE_NAME
            + " SET "
            + NAME.getATTRIBUTE()
            + " = ? "
            + EMAIL.getATTRIBUTE()
            + " = ? "
            + AGE.getATTRIBUTE()
            + " = ? "
            + YEARS_IN_DEGREE.getATTRIBUTE()
            + " = ? ;";

    try (PreparedStatement updateUser = CONNECTION.prepareStatement(update)) {
      updateUser.setString(1, entity.getName());
      updateUser.setString(2, entity.getEmail());
      updateUser.setInt(3, entity.getAge());
      updateUser.setInt(4, entity.getYearsInDegree());

      updateUser.executeUpdate();

    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    }
  }

  @Override
  public void removeEntity(Student entity) {
    String remove = "DELETE FROM " + this.TABLE_NAME + " WHERE id = " + entity.getId() + ";";

    try (PreparedStatement removeUser = CONNECTION.prepareStatement(remove)) {
      removeUser.executeUpdate();

      // entity.setDeletedAt(new Date().toInstant());
    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    }
  }

  private Student covertInStudent(ResultSet result) {
    Student student = null;
    try {
      if (result.next()) {
        String name = result.getString(NAME.getATTRIBUTE());
        String email = result.getString(EMAIL.getATTRIBUTE());
        Integer age = result.getInt(AGE.getATTRIBUTE());
        Integer yearsInDegree = result.getInt(YEARS_IN_DEGREE.getATTRIBUTE());

        student = new Student(name, email, age, yearsInDegree);
      } else {
        throw new ElementNotFound("Element not found.");
      }

    } catch (SQLException | ElementNotFound e) {
      e.printStackTrace();
    }
    return student;
  }

  public static void setCONNECTION() {

    Properties properties = new Properties();
    try {
      FileReader reader = new FileReader(System.getenv("PROPERTIES"));
      properties.load(reader);
      CONNECTION =
          DriverManager.getConnection(
              properties.getProperty("URL")
                  + "?"
                  + "user="
                  + properties.getProperty("USER")
                  + "&password="
                  + properties.getProperty("PASSWORD"));
      System.out.println("Connection open");
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }
}
