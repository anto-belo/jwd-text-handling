package com.epam.labs.jwd.entity;

import java.util.List;

public class Sentence implements TextComponent {
    private final List<Token> tokens;

    public Sentence(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public int getLengthInWordsLength() {
        int length = 0;
        for (Token t : tokens) {
            length += t.word.length();
        }
        return length;
    }

    public int getLengthInTokensLength() {
        int length = 0;
        for (Token t : tokens) {
            length += t.word.length();
            if (t instanceof ExtendedToken) {
                length += ((ExtendedToken) t).getPunctuationMark()
                        .getValue().length();
            }
        }
        return length;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Token t : tokens) {
            result.append(t);
        }
        return result.toString();
    }
}
