package com.epam.labs.jwd.interpreter;

import com.epam.labs.jwd.interpreter.impl.AndExpression;
import com.epam.labs.jwd.interpreter.impl.InvExpression;
import com.epam.labs.jwd.interpreter.impl.LShiftExpression;
import com.epam.labs.jwd.interpreter.impl.OrExpression;
import com.epam.labs.jwd.interpreter.impl.RShiftExpression;
import com.epam.labs.jwd.interpreter.impl.XorExpression;

import java.util.function.BiFunction;

public enum Operation {
    OR("|", 1, OrExpression::new),
    XOR("^", 2, XorExpression::new),
    AND("&", 3, AndExpression::new),
    L_SHIFT("<<", 4, LShiftExpression::new),
    R_SHIFT(">>", 4, RShiftExpression::new),
    INV("~", 5, InvExpression::new);

    private final String value;
    private final int priority;
    private final BiFunction<Expression, Expression, Expression> creator;

    Operation(String value, int priority, BiFunction<Expression, Expression, Expression> creator) {
        this.value = value;
        this.priority = priority;
        this.creator = creator;
    }

    public static int priority(String value) {
        for (Operation operation : values()) {
            if (operation.value.equals(value)) {
                return operation.priority;
            }
        }
        return -1;
    }

    public static boolean containsOperations(String word) {
        if (word == null) {
            return false;
        }
        for (Operation o : values()) {
            if (word.contains(o.value)) {
                return true;
            }
        }
        return false;
    }

    public String getValue() {
        return value;
    }

    public BiFunction<Expression, Expression, Expression> getCreator() {
        return creator;
    }
}
