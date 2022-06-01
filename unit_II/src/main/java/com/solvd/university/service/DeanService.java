package com.solvd.university.service;

import com.solvd.university.daos.interfaces.IBaseDAO;
import com.solvd.university.daos.mySqlImplementation.DeanDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.ElementNotFoundException;
import com.solvd.university.model.Dean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeanService implements IService<Dean> {
    private static final Logger LOGGER = LogManager.getLogger(DeanService.class);
    private IBaseDAO<Dean> deanDAO;

    public Dean getDeanById(Long id) {
        Dean dean = null;
        try {
          dean = deanDAO.getEntityByID(1L);
        } catch (ElementNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return dean;
    }

    @Override
    public IBaseDAO<Dean> setDao() {
        return new DeanDAO();
    }
}
