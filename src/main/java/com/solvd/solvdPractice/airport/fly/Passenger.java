package com.solvd.solvdPractice.airport.fly;

import com.solvd.solvdPractice.airport.people.Person;

public class Passenger extends Person {
    private String reasonToTravel;
    private Luggage luggage;

    //region constructors
    public Passenger() {
    }

    public Passenger(String reasonToTravel) {
        this.reasonToTravel = reasonToTravel;
    }

    public Passenger(String name, Integer age, String nationality, String occupation, String reasonToTravel, Luggage luggage) {
        super(name, age, nationality, occupation);
        this.reasonToTravel = reasonToTravel;
        this.luggage = luggage;
    }
    //endregion

    //region getter and setter
    public String getReasonToTravel() {
        return reasonToTravel;
    }

    public void setReasonToTravel(String reasonToTravel) {
        this.reasonToTravel = reasonToTravel;
    }
    //endregion

    //region override
    @Override
    public int hashCode() {
        return super.hashCode() + 1;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals((Passenger)obj);
    }


    @Override
    public String toString() {
        return "Passenger{" +
                "reasonToTravel='" + reasonToTravel + '\'' +
                ", luggage=" + luggage +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
    //endregion
}
