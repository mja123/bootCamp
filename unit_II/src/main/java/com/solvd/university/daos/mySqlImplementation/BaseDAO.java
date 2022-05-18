package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.connectionPool.ConnectionPool;
import com.solvd.university.daos.exceptions.ElementNotFoundException;
import com.solvd.university.daos.exceptions.FullConnectionPoolException;
import com.solvd.university.daos.interfaces.IBaseDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

import static com.solvd.university.daos.mySqlImplementation.enums.EAttributesEntities.*;

public class BaseDAO<T> implements IBaseDAO<T> {

  private static final Logger LOGGER = LogManager.getLogger(BaseDAO.class);
  private final String TABLE_NAME;
  private Connection connection;
  private final String CLASS_NAME;
  private Class<T> instance;
  private HashMap<Object, String> fields;

  public BaseDAO(String tableName, String className) {
    this.TABLE_NAME = tableName;
    this.CLASS_NAME = className;
    fields = new HashMap<>();
  }

  @Override
  public T getEntityByID(Long id) throws ElementNotFoundException {
    setConnection();

    String select =
        "SELECT * FROM " + this.TABLE_NAME + " WHERE " + ID.getATTRIBUTE() + " = " + id + ";";

    try(PreparedStatement getEntity = connection.prepareStatement(select); ResultSet result = getEntity.executeQuery()) {



    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    }
  }

  @Override
  public void saveEntity(T entity) {

  }

  @Override
  public void updateEntity(T entity) throws ElementNotFoundException {}

  @Override
  public void removeEntity(Long id) throws ElementNotFoundException {
    setConnection();

    String remove = "DELETE FROM " + this.TABLE_NAME + " WHERE " + ID.getATTRIBUTE() + " = " + id + ";";

    try(PreparedStatement removeElement = connection.prepareStatement(remove)) {
      removeElement.executeUpdate();

    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally{
      ConnectionPool.getInstance().goBackConnection(connection);
    }
  }

  private T parseResultSet(ResultSet result) {
    T resultObject;

    Constructor<T>[] constructors = (Constructor<T>[]) this.instance.getConstructors();

    if (constructors.length != 0) {
      for(int i = 0; i < constructors.length; i++) {
        try {
          if (constructors[i].getParameterCount() == result.getFetchSize()) {
            Class[] parameters = constructors[i].getParameterTypes();
            for (Object parameter : parameters) {
              result.
            }
            constructors[i].newInstance()[i]()
          }
        } catch (SQLException e) {
          LOGGER.error(e.getMessage());
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }



  }

  private void reflectionClass() {
    Field[] fields;
    try {
      this.instance = Class.forName(CLASS_NAME);
      fields = this.instance.getDeclaredFields();
      Arrays.stream(fields).forEach(p ->
        this.fields.put(p.getType(), p.getName())
      );

    } catch (ClassNotFoundException e) {
      LOGGER.error(e.getMessage());
    }
  }
  private void setConnection() {
    try {
      this.connection = ConnectionPool.getInstance().getConnection();
    } catch (FullConnectionPoolException e) {
      LOGGER.error(e.getMessage());
      this.setConnection();
    }
  }
}
