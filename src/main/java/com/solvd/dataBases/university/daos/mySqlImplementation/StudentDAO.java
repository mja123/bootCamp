package com.solvd.dataBases.university.daos.mySqlImplementation;

import com.solvd.dataBases.university.daos.connectionPool.ConnectionPool;
import com.solvd.dataBases.university.daos.exceptions.ElementNotFoundException;
import com.solvd.dataBases.university.daos.exceptions.FullConnectionPoolException;
import com.solvd.dataBases.university.daos.interfaces.IStudentDAO;
import com.solvd.dataBases.university.model.Student;
import com.solvd.solvdPractice.collections.exceptions.ElementNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.solvd.dataBases.university.daos.mySqlImplementation.EStudentAttributes.*;

import java.sql.*;

public class StudentDAO implements IStudentDAO {

  private static final Logger LOGGER = LogManager.getLogger(StudentDAO.class);
  private static Connection CONNECTION = null;
  private final String TABLE_NAME = "students";

  @Override
  public Student getEntityByID(Long id) throws ElementNotFound {
    String selectOne = "SELECT * FROM " + this.TABLE_NAME + " WHERE id = " + id + ";";
    Student studentFound = null;

    setCONNECTION();

    try (PreparedStatement getStudent = CONNECTION.prepareStatement(selectOne)) {

      if (id == null) {
        throw new ElementNotFound("Id doesn't exist.");
      }

      ResultSet result = getStudent.executeQuery();
      studentFound = this.covertInStudent(result);

      result.close();

    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(CONNECTION);
    }
    return studentFound;
  }

  @Override
  public void saveEntity(Student entity) {

    setCONNECTION();

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

    try (PreparedStatement insertStudent =
        CONNECTION.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
      CONNECTION.setAutoCommit(false);
      insertStudent.setString(1, entity.getName());
      insertStudent.setString(2, entity.getEmail());
      insertStudent.setInt(3, entity.getAge());
      insertStudent.setInt(4, entity.getYearsInDegree());

      insertStudent.executeUpdate();

      ResultSet keys = insertStudent.getGeneratedKeys();
      if (keys.next()) {
        entity.setId(keys.getLong(1));
        LOGGER.info(keys.getLong(1));
      } else {
        throw new ElementNotFoundException("The id wasn't generated");
      }

      keys.close();

      CONNECTION.commit();

    } catch (SQLException | ElementNotFoundException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(CONNECTION);
    }
  }

  @Override
  public void updateEntity(Student entity) throws ElementNotFound {

    setCONNECTION();

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
            + " = ? "
            + " WHERE "
            + ID.getATTRIBUTE()
            + " = "
            + entity.getId();

    try (PreparedStatement updateUser = CONNECTION.prepareStatement(update)) {

      if (entity.getId() == null) {
        throw new ElementNotFound("Id doesn't exist.");
      }
      updateUser.setString(1, entity.getName());
      updateUser.setString(2, entity.getEmail());
      updateUser.setInt(3, entity.getAge());
      updateUser.setInt(4, entity.getYearsInDegree());

      updateUser.executeUpdate();

    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(CONNECTION);
    }
  }

  @Override
  public void removeEntity(Student entity) throws ElementNotFound {

    setCONNECTION();

    String remove =
        "DELETE FROM "
            + this.TABLE_NAME
            + " WHERE "
            + ID.getATTRIBUTE()
            + " = "
            + entity.getId()
            + ";";

    try (PreparedStatement removeUser = CONNECTION.prepareStatement(remove)) {

      if (entity.getId() == null) {
        throw new ElementNotFound("Id doesn't exist.");
      }

      removeUser.executeUpdate();
      LOGGER.info("Removed.");
    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(CONNECTION);
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
        throw new ElementNotFoundException("Element not found.");
      }

    } catch (SQLException | ElementNotFoundException e) {
      e.printStackTrace();
    }
    return student;
  }

  public static void setCONNECTION() {

    try {
      CONNECTION = ConnectionPool.getInstance().getConnection();
    } catch (FullConnectionPoolException e) {
      LOGGER.error(e.getMessage());
      LOGGER.info("Trying again...");
      setCONNECTION();
    }
  }
}
