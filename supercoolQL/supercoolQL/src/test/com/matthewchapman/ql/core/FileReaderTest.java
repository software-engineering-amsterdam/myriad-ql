package com.matthewchapman.ql.core;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 02/03/2017.
 */
public class FileReaderTest {

    private File file;
    private FileReader fileReader;

    @Before
    public void setUp() {
        file = new File("res/test.txt");
        fileReader = new FileReader();
    }

    @Test
    public void readFile() {
        String output = fileReader.readFile(file);
        int expectedLines = 47;
        String[] lines = output.split(System.getProperty("line.separator"));

        assertEquals(expectedLines, lines.length);
    }

}