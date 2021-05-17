package com.epam.labs.jwd.parser.impl;

import com.epam.labs.jwd.entity.ExtendedToken;
import com.epam.labs.jwd.entity.PunctuationMark;
import com.epam.labs.jwd.entity.TextComponent;
import com.epam.labs.jwd.entity.Token;
import com.epam.labs.jwd.exception.IllegalOperatorException;
import com.epam.labs.jwd.interpreter.Evaluator;
import com.epam.labs.jwd.interpreter.Operation;
import com.epam.labs.jwd.interpreter.impl.SimpleEvaluator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TokenParser extends AbstractParser {
    private static final Logger LOGGER = LogManager.getLogger(TokenParser.class);

    public TokenParser() {
    }

    @Override
    public TextComponent parse(String toParse) {
        Object[] sentenceElements = divide(toParse);
        String word = (String) sentenceElements[0];
        if (Operation.containsOperations(word)) {
            Evaluator evaluator = new SimpleEvaluator();
            try {
                word = String.valueOf(evaluator.evaluate(word));
            } catch (IllegalOperatorException e) {
                LOGGER.error(e.getMessage());
            }
        }
        Token t;
        if (sentenceElements.length == 1) {
            t = new Token(word);
        } else {
            t = new ExtendedToken(word, (PunctuationMark) sentenceElements[1]);
        }
        return t;
    }

    private Object[] divide(String strToken) {
        for (PunctuationMark mark : PunctuationMark.values()) {
            String markVal = mark.getValue();
            if (strToken.endsWith(markVal)) {
                return new Object[]{strToken.substring(0, strToken.indexOf(markVal)), mark};
            }
        }
        return new Object[]{strToken};
    }
}
