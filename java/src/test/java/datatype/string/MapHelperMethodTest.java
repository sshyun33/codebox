package datatype.string;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.rangeClosed;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MapHelperMethodTest {

    private Map<Integer, String> fileMap;

    @Before
    public void setUp() throws Exception {
        fileMap = new HashMap<>();

        fileMap.put(1, "file1");
        fileMap.put(2, null);
        fileMap.put(3, "file3");
        fileMap.put(4, "file4");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateImmutableMap() throws Exception {
        Map<Integer, String> immutableMap = ImmutableMap.of(1, "v1", 2, "v2", 3, "v3");
        immutableMap.put(1, "new");
    }

    @Test
    public void testClearAndIsEmpty() throws Exception {
        fileMap.clear();
        assertTrue(fileMap.isEmpty());
    }

    @Test
    public void testIterateMap() throws Exception {
        fileMap.forEach((k, v) ->
                System.out.println("key: " + k + ", " + "value: " + v));
    }

    @Test
    public void testRemappingByCompute() throws Exception {
        // before java 8
/*        fileMap.keySet().forEach(key -> {
            String value = fileMap.get(key);
            if (value != null)
                fileMap.put(key, value.concat(".jsp"));
        });*/

        // computeXXX() is mutual and thread safety
        // If the function returns null, the mapping is removed.
        fileMap.keySet().forEach(key -> {
            fileMap.compute(key, (k, v) -> (v == null) ? "no file" : v.concat(".jsp"));
        }); // {1:"file1.jsp", 2: "no file", 3: "file3.jsp", 4: "file4.jsp"}

        fileMap.keySet().forEach(key -> {
            fileMap.computeIfPresent(key, (k, v) -> v.concat(".jsp"));
        }); // {1: "file1.jsp", 2: null, 3: "file3.jsp", 4: "file4.jsp"}

        fileMap.keySet().forEach(key -> {
            fileMap.computeIfAbsent(key, k -> "no file");
        }); // {1: "file1", 2: "no file", 3: "file3", 4: "file4"}
    }

    @Test
    public void testRemappingByMerge() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("first", 100);
        map.put("second", null);

        // param: key
        // param: deafultValue (if value is null)
        // param: remappingfunction((oldValue, defaultValue) -> newValue) (if value is present)
        map.merge("first", 100, (v1, v2) -> v1 + v2);
        map.merge("second", 100, (v1, v2) -> v1 + v2);

        assertThat(map.get("first"), is(200));
        assertThat(map.get("second"), is(100));
    }

    @Test
    public void testContains() throws Exception {
        // {1:"file1", 2: null, 3: "file3", 4: "file4"}
        assertTrue(fileMap.containsKey(3));
        assertTrue(fileMap.containsValue("file3"));
        assertTrue(fileMap.containsValue(null));
    }

    @Test
    public void testGetOrDefault() throws Exception {
        // {1:"file1", 2: null, 3: "file3", 4: "file4"}
        assertThat(fileMap.getOrDefault(1, "no file"), is("file1"));
        assertThat(fileMap.getOrDefault(2, "no file"), is(nullValue()));
        assertThat(fileMap.getOrDefault(5, "no file"), is("no file"));
    }

    @Test
    public void testValuesToListOrArray() throws Exception {
        Collection<String> values = fileMap.values();

        ArrayList<String> valuesList = new ArrayList<>(values);
        assertThat(valuesList, is(Arrays.asList("file1", null, "file3", "file4")));

        String[] valuesArr = values.stream().toArray(String[]::new);
        assertThat(valuesArr, is(new String[]{"file1", null, "file3", "file4"}));
    }

    @Test
    public void testPutEntry() throws Exception {
        // {1:"file1", 2: null, 3: "file3", 4: "file4"}
        rangeClosed(1, fileMap.size() + 1).forEach(i ->
                fileMap.putIfAbsent(i, "new file"));

        assertThat(fileMap, is(ImmutableMap
                .of(1, "file1", 2, "new file", 3, "file3", 4, "file4", 5, "new file")));

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "james"); // return old value or null(if absent)
        assertThat(map.get(1), is("james"));

        map.putAll(ImmutableMap.of(2, "tom", 3, "beck"));
        assertThat(map, is(ImmutableMap.of(1, "james", 2, "tom", 3, "beck")));
    }

    @Test
    public void testReplace() throws Exception {
    }
}
