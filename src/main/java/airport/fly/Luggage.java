package airport.fly;

public class Luggage {
    private Float size;
    private Float weight;
    private String color;

    //region constructors
    public Luggage() {
    }

    public Luggage(Float size, Float weight, String color) {
        this.size = size;
        this.weight = weight;
        this.color = color;
    }
    //endregion

    //region getters and setters
    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    //endregion
}
