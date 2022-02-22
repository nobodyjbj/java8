package com.study.functioninterface;

public class App {
    public static void main(String[] args) {
        // 익명 내부 클래스 (anonymous inner class)
//        RunSomething runSomething = new RunSomething() {
//            @Override
//            public void doIt() {
//                System.out.println("hello");
//            }
//        };

        // 익명 내부 클래스는 람다식으로 줄일 수 있다.
        Foo foo = (number) -> {
            return number + 10;
        };

        System.out.println(foo.doIt(1));

        // 함수형 프로그래밍을 사용해야 한다면,
        // 순수함수(Pure function)를 달성하기 위한 주의가 필요하다.
        // * 사이드 이팩트를 만들 수 없다.(함수 밖에 있는 값을 변경하지 못한다.)
        // * 상태가 없다. (함수 밖에 정의되어 있는)
        // 정리: 함수의 파라미터 또는 함수 내부의 값들만 사용해야하고 함수의 외부 변수를 사용해서는 안된다.
    }
}
