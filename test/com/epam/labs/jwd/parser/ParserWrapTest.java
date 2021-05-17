package com.epam.labs.jwd.parser;

import com.epam.labs.jwd.entity.Text;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class ParserWrapTest {

    private final ParserWrap parser = ParserWrap.INSTANCE;

    @Test
    public void testParseText_returnsTextEntity_whenFileHasLegalFormat() {
        final String fileName = "resources/text.txt";
        Text text;

        text = parser.parseText(fileName);
        System.out.println(text);

        assertNotNull("File must contain legally formatted text", text);
    }
}
