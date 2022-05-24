package com.solvd.university.daos.mySqlImplementation;

import com.solvd.university.daos.mySqlImplementation.connectionPool.ConnectionPool;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.daos.mySqlImplementation.exceptions.FullConnectionPoolException;
import com.solvd.university.daos.interfaces.IBaseDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.PrivateConstructorsException;
import com.solvd.university.daos.mySqlImplementation.utils.ReflectionUtil;
import com.solvd.university.daos.mySqlImplementation.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.solvd.university.daos.mySqlImplementation.enums.EAttributesEntities.*;
import static com.solvd.university.daos.mySqlImplementation.utils.StringUtil.attributeToColumn;

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
  private final ReflectionUtil<T> REFLECTION = new ReflectionUtil<>();
  private Long id;
  private ConcurrentHashMap<String, String> classFields;
  private ConcurrentHashMap<String, Object> objectFields;
  private ConcurrentHashMap<String, String> declaredObjectFields;

  public BaseDAO(String tableName, String className) {
    this.TABLE_NAME = tableName;
    this.CLASS_NAME = className;
    classFields = new ConcurrentHashMap<>();
    objectFields = new ConcurrentHashMap<>();
    declaredObjectFields = new ConcurrentHashMap<>();
    this.setInstance();
  }

  public T getResultSetById(Long id) {
    setConnection();

    String select =
        "SELECT * FROM " + this.TABLE_NAME + " WHERE " + ID.getATTRIBUTE() + " = " + id + ";";

    System.out.println(select);
    try {
      PreparedStatement getEntity = connection.prepareStatement(select);
      ResultSet result = getEntity.executeQuery();

      return this.parseResultSet(result, instance, classFields);

    } catch (SQLException | PrivateConstructorsException throwables) {
      LOGGER.error(throwables);
    } finally {
      // ConnectionPool.getInstance().goBackConnection(this.connection);
/*      try {
        getEntity.close();
      } catch (SQLException e) {
        LOGGER.error(e.getMessage());
      }*/
    }
    return null;
  }

  @Override
  public void saveEntity(T entity) {
    this.setConnection();
    String create = "INSERT INTO " + this.TABLE_NAME + "(";

    if (classFields.isEmpty()) {
      REFLECTION.reflectionClass(this.instance, this.CLASS_NAME, this.classFields);
    }
    REFLECTION.reflectionFields(entity, classFields, objectFields);

    ArrayList<String> columnNames = attributeToColumn(objectFields);

    for (String column : columnNames) {
      //Compare if the current element is the last one.
      if (column.equals(columnNames.get(columnNames.size() - 1))) {
        create = create.concat(column + ") VALUES (");
      } else {
        create = create.concat(column + ", ");
      }
    }
    for (String values : columnNames) {

      if (values.equals(columnNames.get(columnNames.size() - 1))) {
        create = create.concat("?);");
      } else {
        create = create.concat("?,");
      }
    }

    try(PreparedStatement createEntity = this.connection.prepareStatement(create)) {
      this.setPlaceHolders(createEntity);

      System.out.println(createEntity);

      createEntity.execute();
    } catch (SQLException throwables) {

      LOGGER.error(throwables);
    } finally {

      ConnectionPool.getInstance().goBackConnection(this.connection);
      this.objectFields.clear();
      this.declaredObjectFields.clear();
    }
  }

  @Override
  public void updateEntity(T entity) throws ElementNotFoundException {
    this.setConnection();

    if (classFields.isEmpty()) {
      REFLECTION.reflectionClass(this.instance, this.CLASS_NAME, this.classFields);
    }

    this.objectFields = REFLECTION.reflectionFields(entity, classFields, objectFields);
    this.id = REFLECTION.getId();

    String update = "UPDATE " + this.TABLE_NAME + " SET ";

    int counter = 0;

    if(this.id == null) {
      throw new ElementNotFoundException("You have to send the record's id.");
    }

    ArrayList<String> formatedFields = attributeToColumn(objectFields);

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
    } finally {
      ConnectionPool.getInstance().goBackConnection(this.connection);
      this.declaredObjectFields.clear();
      this.objectFields.clear();
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
       ConnectionPool.getInstance().goBackConnection(connection);
    }
  }

  private void setPlaceHolders(PreparedStatement query) throws SQLException {
    int counter = 1;
    Object value = null;
    boolean found;
    declaredObjectFields = REFLECTION.declaredAttributesType(classFields, objectFields, declaredObjectFields);
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
    lastValue.clear();
  }


  //In process
  private T parseResultSet(ResultSet result, Class<T> instance, ConcurrentHashMap<String, String> classFields) throws PrivateConstructorsException, SQLException {
    T resultObject = null;

    Constructor<T>[] constructors = (Constructor<T>[]) instance.getConstructors();

    //It is instantiating an object with the default constructor.
     if (constructors.length != 0) {
       for (Constructor<T> constructor : constructors) {
         try {
           if (constructor.getParameterCount() == 0) {
             resultObject = constructor.newInstance();
             break;
           }
         } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
           e.printStackTrace();
         }
       }
    } else {
      throw new PrivateConstructorsException("We can not initialize the class, because it has private constructors");
    }

   Method[] methods = resultObject.getClass().getDeclaredMethods();

    if (classFields.isEmpty()) {
      classFields = REFLECTION.reflectionClass(this.instance, this.CLASS_NAME, classFields);
    }

   objectFields = StringUtil.elementsInResultSet(result, classFields.size());
//TODO: RESOLVE THE TYPES PROBLEMS IN ID (in objectFields are like Integer but we need Long types) and Dates types
   for(Method method : methods) {
     if (!(method.getName().substring(0, 3).equals("set"))) {
       continue;
     }
     try {
       String keyField = method.getName().substring(3, method.getName().length());
       keyField = keyField.substring(0, 1).toLowerCase(Locale.ROOT) + keyField.substring(1);

       Object parameter = objectFields.get(keyField);
       System.out.println(keyField);
       System.out.println(parameter);
       method.invoke(resultObject, parameter);
     } catch (IllegalAccessException | InvocationTargetException e) {
       LOGGER.error(e.getMessage());
     }
   }
   return resultObject = instance.cast(resultObject);
  }

  private void setConnection() {
    try {
      this.connection = ConnectionPool.getInstance().getConnection();
    } catch (FullConnectionPoolException e) {
      LOGGER.error(e.getMessage());
      this.setConnection();
    }
  }

  private void setInstance() {
    try {
      instance = (Class) Class.forName(CLASS_NAME);
    } catch (ClassNotFoundException e) {
      LOGGER.error(e);
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

  public ConcurrentHashMap<String, String> getFields() {
    return classFields;
  }

  public void setFields(ConcurrentHashMap<String, String> fields) {
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
