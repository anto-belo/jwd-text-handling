package com.epam.labs.jwd.interpreter.impl;

import com.epam.labs.jwd.exception.IllegalOperatorException;
import com.epam.labs.jwd.interpreter.Evaluator;
import com.epam.labs.jwd.interpreter.Expression;
import com.epam.labs.jwd.util.Converter;
import com.epam.labs.jwd.util.impl.InfixToPostfixConverter;

import java.util.List;
import java.util.Stack;

public class SimpleEvaluator implements Evaluator {
    private static final String OPERATOR_PATTERN = "[~^&|]|(>>)|(<<)";
    private static final String UNARY_OPERATOR = "~";
    private static final String NUMBER_PATTERN = "[0-9]+";

    @Override
    public int evaluate(String toEvaluate) throws IllegalOperatorException {
        Converter converter = InfixToPostfixConverter.INSTANCE;
        List<String> tokens = converter.convert(toEvaluate);

        Stack<Expression> stack = new Stack<>();
        for (String s : tokens) {
            if (s.matches(OPERATOR_PATTERN)) {
                Expression right = null;
                if (!s.matches(UNARY_OPERATOR)) {
                    right = stack.pop();
                }
                Expression left = stack.pop();
                Expression operator = Expression.create(s, left, right);
                int result = operator.interpret();
                stack.push(new Number(result));
            } else if (s.matches(NUMBER_PATTERN)) {
                    stack.push(new Number(Integer.parseInt(s)));
            } else {
                throw new IllegalOperatorException("Unexpected operator: " + s);
            }
        }
        return stack.pop().interpret();
    }
}
