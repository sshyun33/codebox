package datatype.string;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ListHelperMethodTest {


    private ArrayList<String> fruits;

    @Before
    public void setUp() throws Exception {
        fruits = new ArrayList<>();
    }

    @Test
    public void testListIsEmpty() throws Exception {
        assertTrue(fruits.isEmpty());
    }

    @Test
    public void testListClear() throws Exception {
        fruits.add("apple");
        assertFalse(fruits.isEmpty());

        fruits.clear();
        assertTrue(fruits.isEmpty());
    }

    @Test
    public void testGetIndexOfElements() throws Exception {
        fruits.add("apple");
        fruits.add("orange");
        fruits.add("peach");
        fruits.add("orange");

        int orangeFirstIdx = fruits.indexOf("orange");
        assertThat(orangeFirstIdx, is(1));

        int orangeLastIdx = fruits.lastIndexOf("orange");
        assertThat(orangeLastIdx, is(3));
    }
}
