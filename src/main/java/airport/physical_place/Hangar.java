package airport.physical_place;

import airport.exceptions.FullHangarException;
import airport.fly.Plane;
import java.util.ArrayList;

public class Hangar {
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

    public void repairPlane(Plane plane) {
        System.out.println("Repairing the plain " + plane.getPlaneId());
    }

    //region getters and setters
    public Integer getCapacity() {
        return this.CAPACITY;
    }

    //endregion
}
