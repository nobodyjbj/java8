package com.study;

import java.util.function.*;

public class Foo3 {
    public static void main(String[] args) {
        // 람다 표현식

        // 인자의 값이 하나인 경우
        Supplier<Integer> get10 = () -> 10;

        UnaryOperator<Integer> plus10 = (i) -> i + 10;
        UnaryOperator<Integer> multiply2 = (i) -> i * 2;

        // 인자의 값이 두개인 경우
        BiFunction<Integer, Integer, Integer> sum1 = (a, b) -> a + b; // 인자의 유형이 다를때 사용
        BinaryOperator<Integer> sum2 = (a, b) -> a + b; // 인자의 유형이 위와 같이 같다면 하나로 사용 가능

        // 변수 캡쳐 : 람다를 감싸고 있는 영역에 있는 지역 변수(local valuable)
        Foo3 foo3 = new Foo3();
        foo3.run();

    }

    private void run() {
        final int baseNumber = 10; // local valuable

        // 다른점
        // 로컬클래스와 익명클래스는 쉐도잉이 되지만 람다는 되지 않는다.
        // 쉐도잉이란 함수 안의 스코프의 변수값이 함수 밖의 스코프에 있는 변수의 값을 덮는 것을 말한다. 물론, 두 변수는 이름이 같다.

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11; // 쉐도잉
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 11; // 쉐도잉
                System.out.println(baseNumber);
            }
        };

        // 람다, run() 과 같은 스코프이기때문에 쉐도잉이 일어날 수 없다.
        // IntConsumer printInt = (baseNumber) -> { // 에러, 이미 지정되어 있는 변수라는 컴파일 에러 출력
        //     System.out.println(i + baseNumber);
        // };

        IntConsumer printInt = (i) -> {
            System.out.println(i + baseNumber);
        };

        printInt.accept(10);
    }
}
