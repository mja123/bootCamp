
package airport.physical_place;

        import airport.fly.Plane;

        import java.util.ArrayList;

public class Hangar {
    private ArrayList<Plane> planes = new ArrayList<>();
    private Integer capacity;

    //region constructors
    public Hangar() {
    }

    public Hangar(Integer capacity) {
        this.capacity = capacity;
    }
    //endregion

    public void addPlane(Plane newPlane) {
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
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    //endregion
}
