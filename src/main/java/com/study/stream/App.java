package com.study.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        /* 1. 데이터를 담는 저장소가 아니다.
           2. 근본적으로 Functional 하다. 소스를 변경하지 않는다.
           3. 스트림으로 들어오는 데이터가 무제한일 수 있다. (Short Circuit 메서드를 사용해서 제한할 수 있다.)
           4. 중개 오퍼레이터는 근본적으로 Lazy 하다.
           5. 손쉽게 병렬처리를 할 수 있다.
         */

        List<String> names = new ArrayList<>();
        names.add("iverson");
        names.add("kobe");
        names.add("jordan");
        names.add("harden");
        names.add("curry");

        // 2. 이런 행위만으로 names의 데이터의 변경이 되지 않는다. Functional 하다.
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        names.forEach(System.out::println); // 소문자 출력
        stringStream.forEach(System.out::println); // 대문자 출력

        /* 4. 중개 오퍼레이터
           Stream을 리턴한다.
           Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수 있다.
           (대부분 Stateless 지만 distinct 나 sorted 처럼 이전 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.)
           filter, map, limit, skip, sorted, ...
         */
        names.stream().map(s -> {
            System.out.println(s); // 중개오퍼레이션이기 때문에 출력되지 않는다.
            return s.toUpperCase();
        });

        System.out.println("===========");

        names.forEach(System.out::println);

        /* 종료 오퍼레이터
           Strem을 리턴하지 않는다.
           collet, allMatch, count, forEach, min, max, ...
         */
        List<String> collect = names.stream().map(s -> {
            System.out.println(s); // 중개오퍼레이션이기 때문에 출력되지 않는다.
            return s.toUpperCase();
        }).collect(Collectors.toList()); // 종료 오퍼레이터를 사용
        collect.forEach(System.out::println);

        System.out.println("===========+++++");

        names.forEach(System.out::println);

        // 5. 병렬처리를 쉽게 할 수 있다.
        // 반복문
        for (String name : names) {
            if (name.startsWith("i")) {
                System.out.println(name.toUpperCase());
            }
        }

        // stream 사용
        List<String> collect1 = names.parallelStream() // 병렬처리 : parallelStream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        collect1.forEach(System.out::println);

    }
}
