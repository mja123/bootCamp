package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.mySqlImplementation.connectionPool.ConnectionPool;
import com.solvd.university.daos.mySqlImplementation.connectionPool.IListener;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.daos.mySqlImplementation.exceptions.FullConnectionPoolException;
import com.solvd.university.daos.interfaces.IDeanDAO;
import com.solvd.university.model.Dean;
import static com.solvd.university.daos.mySqlImplementation.enums.EAttributesEntities.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;

public class DeanDAO extends BaseDAO<Dean> implements IDeanDAO, IListener {

  private static final Logger LOGGER = LogManager.getLogger(DeanDAO.class);
  private final String TABLE_NAME = "deans";
  private Connection connection = null;

  //Subscribing to the ConnectionPool's notification list.
  public DeanDAO(String tableName, String className) {
    super(tableName, className);
    this.subscribeToNotifications();
  }

  @Override
  public Dean getEntityByID(Long id) throws ElementNotFoundException {
    Dean dean;
    this.setConnection();

    String selectOne =
        "SELECT * FROM " + this.TABLE_NAME + " WHERE " + ID.getATTRIBUTE() + " = " + id + ";";

    try (PreparedStatement getDean = connection.prepareStatement(selectOne)) {
      ResultSet result = getDean.executeQuery();

      dean = this.covertInDean(result);

      if (dean.getId() == null) {
        throw new ElementNotFoundException("Dean not found");
      }
      return dean;
    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(connection);
    }
    return null;
  }

  @Override
  public void saveEntity(Dean entity) {

    this.setConnection();

    String insertQuery =
        "INSERT INTO "
            + this.TABLE_NAME
            + "("
            + NAME.getATTRIBUTE()
            + ", "
            + AGE.getATTRIBUTE()
            + ", "
            + PROFESSION.getATTRIBUTE()
            + ", "
            + FACULTIES_ID.getATTRIBUTE()
            + ") VALUES(?, ?, ?, ?);";

    try (PreparedStatement newDean = connection.prepareStatement(insertQuery)) {
      newDean.setString(1, entity.getName());
      newDean.setInt(2, entity.getAge());
      newDean.setString(3, entity.getProfession());
      newDean.setLong(4, entity.getFacultiesId());

      newDean.executeUpdate();

    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(connection);
    }
  }

  @Override
  public void updateEntity(Dean entity) throws ElementNotFoundException {
    setConnection();

    String updateQuery =
        "UPDATE "
            + this.TABLE_NAME
            + " SET "
            + NAME.getATTRIBUTE()
            + " = ?, "
            + AGE.getATTRIBUTE()
            + " = ?,"
            + PROFESSION.getATTRIBUTE()
            + " = ?,"
            + FACULTIES_ID.getATTRIBUTE()
            + " = ? WHERE "
            + ID.getATTRIBUTE()
            + " = ?;";

    try (PreparedStatement updateDean = connection.prepareStatement(updateQuery)) {
      updateDean.setString(1, entity.getName());
      updateDean.setInt(2, entity.getAge());
      updateDean.setString(3, entity.getProfession());
      updateDean.setLong(4, entity.getFacultiesId());
      updateDean.setLong(5, entity.getId());

      updateDean.executeUpdate();
    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      ConnectionPool.getInstance().goBackConnection(connection);
    }
  }

  @Override
  public void removeEntity(Long id) throws ElementNotFoundException {
    setConnection();

    String removeQuery =
        "DELETE FROM " + this.TABLE_NAME + " WHERE " + ID.getATTRIBUTE() + " = " + id + ";";

    try (PreparedStatement removeDean = connection.prepareStatement(removeQuery)) {

      if (id == null) {
        throw new ElementNotFoundException("Dean id doesn't exist");
      }
      removeDean.executeUpdate();

    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    }
  }

  private Dean covertInDean(ResultSet result) {
    Dean dean = new Dean();
    try {
      if (result.next()) {
        dean.setId(result.getLong(ID.getATTRIBUTE()));
        dean.setName(result.getString(NAME.getATTRIBUTE()));
        dean.setAge(result.getInt(AGE.getATTRIBUTE()));
        dean.setProfession(result.getString(PROFESSION.getATTRIBUTE()));
        dean.setCreatedAt(result.getDate(CREATED_AT.getATTRIBUTE()));
        dean.setFacultiesId(result.getLong(FACULTIES_ID.getATTRIBUTE()));

      } else {
        throw new ElementNotFoundException("Dean not found.");
      }

    } catch (SQLException | ElementNotFoundException e) {
      e.printStackTrace();
    }
    return dean;
  }

  private void setConnection() {
    try {
      this.connection = ConnectionPool.getInstance().getConnection();

    } catch (FullConnectionPoolException | SQLException | IOException e) {
      LOGGER.error(e.getMessage());
      LOGGER.info("Trying again...");
      //try {
        //this.wait();
      //} catch (InterruptedException ex) {
     //   LOGGER.error(ex.getMessage());
     // }
    }
  }

  private void subscribeToNotifications() {
    ConnectionPool.getInstance().addListener(this);
  }

  @Override
  public void notification() {
    if (connection == null) {
      this.setConnection();
    }
  }
}
