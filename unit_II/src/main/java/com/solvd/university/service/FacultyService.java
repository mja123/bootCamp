package com.solvd.university.service;

import com.solvd.university.daos.interfaces.IDegreeDAO;
import com.solvd.university.daos.interfaces.IFacultyDAO;
import com.solvd.university.daos.mySqlImplementation.DegreeDAO;
import com.solvd.university.daos.mySqlImplementation.FacultyDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FacultyService implements IService {
    private static final Logger LOGGER = LogManager.getLogger(FacultyService.class);
    private IFacultyDAO facultyDAO;

    public FacultyService(String dao) {
        setDAO(dao);
    }

    @Override
    public void setDAO(String dao) {
        switch (dao) {
            case "myBatis":
                facultyDAO = SqlSessionFactoryReference.getINSTANCE()
                        .getSessionFactory()
                        .openSession()
                        .getMapper(IFacultyDAO.class);
                break;
            case "BaseDAO":
                facultyDAO = new FacultyDAO("faculties", "com.solvd.university.model.Faculty");
                break;
        }
    }
}
