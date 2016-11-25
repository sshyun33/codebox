package io;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TextFilePlayerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Path localPathFile;
    private Path localPathDir;
    private String text;
    private Charset charset;

    @Before
    public void setup() throws IOException, URISyntaxException {
        localPathDir = FileSystems.getDefault()
                                    .getPath("/home/ilanian/io-test");
        localPathFile = FileSystems.getDefault()
                                    .getPath("/home/ilanian/sample.txt");
        charset = Charset.forName("UTF-8");
        text = "line1\nline2\nline3\n";

        Files.deleteIfExists(localPathFile);
    }

    @Test
    public void WhenCreateDirAndFileInLocalThenCreate() throws IOException {
        // Use Files.createXXX(), Files.deleteXXX() Method
    }

    @Test
    public void GivenNotExistedWhenWriteFileThenCreate() throws IOException {
        List<String> expected = Arrays.asList("line1", "line2", "line3");

        TextFilePlayer.write(localPathFile, text, charset);

        assertThat(Files.readAllLines(localPathFile), is(expected));
    }

    @Test
    public void GivenTextFileExistWhenWriteThenOverride() throws IOException {
        List<String> expected = Arrays.asList("line1", "line2", "line3");

        TextFilePlayer.write(localPathFile, text, charset);
        TextFilePlayer.write(localPathFile, text, charset);

        assertThat(Files.readAllLines(localPathFile), is(expected));
    }

    @Test
    public void GivenTextFileExistWhenAppendThenAppend() throws IOException {
        List<String> expected = Arrays.asList("line1", "line2", "line3",
                "line1", "line2", "line3");

        TextFilePlayer.append(localPathFile, text);
        TextFilePlayer.append(localPathFile, text);

        assertThat(Files.readAllLines(localPathFile), is(expected));
    }

    @Test
    public void WhenReadFileInLocalThenRead() throws IOException {
        List<String> expected = Arrays.asList("line1", "line2", "line3");
        TextFilePlayer.write(localPathFile, text, charset);

        List<String> lines = TextFilePlayer.readAllLines(localPathFile, text);

        assertThat(lines, is(expected));
    }

    @Test
    public void GivenFileNotExistWhenReadFileInLocalThenException() throws IOException {
        exception.expect(NoSuchFileException.class);
        TextFilePlayer.readAllLines(localPathFile, text);
    }

    @Test
    public void ToDoTestFileInClasspath() {

    }
}