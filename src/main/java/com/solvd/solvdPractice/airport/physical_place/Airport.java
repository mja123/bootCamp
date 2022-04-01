package com.solvd.solvdPractice.airport.physical_place;

import com.solvd.solvdPractice.airport.exceptions.EmptyAirsTripException;
import com.solvd.solvdPractice.airport.exceptions.FollowingPlanesException;
import com.solvd.solvdPractice.airport.exceptions.NotPilotException;
import com.solvd.solvdPractice.airport.exceptions.NotPlaneException;
import com.solvd.solvdPractice.airport.fly.Pilot;
import com.solvd.solvdPractice.airport.fly.Plane;
import com.solvd.solvdPractice.airport.fly.Travel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Airport {
    private static final Logger LOGGER = LogManager.getLogger(Airport.class);
    private ArrayList<Plane> planes = new ArrayList<>();
    private ArrayList<Pilot> pilots = new ArrayList<>();
    private Queue<Travel> travel = new LinkedList<>();
    private AirStrip airStrip = new AirStrip();
    private String name;

    //region constructors
    public Airport() {
    }
    public Airport(String name) {
        this.name = name;
    }
    public Airport(String name, AirStrip airStrip) {
        this.name = name;
        this.airStrip = airStrip;
    }
    //endregion

    public void startTravel() throws FollowingPlanesException, NotPlaneException, NotPilotException, EmptyAirsTripException {
        boolean travelCanStart = false;
        if (planes.size() > 0) {
            for (Plane plane : planes) {
                if (plane.getPlaneId().equals(airStrip.getFirstPlane()) && plane.getPilot() != null) {
                    travelCanStart = true;
                    plane.takeOff();
                    break;
                }
            }
            if (!travelCanStart) {
                throw new NotPilotException("There isn't pilot to start the travel.");
            }
        } else {
           throw new NotPlaneException("There isn't plane to start the travel.");
        }
    }

    //region plane CRUD
    public void addPlane (Plane newPlane) {
        planes.add(newPlane);
        airStrip.addPlanesQueue(newPlane);
    }

    public void getPlanes() {
        for (Plane plane : planes) {
            System.out.println(plane.toString() + "\n");
        }
    }

    public void removePlane(Plane plane) {
        planes.remove(plane);
    }
    //endregion


    //region pilot CRUD
    public void addPilot(Pilot newPilot) {
        pilots.add(newPilot);
    }

    public void getPilot() {
        for (Pilot pilot : pilots) {
            System.out.println(pilot.toString() + "\n");
        }
    }

    public void removePilot(Pilot pilot) {
        pilots.remove(pilot);
    }
    //endregion


    //region getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
