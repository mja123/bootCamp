package com.solvd.solvdPractice.airport.fly;

import com.solvd.solvdPractice.airport.physical_place.Airport;

import java.util.ArrayList;

public class Travel {
    private Airport takeOff;
    private Airport arrive;
    private Plane plane;
    private ArrayList<Passenger> passengers;

    //region constructors
    public Travel() {
    }

    public Travel(Airport takeOff, Airport arrive, Plane plane) {
        this.takeOff = takeOff;
        this.arrive = arrive;
        this.plane = plane;
    }
    //endregion

    //region passenger CRUD
    public void addPassanger(Passenger newPassenger) {
        passengers.add(newPassenger);
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    public void getAllOfPassengers() {
        for (Passenger passenger : passengers) {
            System.out.println(passenger.toString() + "\n");
        }
    }
    //endregion

    //region getters and setters
    public Airport getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(Airport takeOff) {
        this.takeOff = takeOff;
    }

    public Airport getArrive() {
        return arrive;
    }

    public void setArrive(Airport arrive) {
        this.arrive = arrive;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
    //endregion

    public void travelData() {
        System.out.println("The plane id is "+ plane.getPlaneId() + " and it take off from " + takeOff.getName() +
                " to " + arrive.getName());
    }
}
