package regex;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

// Reference: http://tutorials.jenkov.com/java-regex/index.html
public class RegexTest {
    private List<String> result;
    private String text;
    private String patternString;

    @Before
    public void setUp() throws Exception {
        result = new ArrayList<>();
    }

    @Test
    public void testWholeTextMatches() throws Exception {
        text = "John writes about this, and John Doe writes about that," +
                " and John Wayne writes about everything.";
        patternString = ".*this, and.*";

        // Match the pattern against the whole text
        assertTrue(text.matches(patternString));
        assertTrue(Pattern.matches(patternString, text));
    }

    @Test
    public void testMatchesWithFlag() throws Exception {
        text = "John writes about this, and John Doe writes about that," +
                " and John Wayne writes about everything.";
        patternString = ".*THIS, and.*";

        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        // Matcher.matches(): Match the pattern against the whole text
        assertTrue(pattern.matcher(text).matches());

        patternString = ".*THIS, and";
        // Matcher.lookingAt(): Match the pattern against the beginning of the text
        assertTrue(pattern.matcher(text).lookingAt());
    }

    @Test
    public void testHowManyMatches() throws Exception {
        text = "John writes about this, and John Doe writes about that," +
                " and John Wayne writes about everything.";
        patternString = "John";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
            result.add("found: " + count + " : "
                    + matcher.start() + " - " + matcher.end());
        }

        // start(): return index at the beginning of matching
        // end(): return index after(+1) the end of matching
        assertThat(result.get(0), is("found: 1 : 0 - 4"));
        assertThat(result.get(1), is("found: 2 : 28 - 32"));
        assertThat(result.get(2), is("found: 3 : 60 - 64"));
    }

    @Test
    public void testMatchesAfterReset() throws Exception {
        text = "John writes about this, and John Doe writes about that," +
                " and John Wayne writes about everything.";
        patternString = "John";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        // move cursor forward
        if (matcher.find())
            result.add("found: " + matcher.start() + " - " + matcher.end());

        // move cursor at the beginning
        matcher.reset();

        if (matcher.find())
            result.add("found: " + matcher.start() + " - " + matcher.end());

        assertThat(result.get(0), is("found: 0 - 4"));
        assertThat(result.get(1), is("found: 0 - 4"));
    }

    @Test
    public void testMatchesByGroup() throws Exception {
        text = "John writes about this, and John Doe writes about that," +
                " and John Wayne writes about everything.";
        patternString = "(John) (.+?) ";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            result.add(matcher.group(1) +
                    " " + matcher.group(2));
        }

        assertThat(result.get(0), is("John writes"));
        assertThat(result.get(1), is("John Doe"));
        assertThat(result.get(2), is("John Wayne"));
    }

    @Test
    public void testMatchesByNestedGroup() throws Exception {
        text = "John writes about this, and John Doe writes about that," +
                " and John Wayne writes about everything.";
        patternString = "((John) (.+?)) ";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            result.add(
                    String.format("<%s> <%s> <%s>",
                            matcher.group(1),
                            matcher.group(2),
                            matcher.group(3)));
        }

        assertThat(result.get(0), is("<John writes> <John> <writes>"));
        assertThat(result.get(1), is("<John Doe> <John> <Doe>"));
        assertThat(result.get(2), is("<John Wayne> <John> <Wayne>"));
    }

    @Test
    public void testReplaceFirstAndAll() throws Exception {
        text = "John writes about this, and John Doe writes about that," +
                " and John Wayne writes about everything.";
        patternString = "((John) (.+?)) ";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        String replaceFirst = matcher.replaceFirst("Joe Blocks ");
        assertThat(replaceFirst, is("Joe Blocks about this, and John Doe writes about that," +
                " and John Wayne writes about everything."));

        String replaceAll = matcher.replaceAll("Joe Blocks ");
        assertThat(replaceAll, is("Joe Blocks about this, and Joe Blocks writes about that," +
                " and Joe Blocks writes about everything."));

        assertThat(text.replaceFirst(patternString, "Joe Blocks "),
                is("Joe Blocks about this, and John Doe writes about that," +
                " and John Wayne writes about everything."));

        assertThat(text.replaceAll(patternString, "Joe Blocks "),
                is("Joe Blocks about this, and Joe Blocks writes about that," +
                " and Joe Blocks writes about everything."));
    }

    @Test
    public void testAppendReplacementAndAppendTail() throws Exception {
        // To do
    }
}
