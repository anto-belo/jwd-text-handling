package com.epam.labs.jwd.interpreter.impl;

import com.epam.labs.jwd.interpreter.Expression;

public abstract class AbstractExpression implements Expression {
    protected final Expression left;
    protected final Expression right;

    public AbstractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
