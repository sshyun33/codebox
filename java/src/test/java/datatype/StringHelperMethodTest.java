package datatype;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StringHelperMethodTest {

    private String text;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createStringFromCharArray() throws Exception {
        text = "This is sample text.";

        String copy01 = String.copyValueOf(text.toCharArray());
        String copy02 = String.valueOf(text.toCharArray());
        String copy03 = String.valueOf(text.toCharArray(), 0, 3);

        assertThat(copy01, is(equalTo(text)));
        assertThat(copy01, is(not(sameInstance(text))));
        assertThat(copy02, is(equalTo(text)));
        assertThat(copy02, is(not(sameInstance(text))));
        assertThat(copy03, is(equalTo("Thi")));
    }

    @Test
    public void getCharacterOrCodePointFromIndex() throws Exception {
        text = "This is sample text.";
        // get a character from the index
        assertThat(text.charAt(1), is('h'));
        // get a unicode codepoint from the index
        assertThat(text.codePointAt(1), is(104));
    }

    @Test
    public void testStringStartsOrEndsWith() throws Exception {
        text = "This is sample text.";
        assertTrue(text.startsWith("This"));
        assertTrue(text.startsWith("is", 5));
        assertTrue(text.endsWith("text."));
    }

    @Test
    public void testTrim() throws Exception {
        text = "\n \t This is sam\tple text.  \n \r \t";
        assertThat(text.trim(), is("This is sam\tple text."));
    }

    @Test
    public void testIndexOf() throws Exception {
        text = "This is sample text.";

        assertThat(text.indexOf('i'), is(2));
        assertThat(text.lastIndexOf('i'), is(5));

        assertThat(text.indexOf('i', 3), is(5));
        assertThat(text.lastIndexOf('i', 4), is(2));

        assertThat(text.indexOf("sample"), is(8));
        assertThat(text.lastIndexOf("sample"), is(8));
    }

    @Test
    public void testEquals() throws Exception {
        text = "EveRyONe";

        assertTrue(text.equalsIgnoreCase("everyone"));

        assertTrue(text.contentEquals(new StringBuilder(text)));
        assertFalse(text.equals(new StringBuilder(text)));
    }
}
