package com.solvd.university.daos.exceptions;

public class FullConnectionPoolException extends Exception {
    public FullConnectionPoolException(String message) {
        super(message);
    }
}
