package com.solvd.university.daos.mySqlImplementation.exceptions;

public class FullConnectionPoolException extends Exception {
    public FullConnectionPoolException(String message) {
        super(message);
    }
}
