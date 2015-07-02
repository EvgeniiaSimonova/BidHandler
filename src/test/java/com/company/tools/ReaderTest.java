package com.company.tools;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class ReaderTest {

    @Test
    public void testCorrectFile() throws URISyntaxException {
        File file = new File(getClass().getClassLoader().getResource("inputTest.txt").toURI());
        Reader reader = new Reader(file);
        List<String> actualResult = reader.getStringList();
        List<String> expectedResult = new LinkedList<>();
        expectedResult.add("line 1,");
        expectedResult.add("line 2.");

        for (int i = 0; i < expectedResult.size(); i++) {
            String actual = actualResult.get(i);
            String expected = expectedResult.get(i);
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void testIncorrectFile() {
        File file = new File("incorrect.txt");
        Reader reader = new Reader(file);
        List<String> actualResult = reader.getStringList();

        Assert.assertEquals(0, actualResult.size());
    }
}
