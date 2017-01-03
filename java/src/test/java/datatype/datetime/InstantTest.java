package datatype.datetime;

import org.junit.Test;

import java.time.Instant;

public class InstantTest {
    @Test
    public void testNowInstantTime() throws Exception {
        System.out.println("Now Instant Time: " + Instant.now().getEpochSecond());
    }
}
