package airport.machines;

import airport.exceptions.LoadCapacityException;

public class LoaderMachine extends Machine {
    private Integer load;
    private final Integer loadCapacity;

    //region constructors
    public LoaderMachine() {
        this.loadCapacity = 100;
        this.load = 0;
    }

    public LoaderMachine(Integer loadCapacity)
    {
        this.load = 0;
        this.loadCapacity = loadCapacity;
    }

    public LoaderMachine(String type, Integer id, Integer yearsOfUse, Integer loadCapacity) {
        super(type, id, yearsOfUse);
        this.load = 0;
        this.loadCapacity = loadCapacity;
    }
    //endregion

    public void loadLuggage(Integer luggageWeight) throws LoadCapacityException {
        if (getLoadCapacity() >= (getLoad() + luggageWeight)) {
            throw new LoadCapacityException();
        }
        setLoad(getLoad() + luggageWeight);
    }

    public Integer getLoadCapacity() {
        return loadCapacity;
    }

    public Integer getLoad() {
        return load;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }

    //region override
    @Override
    public int hashCode() {
        return super.hashCode() + 1;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals((LoaderMachine)obj);
    }

    @Override
    public String toString() {
        return "LoaderMachine{" +
                "loadCapacity=" + loadCapacity +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", yearsOfUse=" + yearsOfUse +
                '}';
    }
    //endregion
}
