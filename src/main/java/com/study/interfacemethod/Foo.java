package com.study.interfacemethod;

public interface Foo {
    // 인터페이스에서는 기본, default, setter, getter, static 메서드 등을 정의할 수 있다.

    void printName();

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 바꿔 출력한다.
     * 위의 주석은 Java8에서 추가된 인터페이스 메서드를 설명할 때 사용한다.
     */
    default void printNameUpperCase() {
        System.out.println(this.getName().toUpperCase());
    }

    // Object Class 의 method들은 재정의 할 수 없다. equals(), hashcode(), toString() 등등...

    String getName();

    // static method
    // 해당 인터페이스를 구현한 모든 인스턴스, 해당 타입에 대한 유틸리티나 헬퍼 메서드에 관한 것은 static method 를 활용할 수 있다.
    static void printAnything() {
        System.out.println("Foo");
    }

}
