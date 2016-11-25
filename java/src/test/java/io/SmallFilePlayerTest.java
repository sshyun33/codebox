package io;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SmallFilePlayerTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Path localPathFile;
    private Path localPathDir;
    private String text;

    @Before
    public void setup() throws IOException, URISyntaxException {
        text = "line1\nline2\nline3\n";
        localPathDir = FileSystems.getDefault().getPath("/home/ilanian/io-test");
        localPathFile = FileSystems.getDefault().getPath(localPathDir.toString(), "sample.txt");

        Files.deleteIfExists(localPathFile);
        Files.deleteIfExists(localPathDir);
    }

    @Test
    public void WhenCreateDirAndFileInLocalThenCreate() throws IOException {
        Files.createDirectory(localPathDir);
        Files.createFile(localPathFile);

        assertThat(Files.isDirectory(localPathDir), is(true));
        assertThat(Files.isRegularFile(localPathFile), is(true));
    }

    @Test
    public void GivenAlreadyExistWhenCreateDirectoryThenException() throws IOException {
        Files.createDirectory(localPathDir);

        exception.expect(FileAlreadyExistsException.class);
        Files.createDirectory(localPathDir);
    }

    @Test
    public void WhenRemoveFileAndDirInLocalThenRemove() throws IOException {
        // delete file in directory before removing directory
        // otherwise it raises DirectoryNotEmptyException
        Files.createDirectory(localPathDir);
        Files.createFile(localPathFile);
        Files.delete(localPathFile);
        Files.delete(localPathDir);

        assertThat(Files.isDirectory(localPathDir), is(false));
        assertThat(Files.isRegularFile(localPathFile), is(false));
    }

    @Test
    public void GivenNotExistWhenRemoveFileAndDirInLocalThenException() throws IOException {
        exception.expect(NoSuchFileException.class);
        Files.delete(localPathFile);
    }

    @Test
    public void WhenReadAfterCreateTextFileInLocalThenRead() throws IOException {
        List<String> expectedLines =
                Arrays.asList("line1", "line2", "line3");

        Files.createDirectory(localPathDir);
        // Create and write simultaneously
        Files.write(localPathFile, text.getBytes());

        assertThat(Files.readAllBytes(localPathFile), is(text.getBytes()));
        assertThat(Files.readAllLines(localPathFile), is(expectedLines));
    }

    @Test
    public void GivenTextFileExistWhenWriteThenOverride() throws IOException {
        List<String> expectedLines =
                Arrays.asList("line1", "line2", "line3");

        Files.createDirectory(localPathDir);
        Files.write(localPathFile, text.getBytes());
        Files.write(localPathFile, text.getBytes());
        Files.write(localPathFile, text.getBytes());

        assertThat(Files.readAllLines(localPathFile), is(expectedLines));
    }

    @Test
    public void GivenTextFileExistWhenAppendThenAppend() throws IOException {
        List<String> expectedLines =
                Arrays.asList("line1", "line2", "line3", "line1", "line2", "line3");

        Files.createDirectory(localPathDir);
        Files.write(localPathFile, text.getBytes());
        Files.write(localPathFile, text.getBytes(), StandardOpenOption.APPEND);

        assertThat(Files.readAllLines(localPathFile), is(expectedLines));
    }

    @Test
    public void GivenJava8WhenFilesLinesMethodThenStream() throws IOException {
        int sumOfLineLength = 0;

        Files.createDirectory(localPathDir);
        Files.write(localPathFile, text.getBytes());

        sumOfLineLength = Files.lines(localPathFile).mapToInt(String::length).sum();

        assertThat(sumOfLineLength, is(15));
    }

    @Test
    public void ToDoTestFileInClasspath() {

    }
}