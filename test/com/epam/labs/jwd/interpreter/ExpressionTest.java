package com.epam.labs.jwd.interpreter;

import com.epam.labs.jwd.exception.IllegalOperatorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class ExpressionTest {
    @Test
    public void testCreate_throwsIllegalOperatorException_whenUnknownOperationGiven() {
        String operator = "?";

        try {
            Expression.create(operator, null, null);
            fail();
        } catch (IllegalOperatorException e) {
            assertEquals("Unexpected operation: " + operator, e.getMessage());
        }
    }
}
