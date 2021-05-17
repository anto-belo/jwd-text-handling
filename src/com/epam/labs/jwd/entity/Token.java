package com.epam.labs.jwd.entity;

public class Token implements TextComponent {
    protected final String word;

    public Token(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word + " ";
    }
}
