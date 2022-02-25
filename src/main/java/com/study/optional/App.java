package com.study.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        /*
           리턴값으로만 쓰기를 권장한다. ( 메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 쓰지 말자.)
           Optional 을 리턴하는 메소드에서는 null 을 리턴하지 말자.
           프리미티브 타입용 Optional 이 따로 있다.
           Collection, Map, Stream, Array, Optional 등을 Optional 로 감싸지 말자
         */

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api", false));

        // ## Optional 만들기
        Optional<OnlineClass> existOptional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        Optional<OnlineClass> notExistOptional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("hello"))
                .findFirst();

        // ## Optional 에 값이 있는지 없는지 확인하기
        boolean present = existOptional.isPresent();
        System.out.println(present);

        boolean empty = existOptional.isEmpty();
        System.out.println(empty);

        // ## Optional 에 있는 값 가져오기
        OnlineClass onlineClass1 = existOptional.get(); // 가급적 사용하지 않는다.
        System.out.println(onlineClass1.getTitle());

        // OnlineClass onlineClass2 = notExistOptional.get(); // 결과가 없으면 Runtime Exception 계열의 NoSuch 에러가 발생, 가급적 사용하지 않는다.
        // System.out.println(onlineClass2.getTitle());

        // ## Optional 에 값이 있는 경우에 그 값을 가지고 ~~ 을 하라
        existOptional.ifPresent(oc -> System.out.println(oc.getTitle()));

        // ## Optional 에 값이 있으면 가져오고 없는 경우에 ~~ 을 리턴하라
        // 상수로 이미 만들어져 있는 것에 적합
        OnlineClass onlineClass3 = notExistOptional.orElse(createNewClasses());
        System.out.println(onlineClass3.getTitle());

        // ## Optional 에 값이 있으면 없는 경우에 ~~ 을 하라
        // 동적으로 값이 만들어져야 할 때 적합
        OnlineClass onlineClass4 = notExistOptional.orElseGet(App::createNewClasses);
        System.out.println(onlineClass4.getTitle());

        // ## Optional 에 값이 있으면 가져오고 없는 경우에 에러를 던져라
        OnlineClass onlineClass5 = existOptional.orElseThrow(IllegalStateException::new);
        System.out.println(onlineClass5.getTitle());

        // ## Optional 에 들어있는 값 걸러내기
        Optional<OnlineClass> onlineClass6 = existOptional
                .filter(Predicate.not(OnlineClass::isClosed));
        System.out.println(onlineClass6.isEmpty());

        // ## Optional 에 들어있는 값 변환하기
        Optional<Integer> integer = existOptional
                .map(OnlineClass::getId);
        System.out.println(integer.isPresent());

        // ## Optional 안에 Optional 이 있을때
        Optional<Optional<Progress>> progress = existOptional.map(OnlineClass::getProgress);
        Optional<Progress> progress1 = progress.orElse(Optional.empty()); // 이렇게 계속해서 꺼내야할때 유용하게 사용할 수 있는 메서드가 flatMap() 이다.

        Optional<Progress> progress2 = existOptional.flatMap(OnlineClass::getProgress);
    }

    private static OnlineClass createNewClasses() {
        return new OnlineClass(10, "New class", false);
    }
}
