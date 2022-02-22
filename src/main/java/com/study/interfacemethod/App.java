package com.study.interfacemethod;

public class App {
    public static void main(String[] args) {
        Foo foo = new DefaultFoo("User");
        foo.printName();
        foo.printNameUpperCase();

        Foo.printAnything();
    }
}
