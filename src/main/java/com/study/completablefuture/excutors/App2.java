package com.study.completablefuture.excutors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* Runnable 은 void 이기 때문에 어떤 결과물을 가져올 수 없다. 그래서 결과물(리턴값)을 가져오기 위해서는 Java8의 Callable 을 사용한다.
           Callable 의 리턴을 Future 로 받을 수 있다.
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(5000L);
            return "Hello";
        };

        // 리턴하는 타입의 Future 를 만들 수 있다.
        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone()); // 끝났으면 true, 아직 안끝났으면 false
        System.out.println("Started!");

        helloFuture.get(); // blocking call, 결과값을 가져올때까지 기다린다.
        // true 를 주면 종료, false 는 기다린다. cancel() 다음에는 get()으로 결과값을 가져올 수 없다.
        helloFuture.cancel(true);

        System.out.println("End!");
        executorService.shutdown();

        // Callable 을 사용해서 여러개를 한 번에 처리할 수 있다.
        ExecutorService executorService1 = Executors.newFixedThreadPool(4);

        Callable<String> sleep5 = () -> {
            Thread.sleep(5000L);
            return "Sleep5";
        };

        Callable<String> sleep4 = () -> {
            Thread.sleep(4000L);
            return "Sleep4";
        };

        Callable<String> sleep3 = () -> {
            Thread.sleep(3000L);
            return "Sleep3";
        };

        // 여러개의 Callable 을 한 번에 처리해주는 invokeAll(), invokeAll()은 모든 쓰레드가 끝날때까지 기다린 후 결과값을 반환한다.
        List<Future<String>> futures = executorService1.invokeAll(Arrays.asList(sleep5, sleep4, sleep3));
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        // invokeAny() 는 제일 빨리 가져올 수 있는 결과값을 가져온다.
        String s = executorService1.invokeAny(Arrays.asList(sleep5, sleep4, sleep3));
        System.out.println(s);

        executorService1.shutdown();
    }
}
