package com.study.optional;

import com.study.stream.OnlineClass;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        /* 리턴값으로만 쓰기를 권장한다. ( 메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 쓰지 말자.)
           Optional 을 리턴하는 메소드에서는 null 을 리턴하지 말자.
           프리미티브 타입용 Optional 이 따로 있다.
           Collection, Map, Stream, Array, Optional 등을 Optional 로 감싸지 말자
         */

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new com.study.stream.OnlineClass(1, "spring boot", true));
        springClasses.add(new com.study.stream.OnlineClass(2, "spring data jpa", true));
        springClasses.add(new com.study.stream.OnlineClass(3, "spring mvc", false));
        springClasses.add(new com.study.stream.OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api", false));

    }
}
