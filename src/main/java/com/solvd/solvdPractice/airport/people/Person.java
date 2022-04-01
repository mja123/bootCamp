package com.solvd.solvdPractice.airport.people;

public abstract class Person {
    protected String name;
    protected Integer age;
    protected String nationality;
    protected String occupation;

    //region constructors
    public Person() {

    }
    public Person(String name, Integer age, String nationality, String occupation) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.occupation = occupation;
    }
    //endregion

    //region getters and setters

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    //endregion


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
