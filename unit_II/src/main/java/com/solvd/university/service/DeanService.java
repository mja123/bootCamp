package com.solvd.university.service;

import com.solvd.university.daos.interfaces.IBaseDAO;
import com.solvd.university.daos.interfaces.IDeanDAO;
import com.solvd.university.daos.interfaces.ITeacherDAO;
import com.solvd.university.daos.mySqlImplementation.DeanDAO;
import com.solvd.university.daos.mySqlImplementation.TeacherDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Dean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeanService implements IService {
    private static final Logger LOGGER = LogManager.getLogger(DeanService.class);
    private IDeanDAO deanDAO;

    public DeanService(String dao) {
        setDAO(dao);
    }

    @Override
    public void setDAO(String dao) {
        switch (dao) {
            case "myBatis":
                deanDAO = SqlSessionFactoryReference.getINSTANCE()
                        .getSessionFactory()
                        .openSession()
                        .getMapper(IDeanDAO.class);
                break;
            case "BaseDAO":
                deanDAO = new DeanDAO("deans", "com.solvd.university.model.Dean");
                break;
        }
    }

}
