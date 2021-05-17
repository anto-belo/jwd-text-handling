package com.epam.labs.jwd.entity;

import java.util.List;

public class Text implements TextComponent {
    private final List<Paragraph> paragraphs;

    public Text(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Paragraph p : paragraphs) {
            result.append("\n\t").append(p);
        }
        return result.toString();
    }
}
