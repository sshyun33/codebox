package datatype.collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class QueueMethodTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Queue<Integer> queue;

    @Before
    public void setUp() throws Exception {
        queue = new LinkedList<>();
    }

    @Test
    public void testEmptyQueue() throws Exception {
        assertThat(queue.peek(), is(nullValue()));

        exception.expect(NoSuchElementException.class);
        queue.element();
    }

    @Test
    public void testQueueSize() throws Exception {
        queue.add(3);
        queue.add(7);
        queue.add(5);

        assertThat(queue.size(), is(3));
    }

    @Test
    public void testPeek_Element_Poll() throws Exception {
        queue.add(3);
        queue.add(7);
        queue.add(5);

        // peek() and element() retrieves, but does not remove element
        assertThat(queue.peek(), is(3));
        assertThat(queue.element(), is(3));

        // poll() retrieves, and removes element
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(7));
        assertThat(queue.poll(), is(5));
        assertThat(queue.poll(), is(nullValue()));
    }

    @Test
    public void name() throws Exception {
        queue.add(3);

        queue.remove(3);

        assertFalse(queue.contains(3));
    }
}
