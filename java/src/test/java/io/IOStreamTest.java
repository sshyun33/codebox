package io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IOStreamTest {
    private String inFileName;
    private String outFileName;

    @Before
    public void setUp() throws Exception {
        inFileName = "src/test/resources/io/input.txt";
        outFileName = "src/test/resources/io/output.txt";
    }

    @Test
    public void testByteStream() throws IOException {
        try (FileInputStream in = new FileInputStream(inFileName);
             FileOutputStream out = new FileOutputStream(outFileName)) {
            int c;
            // in.read(): get in's one byte by int(4byte) value
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }
    }

    @Test
    public void testCharacterStream() throws Exception {
        try (FileReader in = new FileReader(inFileName);
             FileWriter out = new FileWriter(outFileName)) {
            int c;
            // in.read(): get in's one character(digit,alphabet:1byte, korean:3byte) by int(4byte) value
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }
    }

    @Test
    public void testLineOrientedStream() throws Exception {
        try (BufferedReader in = new BufferedReader(new FileReader(inFileName));
             PrintWriter out = new PrintWriter(new FileWriter(outFileName))) {
            String line;
            // in.readLine(): get in's a line by line terminators(ex. \r\n, \n, \r)
            while ((line = in.readLine()) != null) {
                // out.println(): add current os's line terminator at line's end
                out.println(line);
            }
        }
    }

    @Test
    public void testBufferedByteStream() throws Exception {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileName));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileName))) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            out.flush(); // option: flush memory buffer to disk at this point
        }
    }

    @Test
    public void testBufferedCharacterStream() throws Exception {
        try (BufferedReader in = new BufferedReader(new FileReader(inFileName));
             BufferedWriter out = new BufferedWriter(new FileWriter(outFileName))) {

            String line;
            while ((line = in.readLine()) != null) {
                out.write(line);
                out.newLine();
            }
            out.flush(); // option: flush memory buffer to disk at this point
        }
    }

    @Test
    public void testDataStream() throws Exception {
        //  ByteStream -> BufferedStream -> DataStream
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outFileName)));
             DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(outFileName)))) {
            out.writeInt(1);
            out.writeDouble(1.5D);
            out.writeUTF("abcde");
            out.writeUTF("가나다라마");
            out.flush(); // important! because of remaining input task

            // must use DataInputStream with data source written DataOutputStream
            assertThat(in.readInt(), is(1));
            assertThat(in.readDouble(), is(1.5D));
            assertThat(in.readUTF(), is("abcde"));
            assertThat(in.readUTF(), is("가나다라마"));
            in.readInt(); // raise EOFException not null or -1
        } catch (EOFException e) {
            System.out.println("모든 내용을 읽었습니다.");
        }
    }
}