package com.solvd.solvdPractice.reflectionPractice;

import com.solvd.solvdPractice.enums.BachillerDegree;
import com.solvd.solvdPractice.enums.EDegrees;
import com.solvd.solvdPractice.enums.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class ReflectionProgram {

    private static final Logger LOGGER = LogManager.getLogger(ReflectionProgram.class);

    public static void main(String[] args) {
        Utils utils = new Utils();


        try {
            utils.setClass("com.solvd.solvdPractice.enums.Student");
            if (utils.getTargetClass() == null) {
                throw new ClassNotFoundException("The class doesn't exist.");
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }


        //region Parameters in constructors and methods.

        /*

        long countOfConstructors = Arrays.stream(utils.getConstructors()).count();

        for (int i = 0; i < countOfConstructors; i++) {

            LOGGER.info("Constructor: " + i);
            Arrays.stream(utils.getParameters(i, EclassElements.CONSTRUCTOR))
                    .forEach(p -> LOGGER.info("Parameters in the constructor: " + p.getName()));
        }

        long countOfMethods = Arrays.stream(utils.getMethods()).count();

        for (int i = 0; i < countOfMethods; i++) {

            LOGGER.info("Method: " + i);
            Arrays.stream(utils.getParameters(i, EclassElements.METHOD))
                    .forEach(p -> LOGGER.info("Parameters in the method: " + p.getName()));
        }

         */
        //endregion

        //region fields
        /*
        LOGGER.info("Fields: ");
        Arrays.stream(utils.getFields())
                .forEach(field -> LOGGER.info(field.getName()));

         */
        //endregion

        //region methods
        /*
        LOGGER.info("Methods: ");
        Arrays.stream(utils.getMethods())
                .forEach(method -> LOGGER.info(method.getName()));

         */
        //endregion

        //region constructors
        /*
        Arrays.stream(utils.getConstructor())
                .forEach(p -> LOGGER.info(p.toString()));

         */
        //endregion

        //region Student instantiation
        long  conutOfParameters = Arrays.stream(utils.getParameters(0, EclassElements.CONSTRUCTOR)).count();


        try {
            Student newStudent = (Student) utils.getConstructors()[0].newInstance(new BachillerDegree(EDegrees.INFORMATICS), 2, 5.4);
            LOGGER.info(newStudent.goToClass());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(e.getMessage());
        }
        //endregion

    }
}
