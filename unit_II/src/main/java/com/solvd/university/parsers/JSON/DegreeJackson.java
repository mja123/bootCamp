package com.solvd.university.parsers.JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.university.model.Degree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class DegreeJackson {
  private static final Logger LOGGER = LogManager.getLogger(DegreeJackson.class);

  public static void main(String[] args) {
    ObjectMapper objectMapper = new ObjectMapper();
    marshalling(objectMapper);
    unMarshalling(objectMapper);
  }

  static void marshalling(ObjectMapper objectMapper) {
    Degree degree = new Degree(1L, "Informatics", 4, 1L);
    File jsonDegree = new File(System.getenv("JSON_DEGREE"));
    try {
      objectMapper.writeValue(jsonDegree, degree);
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
    }
  }

  static void unMarshalling(ObjectMapper objectMapper) {
    Degree degree;
    File jsonDegree = new File(System.getenv("JSON_DEGREE"));

    try {
      degree = objectMapper.readValue(jsonDegree, Degree.class);
      LOGGER.info(degree);
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
    }

  }
}
