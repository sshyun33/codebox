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

    /**
     * 1) format syntax
     *   - 형식: %[argument_index$][flags][width][.precision]conversion
     *   - 예제: %3$t5.2f // 3번째 인자를 취해서 플래그(t)를 적용하고 전체 5자리를 유지(오른쪽정렬)한 후
     *                       소수점 2번째 자리까지 표시하는 실수
     *
     * ref: http://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax
     */
    @Test
    public void formatString() throws Exception {
        // format conversion
        String general = String.format("Boolean: %b, HexString(hashcode): %h, String: %s",
                true, "bb", "text");
        String character = String.format("Character: %c", 'a');
        String numeric = String.format("Decimal: %d, Octal: %o, Hex: %x, Float: %f",
                10, 10, 10, 10.123);
        String percentAndLineSeparator = String.format("Percent: 100%% %nNext line:");

        assertThat(general, is("Boolean: true, HexString(hashcode): c40, String: text"));
        assertThat(character, is("Character: a"));
        assertThat(numeric, is("Decimal: 10, Octal: 12, Hex: a, Float: 10.123000"));
        assertThat(percentAndLineSeparator, is("Percent: 100% \nNext line:"));

        // format syntax
        String formatSyntax = String.format("[%2$10.2f]", "first", 1.2345);
        assertThat(formatSyntax, is("[      1.23]"));

        // uppercase format
        String upper = String.format("%B, %S, %C, %X", false, "abc", 'a', 0xa3c);
        assertThat(upper, is("FALSE, ABC, A, A3C"));

        // datetime format
//        String dateTimeFormat = "Time: %t";
//        assertThat(dateTimeFormat, is(""));

        // flags
    }
}
