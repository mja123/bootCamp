package com.solvd.solvdPractice.collections;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CustomLinkedList implements Iterable{
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return Iterable.super.spliterator();
    }
    //generic linkedList to can have products in a carry
}
