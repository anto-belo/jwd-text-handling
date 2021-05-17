package com.epam.labs.jwd.parser.impl;

import com.epam.labs.jwd.entity.Paragraph;
import com.epam.labs.jwd.entity.Text;
import com.epam.labs.jwd.entity.TextComponent;
import com.epam.labs.jwd.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class TextParser extends AbstractParser {
    private static final String PARAGRAPH_SEPARATOR = "\r\n(\t|[ ]{4})";

    public TextParser(Parser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String toParse) {
        String[] strParagraphs = toParse.split(PARAGRAPH_SEPARATOR);

        List<Paragraph> paragraphs = new ArrayList<>();
        for (String strParagraph : strParagraphs) {
            paragraphs.add((Paragraph) nextParser.parse(strParagraph));
        }
        return new Text(paragraphs);
    }
}
