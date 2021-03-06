package com.solvd.solvdPractice.airport.physical_place;

import com.solvd.solvdPractice.airport.exceptions.EmptyAirsTripException;
import com.solvd.solvdPractice.airport.fly.Plane;

import java.util.LinkedList;
import java.util.Queue;

public class AirStrip {
    private Boolean isEmpty;
    private Queue<Plane> planes = new LinkedList<>();
    private Airport airport;

    //region constructors
    public AirStrip() {
        isEmpty = true;
    }

    public AirStrip(Boolean isEmpty, Airport airport) {
        this.isEmpty = isEmpty;
        this.airport = airport;
    }
    //endregion

    public void addPlanesQueue(Plane plane) {
        planes.add(plane);
        isEmpty = false;
    }

    public Integer getFirstPlane() throws EmptyAirsTripException {
        if (planes.isEmpty()) {
            throw new EmptyAirsTripException("There isn't any plane in the air strip.");
        }
        return planes.peek().getPlaneId();
    }

}
