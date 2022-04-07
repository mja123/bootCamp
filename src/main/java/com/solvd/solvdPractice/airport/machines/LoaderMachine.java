package com.solvd.solvdPractice.airport.machines;

import com.solvd.solvdPractice.airport.exceptions.LoadCapacityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoaderMachine extends Machine implements  ILoad {
    private static final Logger LOGGER = LogManager.getLogger(LoaderMachine.class);
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

    public void load(Integer weight) throws LoadCapacityException {
        if (getLoadCapacity() >= (getLoad() + weight)) {
            throw new LoadCapacityException("Load capacity was exceeded.");
        }
        setLoad(getLoad() + weight);
    }

    public void unload() {
        LOGGER.info("Unloading luggage.");
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
