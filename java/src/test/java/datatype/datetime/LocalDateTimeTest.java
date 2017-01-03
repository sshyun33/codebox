package datatype.datetime;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class LocalDateTimeTest {

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
