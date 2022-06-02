package com.solvd.university.service;

import com.solvd.university.daos.interfaces.IDeanDAO;
import com.solvd.university.daos.interfaces.IDegreeDAO;
import com.solvd.university.daos.mySqlImplementation.BaseDAO;
import com.solvd.university.daos.mySqlImplementation.DeanDAO;
import com.solvd.university.daos.mySqlImplementation.DegreeDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Degree;
import com.solvd.university.model.EntityBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DegreeService implements IService {
  private static final Logger LOGGER = LogManager.getLogger(DegreeService.class);

  private IDegreeDAO degreeDAO;

  public DegreeService(String dao) {
    setDAO(dao);
  }

  @Override
  public void setDAO(String dao) {
    switch (dao) {
      case "myBatis":
        degreeDAO = SqlSessionFactoryReference.getINSTANCE()
                .getSessionFactory()
                .openSession()
                .getMapper(IDegreeDAO.class);
        break;
      case "BaseDAO":
        degreeDAO = new DegreeDAO("degrees", "com.solvd.university.model.Degree");
        break;
    }
  }
}
