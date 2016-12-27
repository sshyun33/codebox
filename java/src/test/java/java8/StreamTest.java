package java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StreamTest {

    @Test
    public void testDistinct() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 2, 3);

        List<Integer> distinctNumber = numbers.stream().distinct().collect(toList());

        assertThat(distinctNumber, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testMatches() throws Exception {
        List<String> stringCollection =
                Arrays.asList("aaa1", "aaa2", "bbb1", "bbb2", "bbb3", "ccc", "ddd1", "ddd2");

        boolean anyStartsWithA = stringCollection.stream()
                .anyMatch(s -> s.startsWith("a"));
        assertTrue(anyStartsWithA);

        boolean allMatchWithRegex = stringCollection.stream()
                .allMatch(s -> s.matches("[a-z]{3}\\d?"));
        assertTrue(allMatchWithRegex);

        boolean noneEndWithZ = stringCollection.stream()
                .noneMatch(s -> s.endsWith("z"));
        assertTrue(noneEndWithZ);
    }
}
