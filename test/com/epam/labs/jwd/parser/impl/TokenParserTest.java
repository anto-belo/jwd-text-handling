package com.epam.labs.jwd.parser.impl;

import com.epam.labs.jwd.entity.Token;
import com.epam.labs.jwd.parser.Parser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TokenParserTest {
    private final Parser parser = new TokenParser();

    @Test
    public void testParse_returnUnparsedExpressionToken_whenCannotEvaluateWord() {
        String word = "4?<<5";

        Token t = (Token) parser.parse(word);

        assertEquals(word, t.getWord());
    }
}
