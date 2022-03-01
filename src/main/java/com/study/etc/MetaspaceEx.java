package com.study.etc;

public class MetaspaceEx {
    public static void main(String[] args) {
        /* ## JVM의 여러 메모리 영역 중에서 PermGen 메모리 영역이 없어지고 Metaspace 영역으로 대체되었다
          ### PermGen
            * permanent generation, 클래스 메타데이터를 담는 곳
            * Heap 영역
            * 기본값으로 제한된 크기를 가지고 있음
            * -XX:PermSize=N, PermGen 초기 사이즈 설정
            * -XX:MaxPermSize=N, PermGen 초기 사이즈 설정
          ### Metaspace
            * 클래스 메타데이터를 담는 곳
            * Heap 영역이 아니라, Native 영역
            * 기본값으로 제한된 크기를 가지고 있지 않다.(필요한 만큼 늘어난다.)
            * -XX:MetaspaceSize=N, Metaspace 초기 사이즈 설정
            * -XX:MaxMetaspaceSize=N, Metaspace 초기 사이즈 설정
         */

        // 가비지 컬렉터  : full Gc, young and old generation
    }
}
