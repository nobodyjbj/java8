package com.study.etc;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Repeatable 을 사용할 때 감싸고 있을 애노테이션보다 Retention 과 Target 의 범위가 커야한다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface ChickenContainer {
    // 배열로 하위 애노테이션을 가지고 있어야 한다.
    Chicken[] value();
}
