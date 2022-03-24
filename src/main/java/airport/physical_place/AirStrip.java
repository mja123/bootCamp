package airport.physical_place;

import airport.fly.Plane;

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

    public Integer getFirstPlane() {
        return planes.peek().getPlaneId();
    }


}
