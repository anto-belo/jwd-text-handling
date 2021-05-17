package com.epam.labs.jwd.interpreter.impl;

import com.epam.labs.jwd.interpreter.Expression;

public class XorExpression extends AbstractExpression {
    public XorExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return left.interpret() ^ right.interpret();
    }
}
