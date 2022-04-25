package com.solvd.solvdPractice.personal_practice.multithreading;

public class Person extends Thread {
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }
}
