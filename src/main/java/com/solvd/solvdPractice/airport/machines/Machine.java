package com.solvd.solvdPractice.airport.machines;

public abstract class Machine {
    protected String type;
    protected Integer id;
    protected Integer yearsOfUse;

    //region constructors
    public Machine() {
    }

    public Machine(String type, Integer id, Integer yearsOfUse) {
        this.type = type;
        this.id = id;
        this.yearsOfUse = yearsOfUse;
    }
    //endregion

    //region setters and getters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYearsOfUse() {
        return yearsOfUse;
    }

    public void setYearsOfUse(Integer yearsOfUse) {
        this.yearsOfUse = yearsOfUse;
    }
    //endregion
}
