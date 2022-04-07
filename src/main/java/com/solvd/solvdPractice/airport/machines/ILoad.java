package com.solvd.solvdPractice.airport.machines;

import com.solvd.solvdPractice.airport.exceptions.LoadCapacityException;

public interface ILoad {
    void load(Integer weight) throws LoadCapacityException;
    void unload();
}
