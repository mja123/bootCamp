package com.solvd.university.service;

import com.solvd.university.model.Dean;
import com.solvd.university.service.DeanService;
import com.solvd.university.service.enums.EDataBaseProvider;
import static com.solvd.university.service.enums.EEntities.*;

import com.solvd.university.service.enums.EEntities;
import com.solvd.university.service.exceptions.DataBaseProviderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.solvd.university.service.enums.EDataBaseProvider.*;

public class MainService {
    private static final Logger LOGGER = LogManager.getLogger(MainService.class);
    private EDataBaseProvider DAOFactory;
    private String entity;

    public MainService() {
    }

    public MainService(EDataBaseProvider DAOFactory) {
        this.DAOFactory = DAOFactory;
    }

    public void setDataBaseProvider(EDataBaseProvider dataBaseProvider) throws DataBaseProviderException {
        switch(dataBaseProvider) {
            case MY_SQL:
                DAOFactory = MY_SQL;
            case POSTGRES:
                DAOFactory = POSTGRES;
            default:
                throw new DataBaseProviderException("Provider not found");
        }
    }

    public void entityToWork(EEntities entity) {
        try {
            setDataBaseProvider(MY_SQL);
        } catch (DataBaseProviderException e) {
            LOGGER.error(e.getMessage());
        }

        switch (entity) {
            case DEAN:
                DeanService deanService = new DeanService();
                break;
            case SUBJECT:
                SubjectService subjectService = new SubjectService();
                //subjectService.getSubject();

        }
    }



}
