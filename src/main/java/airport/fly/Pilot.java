
package airport.fly;

import airport.people.Person;

public class Pilot extends Person {
    private Integer yearsOfExperience;

    public Pilot() {
    }

    public Pilot(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Pilot(String name, Integer age, String nationality, String occupation, Integer yearsOfExperience) {
        super(name, age, nationality, occupation);
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        super.toString();
        return "Pilot{" +
                "yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
