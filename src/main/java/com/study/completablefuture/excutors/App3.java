package com.study.completablefuture.excutors;

import java.util.concurrent.*;

public class App3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* ## Future 로 하기 어려운 작업들
            * Future 를 외부에서 완료 시킬 수 없다. 취소하거나, get() 에 타임아웃을 설정할 수는 있다.
            * 블로킹 코드(get())를 사용하지 않고서 작업이 끝날때 콜백을 실행할 수 없다.
            * 여러 Future 를 조합할 수 없다.
            * 예외처리용 api 를 제공하지 않는다.
         */
        // get() 을 하기 전가지 어떤 것도 할 수 없다. 결과값을 get()이후에 와야하기 때문이다.
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> hello = executorService.submit(() -> "hello");
        hello.get();

        /* ## CompletableFuture
            * 외부에서 complete 을 명시적으로 시킬 수 있다.
            * Executors 를 사용할 필요 없다.
            * Implements Future
            * Implements CompletionStage
         */
        // 생성방법 1, 생성자 사용
        CompletableFuture<String> future1 = new CompletableFuture<>();
        future1.complete("iverson");
        future1.get();

        // 생성방법 2 , 스태틱 메서드 사용
        CompletableFuture<String> future2 = CompletableFuture.completedFuture("iverson");
        future2.get();

        // 작업 실행 1, 리턴이 없는 경우
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("리턴이 없는 경우 :: " + Thread.currentThread().getName());
        });
        voidCompletableFuture.get();

        // 작업 실행 2, 리턴이 있는 경우
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            return "리턴이 있는 경우 :: " + Thread.currentThread().getName();
        });
        System.out.println(completableFuture1.get());

        // thenApply() : 리턴값을 다른형태로 변경한 후 리턴
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello :: " + Thread.currentThread().getName());
            return "hello";
        }).thenApply((s) -> { // get() 호출하기 전에 콜백 함수를 사용할 수 있다.
            System.out.println("Hello :: " + Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(completableFuture2.get());

        // thenApply() : 리턴값을 받아서 바로 실행
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello :: " + Thread.currentThread().getName());
            return "hello";
        }).thenAccept((s) -> { // get() 호출하기 전에 콜백 함수를 사용할 수 있다.
            System.out.println("Hello :: " + Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });
        System.out.println(voidCompletableFuture1.get());

        // thenRun() : 리턴값을 받지않고 그냥 실행
        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello :: " + Thread.currentThread().getName());
            return "hello";
        }).thenRun(() -> { // Runnable
            System.out.println("Hello :: " + Thread.currentThread().getName());
        });
        System.out.println(voidCompletableFuture2.get());

        /* ## ForkJoinPool
            * Dequeue (맨 마지막에 들어온게 먼저 나간다.) 를 사용
            * Fork 는 Task 를 분할하여 할당된 각각의 쓰레드에서 처리시킨다는 의미
            * Join 은 할당된 각각의 쓰레드에서 처리된 결과를 기다렸다가 합친다는 의미
         */
    }
}
