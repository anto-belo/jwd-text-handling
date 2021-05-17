package com.epam.labs.jwd.parser;

import com.epam.labs.jwd.entity.Text;
import com.epam.labs.jwd.parser.impl.ParagraphParser;
import com.epam.labs.jwd.parser.impl.SentenceParser;
import com.epam.labs.jwd.parser.impl.TextParser;
import com.epam.labs.jwd.parser.impl.TokenParser;
import com.epam.labs.jwd.util.Reader;
import com.epam.labs.jwd.util.impl.TextFileReader;

public enum ParserWrap {
    INSTANCE;

    private final Parser parser = new TextParser(
            new ParagraphParser(
                    new SentenceParser(
                            new TokenParser())));

    public Text parseText(String fileName) {
        Reader reader = TextFileReader.INSTANCE;
        String text = reader.read(fileName);
        return (Text) parser.parse(text);
    }
}
