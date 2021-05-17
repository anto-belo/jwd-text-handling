package com.epam.labs.jwd.parser.impl;

import com.epam.labs.jwd.entity.Paragraph;
import com.epam.labs.jwd.entity.Sentence;
import com.epam.labs.jwd.entity.TextComponent;
import com.epam.labs.jwd.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {
    private static final String SENTENCE_ENDING = "[.]{1,3}|[!?]";

    public ParagraphParser(Parser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String toParse) {
        String strParagraph = toParse.replaceAll("\r\n", " ").trim();
        ArrayList<String> strSentences = getSentences(strParagraph);

        List<Sentence> sentences = new ArrayList<>();
        for (String strSentence : strSentences) {
            sentences.add((Sentence) nextParser.parse(strSentence));
        }
        return new Paragraph(sentences);
    }

    private ArrayList<String> getSentences(String strParagraph) {
        ArrayList<String> strSentences = new ArrayList<>();
        Pattern pattern = Pattern.compile(SENTENCE_ENDING);
        Matcher matcher = pattern.matcher(strParagraph);
        while (matcher.find()) {
            String sentence = strParagraph.substring(0, matcher.end());
            strSentences.add(sentence);
            if (matcher.end() == strParagraph.length()) {
                break;
            }
            strParagraph = strParagraph.substring(matcher.end() + 1).trim();
            matcher = pattern.matcher(strParagraph);
        }
        return strSentences;
    }
}
