package com.solvd.solvdPractice.personal_practice.hashCode_equals;

public class ProgramOverriding {
    public static void main(String[] args) {
        Person person1 = new Person(10, "Matias");
        Person person2 = new Person(10, "Matias");

        System.out.println(person1.equals(person2));
    }
}
