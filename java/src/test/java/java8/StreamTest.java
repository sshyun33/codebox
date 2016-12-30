package java8;

import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

    @Test
    public void testReuseStream() throws Exception {
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("a2", "a2", "b1", "b3", "c");

        boolean use01 = streamSupplier.get().noneMatch(s -> s.equals("e"));
        assertTrue(use01);

        boolean use02 = streamSupplier.get().anyMatch(s -> s.equals("b1"));
        assertTrue(use02);
    }

    @Test
    public void testInfiniteStream() throws Exception {
        Stream<Integer> infiniteNumbers = Stream.iterate(0, n -> n + 2);
        List<Integer> evenNumbers = infiniteNumbers
                .limit(5) // 빼먹으면 무한 실행!
                .collect(toList());

        Stream<Integer> infiniteRandomNumbers =
                Stream.generate(() -> new Random().nextInt(10) + 1); // random(1~10)

        boolean isInOneToTen = infiniteRandomNumbers
                .limit(100)
                .allMatch(n -> n >= 1 && n <= 10);

        assertTrue(isInOneToTen);
    }

    @Test
    public void testMapToX() throws Exception {
        double avg = Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .average()
                .orElseThrow(NullPointerException::new);

        assertThat(avg , is(2.0));

        List<String> strings = Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .collect(toList());

        assertThat(strings, is(Arrays.asList("a1", "a2", "a3")));
    }
}
