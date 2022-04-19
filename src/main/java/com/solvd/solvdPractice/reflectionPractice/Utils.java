package com.solvd.solvdPractice.reflectionPractice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;
import java.util.Arrays;

public class Utils {
    private static final Logger LOGGER = LogManager.getLogger(Utils.class);
    private Class targetClass;

    public void setClass(String className) throws ClassNotFoundException {
        this.targetClass = Class.forName(className);
    }

    /*
    public T [] getElements(EclassElements element) {

        switch (element) {
            case CONSTRUCTOR:
                return (T[]) targetClass.getDeclaredConstructors();
            case FIELD:
                return (T[]) targetClass.getDeclaredFields();
            default:
                return (T[]) targetClass.getDeclaredMethods();
        }
    }

     */



    public Constructor[] getConstructors() {

        return  this.targetClass.getDeclaredConstructors();
    }

    public Method[] getMethods() {

        return this.targetClass.getDeclaredMethods();
    }

    public Field[] getFields() {

        return this.targetClass.getDeclaredFields();

    }

    public Class[] getParameters(Integer index, EclassElements element) {

        switch (element) {
            case METHOD:
                return this.getMethods()[index].getParameterTypes();
            default:
                return this.getConstructors()[index].getParameterTypes();
        }

    }

    public Class getTargetClass() {
        return targetClass;
    }
}
