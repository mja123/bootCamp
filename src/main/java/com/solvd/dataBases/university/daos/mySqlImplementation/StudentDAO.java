package com.solvd.dataBases.university.daos.mySqlImplementation;

import com.solvd.dataBases.university.daos.interfaces.IStudentDAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class StudentDAO implements IStudentDAO {

    private static FileReader reader = null;
    private static Properties properties = new Properties();
    private static Connection CONNECTION = null;

    public static void setCONNECTION() {
        try {
            reader =  new FileReader(System.getenv("PROPERTIES"));
            properties.load(reader);
            CONNECTION = DriverManager.getConnection(properties.getProperty("URL") + "?" + "user=" +
                    properties.getProperty("USER") + "&password=" + properties.getProperty("PASSWORD"));
            System.out.println("Connection open");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public StudentDAO getEntityByID(Long id) {
        return null;
    }

    @Override
    public void saveEntity(StudentDAO entity) {

    }

    @Override
    public void updateEntity(StudentDAO entity) {

    }

    @Override
    public void removeEntity(StudentDAO entity) {

    }

    public static void main(String[] args) {
        StudentDAO.setCONNECTION();
    }
}
