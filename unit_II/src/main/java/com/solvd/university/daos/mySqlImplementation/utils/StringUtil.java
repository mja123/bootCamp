package com.solvd.university.daos.mySqlImplementation.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class StringUtil {
  public static ArrayList<String> parseAttribute(HashMap<String, Object> entity) {
    ArrayList<String> formatedFields = new ArrayList<>();

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

          formatedFields.add(k);
        });

    return formatedFields;
  }
}
