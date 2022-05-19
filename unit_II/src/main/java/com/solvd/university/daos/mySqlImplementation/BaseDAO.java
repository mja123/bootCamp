package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.mySqlImplementation.connectionPool.ConnectionPool;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.daos.mySqlImplementation.exceptions.FullConnectionPoolException;
import com.solvd.university.daos.interfaces.IBaseDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.PrivateConstructorsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static com.solvd.university.daos.mySqlImplementation.enums.EAttributesEntities.*;

public abstract class BaseDAO<T> implements IBaseDAO<T> {

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

  public ResultSet getResultSetById(Long id) {
    setConnection();

    String select =
        "SELECT * FROM " + this.TABLE_NAME + " WHERE " + ID.getATTRIBUTE() + " = " + id + ";";

    ResultSet result = null;
    PreparedStatement getEntity = null;
    try {
      getEntity = connection.prepareStatement(select);
      result = getEntity.executeQuery();
      return result;

    } catch (SQLException throwables) {
      LOGGER.error(throwables);
    } finally {
      //ConnectionPool.getInstance().goBackConnection(this.connection);
      try {
        getEntity.close();
      } catch (SQLException e) {
        LOGGER.error(e.getMessage());
      }
    }
    return null;
  }

  @Override
  public void saveEntity(T entity) {
    this.setConnection();
    ConnectionPool.getInstance().goBackConnection(this.connection);
  }

  @Override
  public void updateEntity(T entity) throws ElementNotFoundException {
    this.setConnection();

    this.reflectionClass();
    ArrayList<String> targetFields = this.reflectionFields(entity);
    String[] values = this.splitToString(entity, targetFields.size());
    String update = "UPDATE " + this.TABLE_NAME + " SET ";
//TODO: FIX THE ORDER OF THE FIELDS AND REVIEW THE CREATED_AT FIELD IN REFLECTION_CLASS. ADD THE PLACEHOLDERS AND SET IT WITH THE ELEMENTS IN VALUES ARRAY
    for(int i = 0; i < targetFields.size(); i++) {
      update = update.concat(targetFields.get(i) + " =");
      update = update.concat(values[i]);
    }
    System.out.println(update);
  }

  @Override
  public void removeEntity(Long id) throws ElementNotFoundException {
    setConnection();

    String remove =
        "DELETE FROM " + this.TABLE_NAME + " WHERE " + ID.getATTRIBUTE() + " = " + id + ";";

    try (PreparedStatement removeElement = connection.prepareStatement(remove)) {
      removeElement.executeUpdate();

    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    } finally {
      //ConnectionPool.getInstance().goBackConnection(connection);
    }
  }

  private T parseResultSet(ResultSet result) throws PrivateConstructorsException, SQLException {
    T resultObject = null;

    Constructor<T>[] constructors = (Constructor<T>[]) this.instance.getConstructors();

    /* if (constructors.length != 0) {
      Class[] parameters = null;
      for(int i = 0; i < constructors.length; i++) {
        try {
          if (constructors[i].getParameterCount() == result.getFetchSize()) {
            parameters = constructors[i].getParameterTypes();
            for(int j = 0; j < parameters.length; j++) {
              switch (parameters[j].toString()) {
                case "Long":

              }

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

    } else {
      throw new PrivateConstructorsException("We can not initialize the class, because it has private constructors");
    }*/
    /*      Integer constructorIndex = null;
    for (int i = 0; i < constructors.length; i++) {
      parameters = constructors[i].getParameterTypes();
      constructorIndex = i;
    }

      if (Arrays.stream(parameters).findAny().isEmpty()) {
        try {
         resultObject = constructors[constructorIndex].newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
          LOGGER.error(e.getMessage());
        }
      }*/
    return resultObject;
  }

  private String[] splitToString(T entity, Integer count) {

    String[] values = entity.toString().split("[A-Za-z,{}]+=", count + 2);

    for(String value : values) {
      System.out.println(value);
    }

    return values;
  }

  private ArrayList<String> reflectionFields(T entity) {
    ArrayList<String> fieldsToModify = new ArrayList<>();
    Class targetObject = entity.getClass();

    // Filtering all the fields not nulls (fields to modify in the db). After that, they are
    // iterated and compared with all the fields
    // declared in the class, if they are equals (same name) then are added in the ArrayList.
    Arrays.stream(targetObject.getDeclaredFields())
        .filter(Objects::nonNull)
        .forEach(
            f -> {

              for (String names : fields.values()) {

                if (f.getName().equals(names)) {
                  fieldsToModify.add(names);
                }
              }
            });

    return fieldsToModify;
  }

  private void reflectionClass() {
    Field[] fields;
    try {
      this.instance = (Class<T>) Class.forName(CLASS_NAME);
      fields = this.instance.getDeclaredFields();
      Arrays.stream(fields).forEach(p -> this.fields.put(p.getType(), p.getName()));

    } catch (ClassNotFoundException e) {
      LOGGER.error(e);
    }
  }

  public void setConnection() {
    try {
      this.connection = ConnectionPool.getInstance().getConnection();
    } catch (FullConnectionPoolException e) {
      LOGGER.error(e.getMessage());
      this.setConnection();
    }
  }

  //region Getters and setters
  public String getTABLE_NAME() {
    return TABLE_NAME;
  }

  public Connection getConnection() {
    return connection;
  }

  public String getCLASS_NAME() {
    return CLASS_NAME;
  }

  public Class<T> getInstance() {
    return instance;
  }

  public void setInstance(Class<T> instance) {
    this.instance = instance;
  }

  public HashMap<Object, String> getFields() {
    return fields;
  }

  public void setFields(HashMap<Object, String> fields) {
    this.fields = fields;
  }
  //endregion
}
