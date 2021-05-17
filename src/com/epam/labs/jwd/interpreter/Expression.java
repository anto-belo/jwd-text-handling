package com.epam.labs.jwd.interpreter;

import com.epam.labs.jwd.exception.IllegalOperatorException;

public interface Expression {
    static Expression create(String operator, Expression left, Expression right) throws IllegalOperatorException {
        for (Operation o : Operation.values()) {
            if (o.getValue().equals(operator)) {
                return o.getCreator().apply(left, right);
            }
        }
        throw new IllegalOperatorException("Unexpected operation: " + operator);
    }

    int interpret();
}
