package com.study.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class App {
    public static void main(String[] args) {
        // 기계용 api
        Instant instant = Instant.now();
        System.out.println("기준시 UTC, GMT, 영국시간 :: " + instant);

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println("systemDefault() 로컬 시간 :: " + zonedDateTime);

        ZonedDateTime instantInKorea = instant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println("instant 특정 지역의 날짜 시간 :: " + instantInKorea);

        // 사람용 api
        LocalDateTime now = LocalDateTime.now();
        System.out.println("현재 날짜 시간 :: " + now);

        LocalDateTime of = LocalDateTime.of(2022, 02, 25, 0, 0, 0);
        System.out.println("직접 만든 날짜 시간 :: " + of);

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println("zoneDateTime 특정 지역의 날짜 시간 :: " + nowInKorea);

        // 기간
        // 기계용 api
        Instant now1 = Instant.now();
        Instant plus = now1.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now1, plus);
        System.out.println(between.getSeconds());

        // 사람용 api
        LocalDate today = LocalDate.now();
        LocalDate thisYearLastDay = LocalDate.of(2022, Month.DECEMBER, 31);

        Period period = Period.between(today, thisYearLastDay);
        System.out.println(period.getMonths() + "달 " + period.getDays() + "일");

        Period until = today.until(thisYearLastDay);
        System.out.println(until.get(ChronoUnit.DAYS));

        // 포맷팅
        LocalDateTime now2 = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(MMddyyyy.format(now));

        // parsing
        LocalDate parse = LocalDate.parse("01/01/1900", MMddyyyy);
        System.out.println(parse);
    }
}
