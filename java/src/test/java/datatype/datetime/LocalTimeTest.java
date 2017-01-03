package datatype.datetime;

import org.junit.Test;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class LocalTimeTest {

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
}
