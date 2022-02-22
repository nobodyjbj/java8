package com.study.functioninterface;

@java.lang.FunctionalInterface
public interface Foo {

    // 추상메서드가 하나만 있으면 함수형 인터페이스이다. 하나 이상이 되면 함수형 인터페이스가 아니다.
    abstract int doIt(int number);

    // java8이상부터 interface에 static 메서드를 정의할 수 있다.
    static void printName() {
        System.out.println("Example");
    }

    // interface에 default method를 정의 할 수 있다.
    default void printAge() {
        System.out.println("999");
    }
}
