package datatype.datetime;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTest {

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
    public void findLastDayOfThisMonth() throws Exception {
        System.out.println("Last day in this month: " +
                LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));

        System.out.println("Last sunday in this month: " +
                LocalDate.now().with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY)));
    }
}
