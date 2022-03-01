package com.study.completablefuture.excutors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class App4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* ## CompletableFuture 의 조합하기, 예외처리 */

        // 조합 방법 1
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello :: " + Thread.currentThread().getName());
            return "Hello";
        });

        // thenCompose() : 작업이후에 추가로 작업을 이어할 수 있게 한다.
        CompletableFuture<String> completableFuture = hello.thenCompose(s -> getWorld(s));
        System.out.println(completableFuture.get());

        // 조합 방법 2
        CompletableFuture<String> apple = CompletableFuture.supplyAsync(() -> {
            System.out.println("Apple Stock :: " + Thread.currentThread().getName());
            return "Apple Stock :: 165.12$";
        });

        CompletableFuture<String> msSoft = CompletableFuture.supplyAsync(() -> {
            System.out.println("Microsoft Stock :: " + Thread.currentThread().getName());
            return "Microsoft Stock :: 298.79$";
        });

        // thenCombine 따로따로 처리해서 결과가 왔을때 처리하고자 할 때 사용한다.
        CompletableFuture<String> value = apple.thenCombine(msSoft, (a, m) -> a + "\n" + m);
        System.out.println(value.get());

        // 조합 방법 3
        CompletableFuture<String> usedMoneyOfSaturday = CompletableFuture.supplyAsync(() -> {
            System.out.println("usedMoneyOfSaturday :: " + Thread.currentThread().getName());
            return "Saturday :: 300$";
        });

        CompletableFuture<String> usedMoneyOfSunday = CompletableFuture.supplyAsync(() -> {
            System.out.println("usedMoneyOfSunday :: " + Thread.currentThread().getName());
            return "Sunday :: 700$";
        });

        // allOf() : 테스크가 두개 이상일때 모든 테스크가 모두 끝났을때 추가적인 콜백을 실행할 수 있다.
        // 모든 테스크가 동일한 타입을 보장하지 않고, 어떤 테스크는 에러가 났을 수가 있다.
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(usedMoneyOfSaturday, usedMoneyOfSunday);
        System.out.println(voidCompletableFuture.get()); // null 이 나온다.

        // Array 로 담은 후 스트림으로 변환해야 결과값을 볼 수 있다.
        List<CompletableFuture<String>> futures = Arrays.asList(usedMoneyOfSaturday, usedMoneyOfSunday);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
        results.get().stream().forEach(System.out::println);

        // 조합 방법 4
        // anyOf() 는 테스크 중 가장 빨리 처리한 결과물을 가져온다.
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.anyOf(usedMoneyOfSaturday, usedMoneyOfSunday)
                .thenAccept(s -> System.out.println(s));
        System.out.println(voidCompletableFuture1.get());

        // 예외처리 1
        boolean throwError = true;
        CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Exception Example :: " + Thread.currentThread().getName());
            return "Exception Example";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!!";
        });

        // 예외처리 2
        CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Exception Example :: " + Thread.currentThread().getName());
            return "Exception Example";
        }).handle((result, ex) -> {
           if (ex != null) {
               System.out.println(ex);
               return "ERROR!!";
           }

           return result;
        });
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello :: " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
