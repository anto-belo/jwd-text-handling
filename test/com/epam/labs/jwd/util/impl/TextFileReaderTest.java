package com.epam.labs.jwd.util.impl;

import com.epam.labs.jwd.exception.IllegalFileFormatException;
import com.epam.labs.jwd.util.Reader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class TextFileReaderTest {
    private final Reader reader = TextFileReader.INSTANCE;

    @Test
    public void testRead_returnTextString_whenFileCanBeRead() {
        final String fileName = "resources/text.txt";
        String text;

        text = reader.read(fileName);

        assertNotNull(text);
    }

    @Test (expected = IllegalFileFormatException.class)
    public void testRead_throwIllegalFileFormatException_whenFileCannotBeRead() {
        final String fileName = "resources/wrong.txt";

        reader.read(fileName);
    }
}
