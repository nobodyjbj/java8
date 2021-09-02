package com.study;

@FunctionalInterface // 함수형 인터페이스 선언
public interface RunSomething {

    int doIt(int number); // 하나만 있으면 '함수형 인터페이스'

    static void printName() {
        System.out.println("June");
    }

    default void printAge() {
        System.out.println("999");
    }

    // 다른 형태의 메서드가 선언되면 '함수형 인터페이스'이다.
    // 함수형 인터페이스의 정의가 잘 못되면 컴파일할때 에러가 날 것이다.ß
}
