package com.study;

import java.util.function.*;

public class LambdaFunction {
    public static void main(String[] args) {
        // 람다 표현식
        // 변수 캡쳐 : 람다를 감싸고 있는 영역에 있는 지역 변수(local valuable)
        LambdaFunction foo3 = new LambdaFunction();
        foo3.run();
    }

    private void run() {
        // 변하지 않는 상수라면 effective final이라고 정의되고 final을 생략할 수 있다.
        // java8이상부터 로컬 변수에 변화가 없다면, 앞에 final 이 있다고 생각해도 된다. 변수의 변호가 생긴다면 컴파일 에러가 표시된다.
        int baseNumber = 10;

        // 로컬클래스, 익명클래스는 되지만 람다는 되지 않는 것이 있다. 쉐도잉이다.
        // 쉐도잉이란 함수 안의 스코프의 변수값이 함수 밖의 스코프에 있는 변수의 값을 덮는 것을 말한다. 물론, 두 변수는 이름이 같다.

        // 로컬 클래스, 쉐도잉이 가능한 이유는 별도의 scope 이기 때문이다.
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11; // 쉐도잉
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스, 쉐도잉이 가능한 이유는 별도의 scope 이기 때문이다.
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 11; // 쉐도잉
                System.out.println(baseNumber);
            }
        };

        // 람다, run() 과 같은 scope 이기때문에 쉐도잉이 일어날 수 없다.
        // IntConsumer printInt = (baseNumber) -> { // 에러, 이미 지정되어 있는 변수라는 컴파일 에러 출력
        //     System.out.println(i + baseNumber);
        // };
        IntConsumer printInt = (i) -> {
            System.out.println(i + baseNumber);
        };

        printInt.accept(10);
    }
}
