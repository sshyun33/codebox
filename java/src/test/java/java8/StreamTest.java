package java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StreamTest {

    @Test
    public void testDistinct() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 2, 3);

        List<Integer> distinctNumber = numbers.stream().distinct().collect(toList());

        assertThat(distinctNumber, is(Arrays.asList(1, 2, 3)));
    }
}
