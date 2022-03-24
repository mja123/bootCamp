
package airport.fly;

import airport.people.IWork;
import airport.people.Person;

public class Pilot extends Person implements IWork {
    private Integer yearsOfExperience;


    //region constructors

    public Pilot() {
    }

    public Pilot(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Pilot(String name, Integer age, String nationality, String occupation, Integer yearsOfExperience) {
        super(name, age, nationality, occupation);
        this.yearsOfExperience = yearsOfExperience;
    }
    //endregion

    @Override
    public void goToWork() {
        System.out.println("Going to work");
    }

    @Override
    public String toString() {
        super.toString();
        return "Pilot{" +
                "yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
