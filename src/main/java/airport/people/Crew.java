package airport.people;

import airport.people.Person;

public class Crew extends Person {
    private String job;

    //region constructors
    public Crew() {
    }

    public Crew(String job) {
        this.job = job;
    }

    public Crew(String name, Integer age, String nationality, String occupation, String job) {
        super(name, age, nationality, occupation);
        this.job = job;
    }
    //endregion


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        super.toString();
        return "Crew{" +
                "job='" + job + '\'' +
                '}';
    }
}
