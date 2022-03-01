package com.study.etc;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
// @Target(ElementType.TYPE_PARAMETER) // 타입파라미터에 애노테이션 사용 허용
@Target(ElementType.TYPE_USE)
@Repeatable(ChickenContainer.class)
public @interface Chicken {
    String value();
}
