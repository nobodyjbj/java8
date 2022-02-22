package com.study;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodRefefrence {
    public static void main(String[] args) {
        // method reference 는 구현체를 직접 구현하지 않고, 참조하여 사용하는 방법을 말한다.
        // :: 콜론이 2개이면 method reference.

        // static method 참조 방법
        UnaryOperator<String> hi = Greeting::hi;
        System.out.println(hi.apply("user"));

        // instance method 참조 방법
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("user"));

        // 기본 생성자 참조 방법
        Supplier<Greeting> newGreeting = Greeting::new;
        System.out.println(newGreeting.get());

        // 입력값이 있는 생성자 참조 방법
        Function<String, Greeting> userGreeting = Greeting::new;
        Greeting user = userGreeting.apply("user");
        System.out.println(user.getName());

        // 임의 객체의 instance method 참조 방법
        String[] names = {"kobe", "iverson", "jordan"};
        Arrays.sort(names, String::compareToIgnoreCase);
    }
}
