package datatype.string;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

public class CollectionsMethodTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testCopyList() throws Exception {
        final List<Integer> src = IntStream.rangeClosed(1, 10)
                .mapToObj(Integer::new)
                .collect(Collectors.toList());

        List<Integer> dest =
                new ArrayList<>(Collections.nCopies(20, 0)); // fill 0 to dest[0]~dest[19]

        Collections.copy(dest, src); // destination must be at least as long as source list

        IntStream.rangeClosed(0, 19).forEach(i -> {
            if (i < src.size()) assertTrue(src.get(i).equals(dest.get(i)));
            else assertTrue(dest.get(i).equals(0));
        });
    }
}
