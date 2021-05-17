package com.epam.labs.jwd.service;

import com.epam.labs.jwd.entity.Paragraph;
import com.epam.labs.jwd.entity.Sentence;
import com.epam.labs.jwd.entity.Text;
import com.epam.labs.jwd.entity.Token;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortService {

    private final Text text;

    public SortService(Text text) {
        this.text = text;
    }

    public Text sortParagraphsBySentenceCount() {
        return new Text(
                text.getParagraphs().stream()
                        .sorted(Comparator.comparingInt(o -> o.getSentences().size()))
                        .collect(Collectors.toList())
        );
    }

    public Text sortSentencesByWordLength() {
        return sortSentences(Comparator.comparingInt(Sentence::getLengthInWordsLength));
    }

    public Text sortSentencesByTokenLength() {
        return sortSentences(Comparator.comparingInt(Sentence::getLengthInTokensLength));
    }

    public Text sortSentences(Comparator<Sentence> sentenceComparator) {
        List<Paragraph> paragraphs = text.getParagraphs();
        for (int i = 0; i < paragraphs.size(); i++) {
            paragraphs.set(i, new Paragraph(paragraphs.get(i).getSentences().stream()
                    .sorted(sentenceComparator)
                    .collect(Collectors.toList())));
        }
        return new Text(paragraphs);
    }

    public List<Token> sortTokensBySymbolContaining(char symbol) {
        List<Token> tokens = findAllTokens();
        return tokens.stream()
                .sorted(((Comparator<Token>) (o1, o2) -> {
                    int count1 = (int) o1.getWord().chars().filter(c -> c == symbol).count();
                    int count2 = (int) o2.getWord().chars().filter(c -> c == symbol).count();
                    return count1 - count2;
                }).thenComparing(Token::getWord))
                .collect(Collectors.toList());
    }

    public List<Token> findAllTokens() {
        List<Token> tokens = new ArrayList<>();
        for (Paragraph p : text.getParagraphs()) {
            for (Sentence s : p.getSentences()) {
                tokens.addAll(s.getTokens());
            }
        }
        return tokens;
    }
}
