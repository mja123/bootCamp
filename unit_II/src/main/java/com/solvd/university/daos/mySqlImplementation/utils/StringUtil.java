package com.solvd.university.daos.mySqlImplementation.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class StringUtil {
  public static ArrayList<String> attributeToColumn(ConcurrentHashMap<String, Object> entity) {
    ArrayList<String> formattedFields = new ArrayList<>();

    entity.forEach(
        (k, v) -> {
          // Search fields that contains uppercase letters and replaces it for the underscore + the
          // lowercase letter

          for (Character character : k.toCharArray()) {
            if (Character.isUpperCase(character)) {
              k =
                  k.replace(
                      character.toString(), "_" + character.toString().toLowerCase(Locale.ROOT));
            }
          }

            formattedFields.add(k);
        });

    return formattedFields;
  }


  public static ConcurrentHashMap<String, Object> elementsInResultSet(ResultSet result, int countOfFields) throws SQLException {
      ConcurrentHashMap<String, Object> fieldsResultSet = new ConcurrentHashMap<>();

    // Fill a HashMap with the name of the columns in the row and the respective value.
    System.out.println(result.getFetchSize());

      if (result.next()) {
          for(int i = 1; i < (countOfFields + 1) ; i++) {
              if (result.getObject(i) == null) {
                  continue;
              }
              fieldsResultSet.put(result.getMetaData().getColumnLabel(i), result.getObject(i));
          }
      }
      fieldsResultSet.forEach((k, v) -> {
          if (k.contains("_")) {
              //The index of the letter after the underscore (first letter in the joined word).
              int indexOfNextWord = k.indexOf("_") + 1;
              char firstLetter = k.charAt(indexOfNextWord);

              String formattedField = k.replace("_" + firstLetter, Character.toString(firstLetter).toUpperCase());
              fieldsResultSet.put(formattedField, v);
              fieldsResultSet.remove(k);
          }

      });

      return fieldsResultSet;
  }
}
