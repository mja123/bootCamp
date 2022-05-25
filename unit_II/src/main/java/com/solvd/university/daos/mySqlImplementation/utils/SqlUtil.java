package com.solvd.university.daos.mySqlImplementation.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class SqlUtil {

  public static ConcurrentHashMap<String, Object> elementsInResultSet(
      ResultSet result, int countOfFields) throws SQLException {

    ConcurrentHashMap<String, Object> fieldsResultSet = new ConcurrentHashMap<>();

    // Fill a HashMap with the name of the columns in the row and the respective value.
    if (result.next()) {
      for (int i = 1; i < (countOfFields + 1); i++) {
        if (result.getObject(i) == null) {
          continue;
        }
        fieldsResultSet.put(result.getMetaData().getColumnLabel(i), result.getObject(i));
      }
    }
    return fieldsResultSet;
  }
}