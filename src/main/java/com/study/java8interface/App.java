package com.study.java8interface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Iverson");
        names.add("Kobe");
        names.add("Jordan");
        names.add("Harden");
        names.add("Curry");

        // Iterable method
        names.forEach(System.out::println);
        Spliterator<String> spliterator1 = names.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();
        while (spliterator1.tryAdvance(System.out::println));
        System.out.println("==============");
        while (spliterator2.tryAdvance(System.out::println));

        // Collection method
        List<String> k = names.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("K"))
                .collect(Collectors.toList());
        System.out.println(k);

        names.removeIf(s -> s.startsWith("K"));
        names.forEach(System.out::println);

        // Comparator method
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());
        names.forEach(System.out::println);
    }
}
