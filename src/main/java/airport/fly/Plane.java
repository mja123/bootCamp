package airport.fly;

public class Plane {
    private Pilot pilot;
    private String motor;
    private Integer planeId;

    //region constructors
    public Plane() {
    }

    public Plane(Pilot pilot, String motor, Integer planeId) {
        this.pilot = pilot;
        this.motor = motor;
        this.planeId = planeId;
    }
    //endregion


    //region fly
    public void takeOff() {
        System.out.println("The plane is taking off.");
    }

    public void arrive() {
        System.out.println("The plane is arriving.");
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
        return super.equals((Plane)obj);
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
