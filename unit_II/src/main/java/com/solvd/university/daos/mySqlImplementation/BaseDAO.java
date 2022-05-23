package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.mySqlImplementation.connectionPool.ConnectionPool;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.daos.mySqlImplementation.exceptions.FullConnectionPoolException;
import com.solvd.university.daos.interfaces.IBaseDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.PrivateConstructorsException;
import com.solvd.university.daos.mySqlImplementation.utils.ReflectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.solvd.university.daos.mySqlImplementation.enums.EAttributesEntities.*;
import static com.solvd.university.daos.mySqlImplementation.utils.StringUtil.parseAttribute;

public abstract class BaseDAO<T> implements IBaseDAO<T> {

  //TODO:
  /*
  - ADD REACTIVE COMPONENTS IN POOL CONNECTION
  - CREATE METHOD
   */

  private static final Logger LOGGER = LogManager.getLogger(BaseDAO.class);
  private final String TABLE_NAME;
  private Connection connection;
  private final String CLASS_NAME;
  private Class<T> instance;
  private ReflectionUtil<T> reflectionUtil = new ReflectionUtil<>();
  private Long id;
  private HashMap<String, String> classFields;
  private HashMap<String, Object> objectFields;
  private HashMap<String, String> declaredObjectFields;

  public BaseDAO(String tableName, String className) {
    this.TABLE_NAME = tableName;
    this.CLASS_NAME = className;
    classFields = new HashMap<>();
    objectFields = new HashMap<>();
    declaredObjectFields = new HashMap<>();
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
      // ConnectionPool.getInstance().goBackConnection(this.connection);
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

    this.classFields = reflectionUtil.reflectionClass(instance, CLASS_NAME, classFields);
    this.objectFields = reflectionUtil.reflectionFields(entity, classFields, objectFields);
    this.id = reflectionUtil.getId();

    String update = "UPDATE " + this.TABLE_NAME + " SET ";

    int counter = 0;

    if(this.id == null) {
      throw new ElementNotFoundException("You have to send the record's id.");
    }

    ArrayList<String> formatedFields = parseAttribute(objectFields);

    for (String field : formatedFields) {

      if (counter == formatedFields.size() - 1) {
        update = update.concat(field + " = ? WHERE " + ID.getATTRIBUTE() + " = " + this.id);
      } else {
        update = update.concat(field + " = ?, ");
      }
      counter++;
    }

    try  {
      PreparedStatement updateRow = this.connection.prepareStatement(update);
      setPlaceHolders(updateRow);
      System.out.println(updateRow);
      updateRow.executeUpdate();
    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    }
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
      // ConnectionPool.getInstance().goBackConnection(connection);
    }
  }

  //private Long

  private void setPlaceHolders(PreparedStatement query) throws SQLException {
    int counter = 1;
    Object value = null;
    boolean found;
    declaredObjectFields = reflectionUtil.declaredAttributesType(classFields, objectFields, declaredObjectFields);
    ArrayList<String> lastValue = new ArrayList<>();

    //Iterating for the datatype of the fields
    for (String type : declaredObjectFields.values()) {
      //Iterating for the identifiers
      for (String identifier : declaredObjectFields.keySet()) {
        found = false;
        //Iterating for the identifiers of the fields
        for (String field : objectFields.keySet()) {
          //Set variable value with the value of the field.
          if( identifier.equals(field)) {
            //Search if the identifier is not someone that was used.
            if (!lastValue.contains(identifier)) {
              value = objectFields.get(identifier);
              lastValue.add(identifier);
              found = true;
              break;
            }
          }
        }
        if (found)
          break;
      }

      switch (type) {
        case "java.lang.Long" -> query.setLong(counter, (Long) value);
        case "java.lang.Integer" -> query.setInt(counter, (Integer) value);
        case "java.lang.String" -> query.setString(counter, (String) value);
        case "java.sql.Date" -> query.setDate(counter, (java.sql.Date) value);
        case "java.sql.Double" -> query.setDouble(counter, (Double) value);
        case "java.lang.Boolean" -> query.setBoolean(counter, (Boolean) value);
        case "java.lang.Float" -> query.setFloat(counter, (Float) value);
        default -> System.out.println("I don't know");
      }
      counter++;
    }
  }


  //In process
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

  private void setConnection() {
    try {
      this.connection = ConnectionPool.getInstance().getConnection();
    } catch (FullConnectionPoolException e) {
      LOGGER.error(e.getMessage());
      this.setConnection();
    }
  }

  // region Getters and setters
  public String getTABLE_NAME() {
    return TABLE_NAME;
  }

  public Connection getConnection() {
    this.setConnection();
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

  public HashMap<String, String> getFields() {
    return classFields;
  }

  public void setFields(HashMap<String, String> fields) {
    this.classFields = fields;
  }
  // endregion


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseDAO<?> baseDAO = (BaseDAO<?>) o;
    return Objects.equals(TABLE_NAME, baseDAO.TABLE_NAME) && Objects.equals(connection, baseDAO.connection) && Objects.equals(CLASS_NAME, baseDAO.CLASS_NAME) && Objects.equals(instance, baseDAO.instance) && Objects.equals(classFields, baseDAO.classFields) && Objects.equals(objectFields, baseDAO.objectFields) && Objects.equals(declaredObjectFields, baseDAO.declaredObjectFields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(TABLE_NAME, connection, CLASS_NAME, instance, classFields, objectFields, declaredObjectFields);
  }
}
