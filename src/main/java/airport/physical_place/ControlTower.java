package airport.physical_place;

public class ControlTower {
    private Integer high;
    private Integer countOfPlanesFollowing;

    //region constructors
    public ControlTower() {
    }

    public ControlTower(Integer high, Integer countOfPlanesFollowing) {
        this.high = high;
        this.countOfPlanesFollowing = countOfPlanesFollowing;
    }
    //endregion

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
