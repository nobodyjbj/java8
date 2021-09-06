package com.study;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Foo4 {
    public static void main(String[] args) {
        // 메소드 레퍼런스
        // 람다표현식을 표현할때 사용할 수 있는 방법

        // 스테틱 메서드를 참조하는 방법
        UnaryOperator<String> hi = Greeting::hi; // 콜론이 두개 찍혀있으면 메서드 레퍼런스이다.

        // 클래스 안의 메서드를 사용할 경우, 특정 객체의 인스턴스를 사용하는 방법
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("java"));

        // 생성자도 참조 할 수 있다.
        // 입력값이 없는 생성자를 참조하는 방법
        Supplier<Greeting> newGreeting = Greeting::new;
        newGreeting.get();

        // 입력값이 있는 생성자를 참조하는 방법
        Function<String, Greeting> newGreeting2 = Greeting::new;
        System.out.println(newGreeting2.apply("java"));

        // 임의의 객체의 인스턴스에 참조하는 방법
        String[] names = {"java", "java1", "java2"};
        Arrays.sort(names, String::compareToIgnoreCase); // compareToIgnoreCase 라는 인스턴스 메서드 사용
    }
}
