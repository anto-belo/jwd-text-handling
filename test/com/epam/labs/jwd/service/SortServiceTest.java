package com.epam.labs.jwd.service;

import com.epam.labs.jwd.entity.ExtendedToken;
import com.epam.labs.jwd.entity.Paragraph;
import com.epam.labs.jwd.entity.PunctuationMark;
import com.epam.labs.jwd.entity.Sentence;
import com.epam.labs.jwd.entity.Text;
import com.epam.labs.jwd.entity.Token;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class SortServiceTest {
    private Text text;
    private SortService service;

    @Before
    public void setUp() {
        text = new Text(
                new ArrayList<>(Arrays.asList(
                        new Paragraph(Arrays.asList(
                                new Sentence(Arrays.asList(
                                        new ExtendedToken("Hello", PunctuationMark.COMMA),
                                        new ExtendedToken("World", PunctuationMark.EXCLAMATION)
                                )),
                                new Sentence(Arrays.asList(
                                        new Token("This"),
                                        new Token("is"),
                                        new Token("simple"),
                                        new ExtendedToken("test", PunctuationMark.EXCLAMATION)
                                ))
                        )),
                        new Paragraph(
                                Arrays.asList(
                                        new Sentence(Arrays.asList(
                                                new Token("This"),
                                                new Token("is"),
                                                new Token("very"),
                                                new Token("simple"),
                                                new ExtendedToken("test", PunctuationMark.EXCLAMATION)
                                        )),
                                        new Sentence(Arrays.asList(
                                                new Token("This"),
                                                new ExtendedToken("test", PunctuationMark.EXCLAMATION)
                                        )),
                                        new Sentence(Collections.singletonList(
                                                new Token("This")
                                        )),
                                        new Sentence(Arrays.asList(
                                                new Token("This"),
                                                new Token("is"),
                                                new ExtendedToken("test", PunctuationMark.EXCLAMATION)
                                        ))
                                )),
                        new Paragraph(
                                Collections.singletonList(
                                        new Sentence(Arrays.asList(
                                                new Token("The"),
                                                new Token("end")
                                        ))
                                ))
                ))
        );
        service = new SortService(text);
    }

    @Test
    public void testSortParagraphsBySentenceCount_returnSortedText_always() {
        text = service.sortParagraphsBySentenceCount();

        List<Paragraph> paragraphs = text.getParagraphs();
        for (int i = 0; i < paragraphs.size() - 1; i++) {
            int sizePrev = paragraphs.get(i).getSentences().size();
            int sizeNext = paragraphs.get(i + 1).getSentences().size();
            assertTrue("Sentences count in the previous paragraph is more than in the next",
                    sizePrev <= sizeNext);
        }
    }

    @Test
    public void testSortSentencesByWordLength_returnSortedText_always() {
        text = service.sortSentencesByWordLength();

        for (Paragraph p : text.getParagraphs()) {
            List<Sentence> sentences = p.getSentences();
            for (int i = 0; i < sentences.size() - 1; i++) {
                int lengthPrev = sentences.get(i).getLengthInWordsLength();
                int lengthNext = sentences.get(i + 1).getLengthInWordsLength();
                assertTrue("Previous sentence length in words is more than next sentence length",
                        lengthPrev <= lengthNext);
            }
        }
    }

    @Test
    public void testSortSentencesByTokenLength_returnSortedText_always() {
        text = service.sortSentencesByTokenLength();

        for (Paragraph p : text.getParagraphs()) {
            List<Sentence> sentences = p.getSentences();
            for (int i = 0; i < sentences.size() - 1; i++) {
                int lengthPrev = sentences.get(i).getLengthInTokensLength();
                int lengthNext = sentences.get(i + 1).getLengthInTokensLength();
                assertTrue("Previous sentence length in tokens is more than next sentence length",
                        lengthPrev <= lengthNext);
            }
        }
    }

    @Test
    public void testSortTokensBySymbolContaining_returnSortedTokenList_always() {
        char c = 'e';
        List<Token> sortedTokens = service.sortTokensBySymbolContaining(c);

        for (int i = 0; i < sortedTokens.size() - 1; i++) {
            Token t1 = sortedTokens.get(i);
            Token t2 = sortedTokens.get(i + 1);
            int count1 = (int) t1.getWord().chars().filter(cs -> cs == c).count();
            int count2 = (int) t2.getWord().chars().filter(cs -> cs == c).count();
            boolean sorted = count1 < count2;
            if (count1 == count2) {
                sorted = t1.getWord().compareTo(t2.getWord()) <= 0;
            }
            assertTrue(sorted);
        }
    }
}
