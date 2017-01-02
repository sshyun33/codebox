package java8;

import org.junit.Test;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StreamCollectorsTest {
    @Test
    public void testAveragingInt() throws Exception {
        Stream<String> numbers = Stream.of("a1", "e55555", "c333", "b22", "d4444");

        Double avgLen = numbers.collect(
                Collectors.averagingInt(String::length));

        assertThat(avgLen, is(4.0)); // 2+6+4+3+5 = 20 / 5 = 4
    }

    @Test
    public void testSummarizing() throws Exception {
        Stream<String> numbers = Stream.of("a1", "b22", "c333", "d4444", "e55555");

        IntSummaryStatistics summary = numbers.collect(
                Collectors.summarizingInt(String::length));

        assertThat(summary.getCount(), is(5L));
        assertThat(summary.getSum(), is(20L));
        assertThat(summary.getMin(), is(2));
        assertThat(summary.getAverage(), is(4.000000));
        assertThat(summary.getMax(), is(6));

        summary.accept(99);  // put new value in summary
        assertThat(summary.getMax(), is(99));
    }
}
