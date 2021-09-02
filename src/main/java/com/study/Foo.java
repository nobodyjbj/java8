package com.study;

public class Foo {
    public static void main(String[] args) {

        // 익명 내부 클래스 (anonymous inner class)
        /*
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("hello");
            }
        };
        */

        // 익명 내부 클래스는 람다식으로 줄일 수 있다.
        RunSomething runSomething = () -> System.out.println("hello");
        runSomething.doIt();
    }
}
