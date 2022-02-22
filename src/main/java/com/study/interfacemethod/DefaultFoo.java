package com.study.interfacemethod;

public class DefaultFoo implements Foo, Bar {

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    // 충돌하는 interface method 는 직접 재정의를 해줘야 한다.
    @Override
    public void printNameUpperCase() {
        Foo.super.printNameUpperCase();
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
