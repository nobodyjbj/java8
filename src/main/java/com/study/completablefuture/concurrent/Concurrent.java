package com.study.completablefuture.concurrent;

public class Concurrent {
    /* ## Concurrent 소프트 웨어
        * 동시에 여러 작업을 할 수 있는 소프트웨어
        * 예) 웹 브라우저로 유튜브를 보면서 키보드로 문서에 타이핑을 할 수 있다.
        * 예) 녹화를 하면서 IntelliJ 로 코딩을 하고 워드에 적어둔 문서를 보거나 수정할 수 있다.
       ## 자마에서 지원하는 컨커런트 프로그래밍
        * 멀티프로세싱 (ProcessBuilder)
        * 멀티쓰레드
       ## 자바 멀티쓰레드 프로그래밍
        * Thread / Runnable
     */
    public static void main(String[] args) throws InterruptedException {
        // 쓰레드의 순서는 보장되지 않는다.

        // 다른 쓰레드 구형 방법 1
        Thread thread1 = new MyThread();
        thread1.start();

        // 다른 쓰레드 구형 방법 2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2 :: " + Thread.currentThread().getName());
            }
        });
        thread2.start();

        // Java8에서는 Runnable 이 함수형인터페이스로 변경되었기 때문에 람다로 사용할 수 있다.
        Thread thread3 = new Thread(() ->{
            while (true) {
                System.out.println("Thread3 :: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch(InterruptedException e) {
                    System.out.println("exit!");
                    return; // 종료, 종료안하면 계속 돈다.
                }
            }
        });
        thread3.start();
        System.out.println("Thread3 :: " + Thread.currentThread().getName());
        thread3.sleep(3000L); // 잠재우는 명령어
        thread3.interrupt(); // 깨우는 명령어

        Thread thread4 = new Thread(() ->{
            System.out.println("Thread4 :: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch(InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread4.start();
        System.out.println("Thread4 :: " + Thread.currentThread().getName());
        thread4.join(); // 기다리게 하는 명령어
        System.out.println(thread4 + "is finished.");

        // 메인쓰레드
        System.out.println("Main Thread :: " + Thread.currentThread().getName());
    }

    // 다른 쓰레드 구형 방법 1
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread1 :: " + Thread.currentThread().getName());
        }
    }
}
