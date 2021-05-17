package com.epam.labs.jwd.interpreter.impl;

import com.epam.labs.jwd.interpreter.Expression;

public class InvExpression extends AbstractExpression {
    public InvExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return ~left.interpret();
    }
}
