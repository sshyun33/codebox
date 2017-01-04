package datatype.datetime;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

    // 포맷 문자 형식은 DateTimeFormatter Javadoc 확인
    @Test
    public void createDate() throws Exception {
        LocalDate date = LocalDate.of(2017, 1, 4);

        LocalDate parsedDate = LocalDate.parse("2017-01-04"); // default: ISO_LOCAL_DATE(yyyy-MM-dd)

        LocalDate customParsedDate01 =
                LocalDate.parse("20170104", DateTimeFormatter.BASIC_ISO_DATE); // yyyyMMdd

        LocalDate customParsedDate02 =
                LocalDate.parse("2017.01.04", DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        assertThat(date.toString(), is("2017-01-04"));
        assertThat(parsedDate.toString(), is("2017-01-04"));
        assertThat(customParsedDate01.toString(), is("2017-01-04"));
        assertThat(customParsedDate02.toString(), is("2017-01-04"));
    }

    @Test
    public void printDateByFormat() throws Exception {
        LocalDate date = LocalDate.of(2017, 1, 4);

        assertThat(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                is("2017/01/04"));
        assertThat(date.format(DateTimeFormatter.ofPattern("yyyy/MMM/dd")),
                is("2017/Jan/04"));
        assertThat(date.format(DateTimeFormatter.ofPattern("yyyy/MMMM/dd")),
                is("2017/January/04"));

        assertThat(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd. eee")),
                is("2017/01/04. Wed"));
        assertThat(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd. eeee")),
                is("2017/01/04. Wednesday"));
    }
}
