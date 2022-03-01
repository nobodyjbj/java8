package com.study.etc;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ParallelSortEx {
    public static void main(String[] args) {
        /* ## Arrays.parallelSort()
            * Fork/Join 프레임워크를 사용해서 배열을 병렬로 정렬하는 기능을 제공
           ## 병렬 정렬 알고리즘
            * 배열을 둘로 계속 쪼갠다.
            * 합치면서 정렬한다.
           ## sort() 와 parallelSort() 비교
            * parallelSort 가 sort 보다 빠르다.
         */

        int size = 1000;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers); // 싱글쓰레드
        System.out.println("serial sorting took :: " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers); // 멀티쓰레드
        System.out.println("serial sorting took :: " + (System.nanoTime() - start));
    }
}
