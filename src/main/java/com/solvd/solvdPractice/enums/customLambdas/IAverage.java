package com.solvd.solvdPractice.enums.customLambdas;

import java.util.ArrayList;

@FunctionalInterface
public interface IAverage <T>{

    Double average(ArrayList<T> marks);
}
