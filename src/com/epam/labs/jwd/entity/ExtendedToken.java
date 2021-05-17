package com.epam.labs.jwd.entity;

public class ExtendedToken extends Token {
    private final PunctuationMark punctuationMark;

    public ExtendedToken(String word, PunctuationMark punctuationMark) {
        super(word);
        this.punctuationMark = punctuationMark;
    }

    public PunctuationMark getPunctuationMark() {
        return punctuationMark;
    }

    @Override
    public String toString() {
        return word + punctuationMark.getValue() + " ";
    }
}
