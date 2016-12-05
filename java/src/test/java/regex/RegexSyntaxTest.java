package regex;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegexSyntaxTest {
    
    private String text;

    @Before
    public void setUp() throws Exception {
        text = "John went for a walk, and John fell down, and John hurt his knee."; 
    }

    //

    /**
     * Character classes
     * [character]: match one character
     */
    @Test
    public void testCharacterClasses() throws Exception {
        assertTrue("john".matches("[jJ]ohn")); // j or J
        assertTrue("John".matches("[jJ]ohn")); // j or J
        assertTrue("john".matches("[^a]ohn")); // not a
        assertTrue("john".matches("[^a-e]ohn")); // not a through j
        assertTrue("john".matches("[a-jA-J]ohn")); // a through j or A through J
        assertFalse("john".matches("[a-z&&[^j]]ohn")); // a through z and not j
        assertFalse("john".matches("[a-z&&[^j-z]]ohn")); // a through z and not (j through z)
    }

    /**
     * Predefined character classes
     * . : any one character
     * \d : A digit [0-9]
     * \D : A non-digit [^0-9]
     * \h : A horizontal whitespace character [ \t\xA0\u1680\u180e\u2000-\u200a\u202f\u205f\u3000]
     * \H : A non-horizontal whitespace character [^\h]
     * \s : A whitespace character [ \t\n\x0B\f\r]
     * \S : A non-whitespace character [^\s]
     * \v : A vertical whitespace character [\n\x0B\f\r\x85\u2028\u2029]
     * \V : A non-vertical whitespace character [^\v]
     * \w : A word character [a-zA-Z_0-9]
     * \W : A non-word character [^\w]
     * @throws Exception
     */
    @Test
    public void testPredefinedCharacterClasses() throws Exception {
        assertTrue("john12".matches("......"));
        assertTrue("john12".matches("\\D\\D\\D\\D\\d\\d"));
        assertTrue("john12".matches("\\w\\w\\w\\w\\w\\w"));
        assertTrue("john 12".matches("\\w\\w\\w\\w \\d\\d"));
        assertTrue("john 12".matches("\\w\\w\\w\\w\\s\\d\\d"));
        assertTrue("john 12".matches("\\w\\w\\w\\w\\h\\d\\d"));
        assertTrue("a3_* ".matches("\\w\\w\\w\\W\\s"));
    }

    /**
     * ^ : The beginning of a line
     * $ : The end of a line
     * \b : A word boundary
     * \B : A non-word boundary
     * \A : The beginning of the input
     * \G : The end of the previous match
     * \Z : The end of the input but for the final terminator, if any
     * \z : the end of the input
     * @throws Exception
     */
    @Test
    public void testBoundaryMatchers() throws Exception {
        assertTrue("John is a developer.".matches("^J.*"));
        assertTrue("John is a developer.".matches("^(John).*"));
        assertTrue("John is a developer.".matches("^(John).*\\.$"));
        assertTrue("John is a developer.".matches("^(John).*(developer\\.)$"));
        assertTrue("John is a developer.".matches("\\bJohn\\b is.*"));
        assertTrue("John is a developer.".matches("J\\Boh\\Bn is.*"));
    }
}
