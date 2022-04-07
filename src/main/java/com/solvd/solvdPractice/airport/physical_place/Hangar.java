package com.solvd.solvdPractice.airport.physical_place;

import com.solvd.solvdPractice.airport.exceptions.FullHangarException;
import com.solvd.solvdPractice.airport.fly.Plane;
import com.solvd.solvdPractice.airport.people.SecurityGuard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Hangar implements IRapair <Plane> {
    private static final Logger LOGGER = LogManager.getLogger(Hangar.class);
    private ArrayList<Plane> planes = new ArrayList<>();
    private final Integer CAPACITY = 3;

    //region constructors
    public Hangar() {
    }

    //endregion

    public void addPlane(Plane newPlane) throws FullHangarException {
        if (planes.size() >= CAPACITY) {
            throw new FullHangarException("The hangar is full, we can't add other plane.");
        }
        planes.add(newPlane);
    }

    public void removePlane(Plane plane) {
        planes.remove(plane);
    }


    //region getters and setters
    public Integer getCapacity() {
        return this.CAPACITY;
    }

    @Override
    public void reparing(Plane toRepair) {
        LOGGER.info("Repairing the plain: " + toRepair.getPlaneId());
    }

    @Override
    public boolean canBeRepair(Plane object) {
        return (object.getMotor() != null);
    }

    //endregion
}
