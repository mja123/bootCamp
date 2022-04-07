package com.solvd.solvdPractice.airport.physical_place;

public interface IRapair <T> {
    void reparing(T toRepair);
    boolean canBeRepair(T object);
}
