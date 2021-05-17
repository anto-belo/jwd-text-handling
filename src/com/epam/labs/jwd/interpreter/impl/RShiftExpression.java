package com.epam.labs.jwd.interpreter.impl;

import com.epam.labs.jwd.interpreter.Expression;

public class RShiftExpression extends AbstractExpression {
    public RShiftExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return left.interpret() >> right.interpret();
    }
}
