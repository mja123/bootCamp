package com.solvd.university.daos.mySqlImplementation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class ReflectionUtil<T> {
  private static final Logger LOGGER = LogManager.getLogger(ReflectionUtil.class);

  private Long id;
  public HashMap<String, Object> reflectionFields(
      T entity,
      HashMap<String, String> classFields,
      HashMap<String, Object> objectFields) {
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

  public HashMap<String, String> declaredAttributesType(
      HashMap<String, String> classFields,
      HashMap<String, Object> objectFields,
      HashMap<String, String> declaredObjectFields) {
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

  public HashMap<String, String> reflectionClass(
      Class<T> instance, String CLASS_NAME, HashMap<String, String> classFields) {
    Field[] fields;
    try {
      instance = (Class) Class.forName(CLASS_NAME);
      fields = instance.getDeclaredFields();
      // Get the declared fields and put in a map, key = type of field and value = identifier of the
      // field
      Arrays.stream(fields).forEach(p -> classFields.put(p.getName(), p.getType().getTypeName()));
    } catch (ClassNotFoundException e) {
      LOGGER.error(e);
    }
    return classFields;
  }
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
