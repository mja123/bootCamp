package com.solvd.solvdPractice.airport.fly;

import com.solvd.solvdPractice.airport.exceptions.FollowingPlanesException;
import com.solvd.solvdPractice.airport.physical_place.ControlTower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Plane implements IFly {
    private static final Logger LOGGER = LogManager.getLogger(Plane.class);
    private Pilot pilot;
    private String motor;
    private Integer planeId;

    //region constructors
    public Plane() {
    }
    public Plane(Pilot pilot, Integer planeId) {
        this.pilot = pilot;
        this.planeId = planeId;
    }

    public Plane(Pilot pilot, String motor, Integer planeId) {
        this.pilot = pilot;
        this.motor = motor;
        this.planeId = planeId;
    }
    //endregion


    //region fly
    public void takeOff() throws FollowingPlanesException {
        ControlTower controlTower = new ControlTower();

        controlTower.startFollowingPlane();
        LOGGER.info("The plane is taking off.");
    }

    public void arrive() {
        LOGGER.info("The plane is arriving.");
    }
    //endregion


    //region getters and setters
    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public Integer getPlaneId() {
        return planeId;
    }

    public void setPlaneId(Integer planeId) {
        this.planeId = planeId;
    }
    //endregion


    //region override
    @Override
    public int hashCode() {
        return super.hashCode() + 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Plane) {
            Plane plane = (Plane) obj;
            return Objects.equals(this.planeId, plane.planeId);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Plane data: Plane{" +
                "pilot=" + pilot +
                ", motor='" + motor + '\'' +
                ", planeId=" + planeId +
                '}';
    }
    //endregion
}
