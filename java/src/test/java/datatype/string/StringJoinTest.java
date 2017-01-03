package datatype.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringJoinTest {

    // Use StringJoiner Object
    // StringJoiner has introduced since JDK 1.8
    // StringJoiner is used internally by static String.join()
    @Test
    public void useStringJoinerWithADelimiter() throws Exception {
        StringJoiner stringJoiner = new StringJoiner("-");
        stringJoiner.add("first");
        stringJoiner.add("second");
        stringJoiner.add("third");
        // String joinedString = stringJoiner.add("first").add("second").add("third").toString()

        assertThat(stringJoiner.toString(), is("first-second-third"));
    }

    @Test
    public void useJoinStringWithDelimiterPrefixSuffix() throws Exception {
        StringJoiner stringJoiner = new StringJoiner("-", "prefix-", "-suffix");
        stringJoiner.add("first");
        stringJoiner.add("second");
        stringJoiner.add("third");

        assertThat(stringJoiner.toString(), is("prefix-first-second-third-suffix"));
    }

    // Use String.join()
    @Test
    public void useStringJoinWithStringParameters() throws Exception {
        String joinedString = String.join("-", "first", "second", "third");

        assertThat(joinedString, is("first-second-third"));
    }

    @Test
    public void useStringJoinWithIterable() throws Exception {
        List<String> strings = Arrays.asList("first", "second", "third");

        String joinedString = String.join("-", strings);

        assertThat(joinedString, is("first-second-third"));
    }

    // Use Collectors.joining()
    @Test
    public void useCollectorsJoinWithADelimiterInStream() throws Exception {
        List<String> strings = Arrays.asList("first", "second", "third");

        String joinedString = strings.stream().collect(Collectors.joining("-"));

        assertThat(joinedString, is("first-second-third"));
    }

    @Test
    public void useCollectorsJoinWithADelimiterPrefixSuffixInStream() throws Exception {
        List<String> strings = Arrays.asList("first", "second", "third");

        String joinedString = strings.stream().collect(Collectors.joining("-", "prefix-", "-suffix"));

        assertThat(joinedString, is("prefix-first-second-third-suffix"));
    }
}