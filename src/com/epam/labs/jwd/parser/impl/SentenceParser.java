package com.epam.labs.jwd.parser.impl;

import com.epam.labs.jwd.entity.Sentence;
import com.epam.labs.jwd.entity.TextComponent;
import com.epam.labs.jwd.entity.Token;
import com.epam.labs.jwd.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class SentenceParser extends AbstractParser {
    private static final String TOKEN_SEPARATOR = " ";

    public SentenceParser(Parser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String toParse) {
        String[] strTokens = toParse.split(TOKEN_SEPARATOR);

        List<Token> tokens = new ArrayList<>();
        for (String strToken : strTokens) {
            tokens.add((Token) nextParser.parse(strToken));
        }
        return new Sentence(tokens);
    }
}
