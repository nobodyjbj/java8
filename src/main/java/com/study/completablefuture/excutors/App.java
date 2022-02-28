package com.study.completablefuture.excutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        /* ## 고수준 Concurrency 프로그래밍
            * 쓰레드를 만들고 관리하는 작업을 애플리케이션에서 분리
            * 그런 기능을 Executors 에게 위임
           ## Executors 가 하는 일
            * 쓰레드 만들기 : 애플리케이션이 사용할 쓰레드 풀을 만들어 관리한다.
            * 쓰레드 관리 : 쓰레드 생명 주기를 관리한다.
            * 작업 처리 및 실행 : 쓰레드로 실행할 작업을 제공할 수 있는 API를 제공한다.
           ## 주요 인터페이스
            * Executor : execute(Runnable)
            * ExecutorsService : Executor 상속 받은 인터페이스로, Callable 도 생성할 수
              있으며, Executor 를 종료시키거나, 여러 Callable 을 동시에 실행하는 등의 기능을 제공한다.
            * ScheduledExecutorService : ExecutorService 를 상속 받은 인터페이스로 특정 시간 이후에 또는 주기적으로 작업을 실행할 수 있다.
            * Runnable : void 이며, 결과값을 가져오지 않고 실행만 한다.
         */

        // Executors 를 사용한 쓰레드 생성 방법
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1 :: " + Thread.currentThread().getName());
            }
        });
        // grace full shutdown : 현재 작업을 끝까지 마치고 shutdown 한다는 뜻
        executorService1.shutdown();

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        executorService2.submit(() -> {
            System.out.println("Thread2 :: " + Thread.currentThread().getName());
        });
        executorService2.shutdown();

        // ThreadPool 사용하면 성능을 높일 수 있다. 블록킹 큐와 쓰레드풀 정리.
        ExecutorService executorService3 = Executors.newFixedThreadPool(2);
        executorService3.submit(getRunnable("kobe"));
        executorService3.submit(getRunnable("iverson"));
        executorService3.submit(getRunnable("curry"));
        executorService3.submit(getRunnable("harden"));
        executorService3.submit(getRunnable("jordan"));
        executorService3.shutdown();

        // 스케줄을 사용할 수 있다.
        ScheduledExecutorService executorService4 = Executors.newSingleThreadScheduledExecutor();
        executorService4.schedule(getRunnable("lebron"), 5, TimeUnit.SECONDS);
        // 반복실행, shutdown() 이 없어야 한다.
        executorService4.scheduleAtFixedRate(getRunnable("lebron"), 3,1, TimeUnit.SECONDS);
        executorService4.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
