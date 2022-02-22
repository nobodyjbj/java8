package com.study.interfacemethod;

public interface Bar {
    default void printNameUpperCase() {
        System.out.println("Bar");
    }
}
