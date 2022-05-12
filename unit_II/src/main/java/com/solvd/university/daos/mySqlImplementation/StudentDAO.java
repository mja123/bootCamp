package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.connectionPool.ConnectionPool;
import com.solvd.university.daos.exceptions.ElementNotFoundException;
import com.solvd.university.daos.exceptions.FullConnectionPoolException;
import com.solvd.university.daos.interfaces.IStudentDAO;
import com.solvd.university.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.solvd.university.daos.mySqlImplementation.enums.EAttributesEntities.*;

import java.sql.*;

public class StudentDAO implements IStudentDAO {

  private static final Logger LOGGER = LogManager.getLogger(StudentDAO.class);
  private static Connection connection = null;
  private final String TABLE_NAME = "students";

  @Override
  public Student getEntityByID(Long id) throws ElementNotFoundException {
    String selectOne = "SELECT * FROM " + this.TABLE_NAME + " WHERE id = " + id + ";";
    Student studentFound = null;

    setConnection();

    try (PreparedStatement getStudent = connection.prepareStatement(selectOne)) {

      if (id == null) {
        throw new ElementNotFoundException("Id doesn't exist.");
      }

      ResultSet result = getStudent.executeQuery();
      studentFound = this.covertInStudent(result);

      result.close();

    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(connection);
    }
    return studentFound;
  }

  @Override
  public void saveEntity(Student entity) {

    setConnection();

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
        connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
      connection.setAutoCommit(false);
      insertStudent.setString(1, entity.getName());
      insertStudent.setString(2, entity.getEmail());
      insertStudent.setInt(3, entity.getAge());
      insertStudent.setInt(4, entity.getYearsInDegree());

      insertStudent.executeUpdate();

      ResultSet keys = insertStudent.getGeneratedKeys();
      if (keys.next()) {
        entity.setId(keys.getLong(1));
      } else {
        throw new ElementNotFoundException("The id wasn't generated");
      }

      keys.close();

      connection.commit();

    } catch (SQLException | ElementNotFoundException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(connection);
    }
  }

  @Override
  public void updateEntity(Student entity) throws ElementNotFoundException {

    setConnection();

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

    try (PreparedStatement updateUser = connection.prepareStatement(update)) {

      if (entity.getId() == null) {
        throw new ElementNotFoundException("Id doesn't exist.");
      }
      updateUser.setString(1, entity.getName());
      updateUser.setString(2, entity.getEmail());
      updateUser.setInt(3, entity.getAge());
      updateUser.setInt(4, entity.getYearsInDegree());

      updateUser.executeUpdate();

    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(connection);
    }
  }

  @Override
  public void removeEntity(Long id) throws ElementNotFoundException {

    setConnection();

    String remove =
        "DELETE FROM " + this.TABLE_NAME + " WHERE " + ID.getATTRIBUTE() + " = " + id + ";";

    try (PreparedStatement removeUser = connection.prepareStatement(remove)) {

      if (id == null) {
        throw new ElementNotFoundException("Id doesn't exist.");
      }

      removeUser.executeUpdate();
      LOGGER.info("Removed.");
    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(connection);
    }
  }

  private Student covertInStudent(ResultSet result) {
    Student student = new Student();
    try {
      if (result.next()) {
        student.setId(result.getLong(ID.getATTRIBUTE()));
        student.setName(result.getString(NAME.getATTRIBUTE()));
        student.setEmail(result.getString(EMAIL.getATTRIBUTE()));
        student.setAge(result.getInt(AGE.getATTRIBUTE()));
        student.setYearsInDegree(result.getInt(YEARS_IN_DEGREE.getATTRIBUTE()));
        student.setCreatedAt(result.getDate(CREATED_AT.getATTRIBUTE()));

      } else {
        throw new ElementNotFoundException("Element not found.");
      }

    } catch (SQLException | ElementNotFoundException e) {
      e.printStackTrace();
    }
    return student;
  }

  private static void setConnection() {

    try {
      connection = ConnectionPool.getInstance().getConnection();
    } catch (FullConnectionPoolException e) {
      LOGGER.error(e.getMessage());
      LOGGER.info("Trying again...");
      setConnection();
    }
  }
}
