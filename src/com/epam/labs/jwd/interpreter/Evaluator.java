package com.epam.labs.jwd.interpreter;

import com.epam.labs.jwd.exception.IllegalOperatorException;

public interface Evaluator {
    int evaluate(String toEvaluate) throws IllegalOperatorException;
}
