package io;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PathTest {

    private Path absolutePath;
    private Path relativePath;

    @Test
    public void testGetXXX() throws Exception {
        relativePath = Paths.get("dirs/bbb.txt");
        assertThat(relativePath.toString(), is("dirs/bbb.txt"));

        absolutePath = Paths.get("/tmp", "files", "aaa.txt");
        assertThat(absolutePath.toString(), is("/tmp/files/aaa.txt"));

        assertThat(absolutePath.getFileName().toString(), is("aaa.txt"));
        assertThat(absolutePath.getParent().toString(), is("/tmp/files"));
        assertThat(absolutePath.getRoot().toString(), is("/"));
        assertThat(absolutePath.getName(0).toString(), is("tmp"));

        assertThat(absolutePath.getNameCount(), is(3));
    }

    @Test
    public void testToAbsolutePath() throws Exception {
        absolutePath = Paths.get("/tmp", "files", "aaa.txt");
        relativePath = Paths.get("dirs/bbb.txt");

        assertTrue(absolutePath.isAbsolute());
        assertFalse(relativePath.isAbsolute());

        assertThat(absolutePath.toAbsolutePath().toString(),
                is("/tmp/files/aaa.txt"));

        assertThat(relativePath.toAbsolutePath().toString(),
                is(Paths.get("").toAbsolutePath().toString() + "/dirs/bbb.txt"));
                // ex. /home/ilanian/Workspace/idea/codebox/java/dir01/ccc.txt
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void testPathIterator() throws Exception {
        absolutePath = Paths.get("/tmp", "files", "aaa.txt");

        List<String> paths = Stream.generate(absolutePath.iterator()::next).limit(3)
                .map(path -> path.toString())
                .collect(Collectors.toList());
        assertThat(paths, is(Arrays.asList("tmp", "files", "aaa.txt")));
    }

    @Test
    public void testResolve() throws Exception {
        absolutePath = Paths.get("/tmp", "files");
        assertThat(absolutePath.resolve("aaa.txt").toString(),
                is("/tmp/files/aaa.txt"));
        assertThat(absolutePath.resolve(Paths.get("aaa.txt")).toString(),
                is("/tmp/files/aaa.txt"));

        assertThat(absolutePath.resolve("/aaa.txt").toString(), is("/aaa.txt"));
        assertThat(absolutePath.resolve("").toString(), is("/tmp/files"));

        assertThat(absolutePath.resolveSibling("dirs").toString(), is("/tmp/dirs"));
    }

    // inverse of resolve()
    @Test
    public void testRelativize() throws Exception {
        absolutePath = Paths.get("/tmp", "files");

        assertThat(absolutePath.relativize(Paths.get("/tmp/files/bbb.txt")).toString(),
                is("bbb.txt"));
        assertThat(absolutePath.relativize(Paths.get("/tmp/dirs")).toString(),
                is("../dirs"));
    }
}