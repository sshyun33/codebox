package datatype.collection;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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

    @Test
    public void testListRemoves() throws Exception {
        fruits = Lists.newArrayList("apple", "orange", "peach", "orange");
        fruits.remove(0);
        assertThat(fruits, is(Arrays.asList("orange", "peach", "orange")));

        fruits = Lists.newArrayList("apple", "orange", "peach", "orange");
        fruits.remove("orange"); // remove the first occurrence
        assertThat(fruits, is(Arrays.asList("apple", "peach", "orange")));

        fruits = Lists.newArrayList("apple", "orange", "peach", "orange");
        fruits.removeIf(x -> x.equals("orange"));
        assertThat(fruits, is(Arrays.asList("apple", "peach")));

        fruits = Lists.newArrayList("apple", "orange", "peach", "orange");
        fruits.removeAll(Arrays.asList("orange", "peach"));
        assertThat(fruits, is(Collections.singletonList("apple")));
    }
}
