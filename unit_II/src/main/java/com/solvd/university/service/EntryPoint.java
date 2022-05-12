package com.solvd.university.service;

import static com.solvd.university.service.enums.EEntities.*;
import com.solvd.university.service.exceptions.DataBaseProviderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.solvd.university.service.enums.EDataBaseProvider.*;


public class EntryPoint {
    private static final Logger LOGGER = LogManager.getLogger(EntryPoint.class);

  public static void main(String[] args) {
      MainService service = new MainService();

      /*try {
          service.setDataBaseProvider(MY_SQL);
      } catch (DataBaseProviderException e) {

          LOGGER.error(e.getMessage());
      }*/

      service.entityToWork(DEAN);
  }




}
