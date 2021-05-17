package com.epam.labs.jwd.entity;

public enum PunctuationMark implements TextComponent {
    ELLIPSIS("..."),
    DOUBLE_DOT(".."),
    DOT("."),
    COMMA(","),
    SEMICOLON(";"),
    EXCLAMATION("!"),
    QUESTION("?"),
    DASH("-");

    private final String value;

    PunctuationMark(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
