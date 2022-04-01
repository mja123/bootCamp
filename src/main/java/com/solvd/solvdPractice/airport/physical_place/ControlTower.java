package com.solvd.solvdPractice.airport.physical_place;

import com.solvd.solvdPractice.airport.exceptions.FollowingPlanesException;

public class ControlTower {
    private Integer high;
    private Integer countOfPlanesFollowing;
    //region constructors

    public ControlTower() {
        this.countOfPlanesFollowing = 0;
    }

    public ControlTower(Integer high, Integer countOfPlanesFollowing) {
        this.high = high;
        this.countOfPlanesFollowing = countOfPlanesFollowing;
    }
    //endregion

    public void startFollowingPlane() throws FollowingPlanesException {
        int maxCountOfPlanesFollowing = 3;

        if (getCountOfPlanesFollowing() + 1 >= maxCountOfPlanesFollowing) {
            throw new FollowingPlanesException("Control tower can't follow more planes.");
        }
        this.setCountOfPlanesFollowing(getCountOfPlanesFollowing() + 1);
    }

    //region getters and setters
    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getCountOfPlanesFollowing() {
        return countOfPlanesFollowing;
    }

    public void setCountOfPlanesFollowing(Integer countOfPlanesFollowing) {
        this.countOfPlanesFollowing = countOfPlanesFollowing;
    }
    //endregion
}
