package datatype;

import org.junit.Test;

import java.time.*;

public class DateTimeBasicMethodTest {

    @Test
    public void testNowTime() throws Exception {
        System.out.println("Now Time: " + LocalTime.now());
        System.out.println("UTC Time: " + LocalTime.now(ZoneOffset.UTC));
        System.out.println("UTC+9 Time: " + LocalTime.now(ZoneId.of("UTC+9")));
        System.out.println("Seoul Time: " + LocalTime.now(ZoneId.of("Asia/Seoul")));

        LocalTime nowTime = LocalTime.now(); // 13:48:55.385
        System.out.println("Now Hour: " + nowTime.getHour());
        System.out.println("Now Minute: " + nowTime.getMinute());
        System.out.println("Now Second: " + nowTime.getSecond());
        System.out.println("Now NanoSecond: " + nowTime.getNano());
    }

    @Test
    public void testNowDate() throws Exception {
        System.out.println("Now Date: " + LocalDate.now());
        System.out.println("UTC Date: " + LocalDate.now(ZoneOffset.UTC));
        System.out.println("UTC+9 Date: " + LocalDate.now(ZoneId.of("UTC+9")));
        System.out.println("Seoul Date: " + LocalDate.now(ZoneId.of("Asia/Seoul")));

        LocalDate nowDate = LocalDate.now(); // 2017-01-02
        System.out.println(nowDate.getYear()); // 2017
        System.out.println(nowDate.getMonth()); // JANUARY
        System.out.println(nowDate.getDayOfWeek()); // MONDAY
        System.out.println(nowDate.getDayOfMonth()); // 2
        System.out.println(nowDate.getDayOfYear()); // 2
    }

    @Test
    public void testNowDateTime() throws Exception {
        System.out.println("Now DateTime: " + LocalDateTime.now());
        System.out.println("UTC DateTime: " + LocalDateTime.now(ZoneOffset.UTC));
        System.out.println("UTC+9 DateTime: " + LocalDateTime.now(ZoneId.of("UTC+9")));
        System.out.println("Seoul DateTime: " + LocalDateTime.now(ZoneId.of("Asia/Seoul")));

        LocalDateTime nowDateTime = LocalDateTime.now(); // 2017-01-02T13:47:46.506
        System.out.println("Now Hour: " + nowDateTime.getHour());
        System.out.println("Now Minute: " + nowDateTime.getMinute());
        System.out.println("Now Second: " + nowDateTime.getSecond());
        System.out.println("Now NanoSecond: " + nowDateTime.getNano());
        System.out.println(nowDateTime.getYear()); // 2017
        System.out.println(nowDateTime.getMonth()); // JANUARY
        System.out.println(nowDateTime.getDayOfWeek()); // MONDAY
        System.out.println(nowDateTime.getDayOfMonth()); // 2
        System.out.println(nowDateTime.getDayOfYear()); // 2
    }
}
