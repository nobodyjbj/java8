package com.study.functioninterface;

import java.util.function.*;

public class App2 {
    public static void main(String[] args) {
        // 주요한 함수형 인터페이스 : https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

        Function<Integer, Integer> plus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        System.out.println(plus10.compose(multiply2).apply(2)); // 곱하기 2를 먼저하고 플러스 10 -> 14
        System.out.println(plus10.andThen(multiply2).apply(2)); // 플러스 10을 먼저하고 곱하기 2 -> 24

        // T 타입을 받아서 아무 값도 리턴하지 않는 함수 인터페이스
        Consumer<Integer> printT = System.out::println;
        printT.accept(10);

        // 인자가 없이 무조건 정의한 값을 출력하는 함수 인터페이스
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        // T 타입에 대한 TRUE, FALSE값을 리턴해주는 함수 인터페이스
        Predicate<String> startsWithZero = (s) -> s.startsWith("Zero");
        System.out.println(startsWithZero.test("Zero cola"));

        // 입력되는 T 타입과 출력해야할 값의 타입이 같을때 사용하는 함수 인터페이스
        UnaryOperator<Integer> plus20 = (i) -> i + 10;
        System.out.println(plus20.apply(2));

        // 인자의 값이 하나인 경우
        Supplier<Integer> get20 = () -> 20;

        UnaryOperator<Integer> plus30 = (i) -> i + 30;
        UnaryOperator<Integer> multiply3 = (i) -> i * 3;

        // 인자의 값이 두개인 경우
        BiFunction<Integer, Integer, Integer> sum1 = (a, b) -> a + b; // 인자의 유형이 다를때 사용
        BinaryOperator<Integer> sum2 = (a, b) -> a + b; // 인자의 유형이 위와 같이 같다면 하나로 사용 가능
    }
}
