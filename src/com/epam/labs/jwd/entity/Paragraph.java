package com.epam.labs.jwd.entity;

import java.util.List;

public class Paragraph implements TextComponent {
    private final List<Sentence> sentences;

    public Paragraph(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Sentence s : sentences) {
            result.append(s);
        }
        return result.toString();
    }
}
