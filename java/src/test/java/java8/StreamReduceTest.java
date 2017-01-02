package java8;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StreamReduceTest {

    private List<String> strings;

    @Before
    public void setUp() throws Exception {
        strings = Arrays.asList("a", "bb", "ccc", "dddd", "eeeee");
    }

    @Test
    public void reduceWithOneArgs() throws Exception {
        int multipleOfLength = strings.stream()
                .mapToInt(String::length)
                .reduce((l1, l2) -> l1 * l2)
                .orElseThrow(RuntimeException::new);

        assertThat(multipleOfLength, is(120));
    }

    @Test
    public void ReduceWithTwoArgs() throws Exception {
        int multipleOfLength = strings.stream()
                .mapToInt(String::length)
                .reduce(1, (l1, l2) -> l1 * l2);

        assertThat(multipleOfLength, is(120));
    }

    @Test
    public void ReduceWithThreeArgs() throws Exception {
        Integer multipleOfLength = strings.stream()
                // (identity, accumulator, combiner): combiner는 parallelstream에서만 호출됨.
                .reduce(1, (sum, s) -> sum *= s.length(), (sum1, sum2) -> sum1 * sum2);

        assertThat(multipleOfLength, is(120));
    }
}
