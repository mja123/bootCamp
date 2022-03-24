package airport.physical_place;

import airport.fly.Pilot;
import airport.fly.Plane;
import airport.fly.Travel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Airport {
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

    public void startTravel() {
        boolean travelCanStart = false;
        if (planes.size() > 0) {
            for (Plane plane : planes) {
                if (plane.getPlaneId().equals(airStrip.getFirstPlane()) && plane.getPilot() != null) {
                    travelCanStart = true;
                    break;
                }
            }
            if (travelCanStart) {
                planes.get(airStrip.getFirstPlane()).takeOff();
            }
        } else {
            System.out.println("The airport can't start the travel.");
        }
    }

    //region plane CRUD
    public void addPlane(Plane newPlane) {
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
