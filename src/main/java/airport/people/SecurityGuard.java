package airport.people;

import airport.people.Person;

public class SecurityGuard extends Person {
    private Boolean isInAPlane;

    //region constructors
    public SecurityGuard() {
    }

    public SecurityGuard(Boolean isInAPlane) {
        this.isInAPlane = isInAPlane;
    }

    public SecurityGuard(String name, Integer age, String nationality, String occupation, Boolean isInAPlane) {
        super(name, age, nationality, occupation);
        this.isInAPlane = isInAPlane;
    }
    //endregion

    public Boolean getInAPlane() {
        return isInAPlane;
    }

    public void setInAPlane(Boolean inAPlane) {
        isInAPlane = inAPlane;
    }
}
