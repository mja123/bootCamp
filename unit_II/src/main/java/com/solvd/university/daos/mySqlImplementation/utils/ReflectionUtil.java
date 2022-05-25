package com.solvd.university.daos.mySqlImplementation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectionUtil<T> {
  private static final Logger LOGGER = LogManager.getLogger(ReflectionUtil.class);
  private Class<T> instance;
  private Long id;

  public ReflectionUtil(String className) {
    targetClass(className);
  }

  public ConcurrentHashMap<String, Object> reflectionFields(
      T entity,
      ConcurrentHashMap<String, String> classFields,
      ConcurrentHashMap<String, Object> objectFields) {

    Class targetObject = entity.getClass();
    Method[] methods = targetObject.getDeclaredMethods();

    // Search for getMethods in the class comparing to 'get' + the field's identifiers
    for (Method method : methods) {
      // Skip all the no getters methods
      if (!(method.getName().substring(0, 3).equals("get"))) {
        continue;
      }
      for (String fieldName : classFields.keySet()) {
        // Capitalizing the field identifier.
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        // Searching for the getter of the fields
        if (method.getName().equals("get" + fieldName)) {
          try {
            // Filling the map with field-value pair.
            fieldName = fieldName.substring(0, 1).toLowerCase(Locale.ROOT) + fieldName.substring(1);
            if (method.invoke(entity) != null) {
              if (fieldName.equals("id")) {
                id = (Long) method.invoke(entity);
                continue;
              }
              objectFields.put(fieldName, method.invoke(entity));
              break;
            }
          } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(e);
          }
        }
      }
    }
    return objectFields;
  }

  public ConcurrentHashMap<String, String> declaredAttributesType(
      ConcurrentHashMap<String, String> classFields,
      ConcurrentHashMap<String, Object> objectFields,
      ConcurrentHashMap<String, String> declaredObjectFields) {

    // Filling declaredObjectFields map with the datatype-identifier pair initialized in the target
    // object
    for (String objectField : objectFields.keySet()) {
      for (String classField : classFields.keySet()) {
        if (objectField.equals(classField)) {
          declaredObjectFields.put(objectField, classFields.get(classField));
        }
      }
    }
    return declaredObjectFields;
  }

  public ConcurrentHashMap<String, String> reflectionClass(ConcurrentHashMap<String, String> classFields) {
    Field[] fields;

    fields = instance.getDeclaredFields();
    // Get the declared fields and put in a map, key = type of field and value = identifier of the
    // field
    Arrays.stream(fields).forEach(p -> classFields.put(p.getName(), p.getType().getTypeName()));

    return classFields;
  }

  public void classFieldsTypes(ConcurrentHashMap<String, Object> classFields) {
    Field[] fields;

    fields = instance.getDeclaredFields();
    // Get the declared fields and put in a map, key = type of field and value = identifier of the
    // field
    Arrays.stream(fields).forEach(p -> classFields.put(p.getName(), p.getType()));
  }
  public void castColumnsToAttributes(
      ConcurrentHashMap<String, Object> columns) {

      ConcurrentHashMap<String, Object> fieldsTypes = new ConcurrentHashMap<>();
      ArrayList<String> fieldsUsed = new ArrayList<>();
      classFieldsTypes(fieldsTypes);

      for (String column : columns.keySet()) {
        for (String field : fieldsTypes.keySet()) {
          if (fieldsUsed.contains(field)) {
            continue;
          }
          if (column.equals(field)) {
            //TODO: FIND THE WAY TO CAST THE COLUMN VALUE TO THE TYPE IN FIELDSTYPES.
          System.out.println("Column: " + column + "cast: " +fieldsTypes.get(field).getClass().cast(columns.get(column)).getClass());
            columns.put(column, columns.get(column).getClass().cast(fieldsTypes.get(field)));
            fieldsUsed.add(field);
          }
        }
      }
  }
  private void targetClass(String className) {
    if (instance == null) {
      try {
        instance = (Class) Class.forName(className);
      } catch (ClassNotFoundException e) {
        LOGGER.error(e);
      }
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Class<T> getInstance() {
    return instance;
  }

  public void setInstance(Class<T> instance) {
    this.instance = instance;
  }
}
