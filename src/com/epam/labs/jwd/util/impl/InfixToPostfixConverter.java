package com.epam.labs.jwd.util.impl;

import com.epam.labs.jwd.interpreter.Operation;
import com.epam.labs.jwd.util.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public enum InfixToPostfixConverter implements Converter {
    INSTANCE;

    @Override
    public List<String> convert(String in) {
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        StringBuilder number = new StringBuilder();
        StringBuilder operator;
        for (int i = 0; i < in.length(); ++i) {
            char c = in.charAt(i);
            if (Character.isDigit(c)) {
                number.append(c);
            } else {
                if (!number.toString().trim().equals("")) {
                    result.add(number.toString());
                    number = new StringBuilder();
                }
                if (c == '(') {
                    stack.push(String.valueOf(c));
                } else if (c == ')') {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        result.add(stack.pop());
                    }
                    stack.pop();
                } else {
                    operator = new StringBuilder();
                    if (c == '<' || c == '>') {
                        operator.append(c).append(c);
                        i++;
                    } else {
                        operator.append(c);
                    }
                    while (!stack.isEmpty() && Operation.priority(operator.toString()) <= Operation.priority(stack.peek())) {
                        result.add(stack.pop());
                    }
                    stack.push(operator.toString());
                }
            }
        }
        if (!number.toString().trim().equals("")) {
            result.add(number.toString());
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}
