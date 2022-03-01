package com.study.etc;

import java.util.Arrays;

@Chicken("후라이드")
@Chicken("양념")
public class AnnotationEx {
    public static void main(String[] args) {
        /* ## 애노테이션의 변화
         * Java8 부터 애노테이션을 타입 선언부에서도 사용할 수 있다.
         * Java8 부터 애노테이션을 중복해서 사용할 수 있다.
         */

        ChickenContainer chickenContainer = AnnotationEx.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c ->
                System.out.println(c.value())
        );
    }
}
