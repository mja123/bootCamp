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
import java.util.*;
import java.util.stream.Collectors;

import static com.solvd.university.daos.mySqlImplementation.enums.EAttributesEntities.*;

public abstract class BaseDAO<T> implements IBaseDAO<T> {

  private static final Logger LOGGER = LogManager.getLogger(BaseDAO.class);
  private final String TABLE_NAME;
  private Connection connection;
  private final String CLASS_NAME;
  private Class<T> instance;
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

    this.reflectionClass();
    this.reflectionFields(entity);

    String update = "UPDATE " + this.TABLE_NAME + " SET ";

    int counter = 0;
    for (String field : objectFields.keySet()) {

      if (counter == objectFields.size() - 1) {
        update = update.concat(field + " = ?;");
      } else {
        update = update.concat(field + " = ?, ");
      }
      counter++;
    }
    System.out.println(update);
    try  {
      PreparedStatement updateRow = this.connection.prepareStatement(update);
      updateRow = setPlaceHolders(updateRow);
      System.out.println(updateRow);
    } catch (SQLException throwables) {
      LOGGER.error(throwables.getMessage());
    }
    /*ArrayList<String> targetFields = this.reflectionFields(entity);
    List<String> values = this.splitToString(entity, targetFields.size());

    // TODO: FIX THE ORDER OF THE FIELDS AND REVIEW THE CREATED_AT FIELD IN REFLECTION_CLASS. ADD
    // THE PLACEHOLDERS AND SET IT WITH THE ELEMENTS IN VALUES ARRAY


    */
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

  private PreparedStatement setPlaceHolders(PreparedStatement query) throws SQLException {
    int counter = 1;
    Object value = null;
    boolean found;
    //Iterating for the datatype of the fields
    //TODO: AFTER THE FIRST ITERATION, I NEED TO PASS THE IDENTIFIER IN DECAREDOBJECTFIELDS AND IN OBJECTFIELDS
    for (String type : declaredObjectFields.values()) {
      //Iterating for the identifiers
      for (String identifier : declaredObjectFields.keySet()) {
        found = false;
        //Iterating for the identifiers of the fields
        for (String field : objectFields.keySet()) {
          //Set variable value with the value of the field.
          if( identifier.equals(field)) {
            System.out.println(identifier);
            value = objectFields.get(identifier);
            found = true;
            break;
          }
        }
        if (found)
          break;
      }

      System.out.println("Counter: " + counter + " value: " + value);
      switch (type) {
        case "java.lang.Long":
          query.setLong(counter, (Integer) value);
          break;
        case "java.lang.Integer":
          System.out.println("Integer");
          query.setInt(counter, (Integer) value);
          break;
        case "java.lang.String":
          query.setString(counter, (String) value);
          break;
        case "java.sql.Date":
          query.setDate(counter, (java.sql.Date) value);
          break;
        case "java.sql.Double":
          query.setDouble(counter, (Double) value);
          break;
        case "java.lang.Boolean":
          System.out.println("Boolean");
          query.setBoolean(counter, (Boolean) value);

          break;
      }
        //case "java.lang.Float" -> query.setFloat(counter, (Float) value);
        //default -> System.out.println("I don't know");
     /* switch (type) {
        case "java.lang.Long" -> query.setLong(counter, (Integer) value);
        //(Integer) row.get("ID")).longValue()
        case "java.lang.Integer" -> query.setInt(counter, (Integer) value);
        case "java.lang.String" -> query.setString(counter, (String) value);
        case "java.sql.Date" -> query.setDate(counter, (java.sql.Date) value);
        case "java.sql.Double" -> query.setDouble(counter, (Double) value);
        case "java.lang.Boolean" -> query.setBoolean(counter, (Boolean) value);
        case "java.lang.Float" -> query.setFloat(counter, (Float) value);
        default -> System.out.println("I don't know");
      }*/
      counter++;
    }
    return query;
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

  private List<String> splitToString(T entity, Integer count) {
    List<String> values = new ArrayList<>();

    //TODO: FIX THE REGEX, WE NEED TO REMOVE THE EMPTIES, NULLS AND COMMAS ELEMENTS.
    String[] allValues = entity.toString().split("[A-Za-z,{}]+=", count + 2);

    values = Arrays.stream(allValues).filter(Objects::nonNull).collect(Collectors.toList());

/*    for (String value : values) {
      System.out.println(value);
    }*/

    return values;
  }

  private void reflectionFields(T entity) {
    Class targetObject = entity.getClass();
    Method[] methods = targetObject.getDeclaredMethods();


    //Search for getMethods in the class comparing to 'get' + the field's identifiers
    for (Method method : methods) {
      for (String fieldName : classFields.keySet()) {
        //Capitalizing the field identifier.
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        //Searching for the getter of the fields
        if (method.getName().equals("get" + fieldName)) {
          try {
            //Filling the map with field-value pair.
            fieldName = fieldName.toLowerCase(Locale.ROOT);
            if (method.invoke(entity) != null) {
              objectFields.put(fieldName, method.invoke(entity));
            }
          } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(e);
          }
        }
      }
    }
    //Filling declaredObjectFields map with the datatype-identifier pair initialized in the target object
    for (String objectField : objectFields.keySet()) {
      for (String classField : classFields.keySet()) {
        if (objectField.equals(classField)) {
          declaredObjectFields.put(objectField, classFields.get(classField));
        }
      }
    }
    declaredObjectFields.forEach((k,v) -> LOGGER.info("Identifier: " + k + ", type: " + v));
  }

    /*for (Method method : getMethods) {
      if (method.getName().contains(classFields.))
    }
    getMethods.forEach(m -> {
      try {
        objectFields.put(, m.invoke(entity));
      } catch (IllegalAccessException | InvocationTargetException e) {
        LOGGER.error(e);
      }
    });*/
/*
    // Filtering all the fields not nulls (fields to modify in the db). After that, they are
    // iterated and compared with all the fields
    // declared in the class, if they are equals (same name) then are added in the ArrayList.
    Arrays.stream(targetObject.getDeclaredFields())
        .forEach(
            f -> {
              for (String names : classFields.values()) {

                try {
                  if ((f.getName().equals(names)) && getMethods.indexOf() != null) {
                    fieldsToModify.add(names);
                  }
                } catch (IllegalAccessException e) {
                  LOGGER.error(e);
                }
              }
            });

    classFields.forEach((k, v) -> LOGGER.info("Key: " + k + " value: " + v));
    return fieldsToModify;*/

  private void reflectionClass() {
    Field[] fields;
    try {
      this.instance = (Class<T>) Class.forName(CLASS_NAME);
      fields = this.instance.getDeclaredFields();
      //Get the declared fields and put in a map, key = type of field and value = identifier of the field
      Arrays.stream(fields).forEach(p -> this.classFields.put(p.getName(), p.getType().getTypeName()));
      //Arrays.stream(fields).forEach(p -> LOGGER.info("Name: " + p.getName() + ", type: " + p.getType().getTypeName()));
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

  // region Getters and setters
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

  public HashMap<String, String> getFields() {
    return classFields;
  }

  public void setFields(HashMap<String, String> fields) {
    this.classFields = fields;
  }
  // endregion
}
