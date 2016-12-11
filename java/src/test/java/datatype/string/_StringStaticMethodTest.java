package datatype.string;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class _StringStaticMethodTest {

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
    public void test2() throws Exception {
        text = "This is sample text.";

        assertThat(text.charAt(1), is('h'));
        // get unicode code point from index
        assertThat(text.codePointAt(1), is(104));
    }
}
