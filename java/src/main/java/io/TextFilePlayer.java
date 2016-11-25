package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

// Why use BuffredXXX?
// https://medium.com/@adamba/why-use-bufferedreader-and-bufferedwriter-classses-in-java-39074ee1a966#.rpvpexwbp
public class TextFilePlayer {
    public static void write(Path file, String s, Charset charset) throws IOException {
        // it create file if file didn't exist
        // Use Files.newBufferedWriter(Path) if charset is UTF-8(default)
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writer.write(s, 0, s.length());
        } catch (IOException e) {
            throw e;
        }
    }

    public static void append(Path file, String s) throws IOException {
        // require to modify
        if (!Files.isWritable(file)) Files.createFile(file);

        try (BufferedWriter writer =
                     // rasie NoSuchFileException if file doesn't exist
                     Files.newBufferedWriter(file, StandardOpenOption.APPEND)) {
            writer.append(s);
        } catch (IOException e) {
            throw e;
        }
    }

    public static List<String> readAllLines(Path file, String s) throws IOException {
        List<String> allLines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
            }
        } catch (IOException e) {
            throw e;
        }
        return allLines;
    }
}
