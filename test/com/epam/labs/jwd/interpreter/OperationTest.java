package com.epam.labs.jwd.interpreter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class OperationTest {
    @Test
    public void testContainsOperations_returnFalse_whenWordNull() {
        String word = null;

        boolean test = Operation.containsOperations(word);

        assertFalse(test);
    }

    @Test
    public void testContainsOperations_returnFalse_whenNoOperationsInWord() {
        String word = "44";

        boolean test = Operation.containsOperations(word);

        assertFalse(test);
    }

    @Test
    public void testContainsOperations_returnTrue_whenSomeOperationsInWord() {
        String word = "44<<34";

        boolean test = Operation.containsOperations(word);

        assertTrue(test);
    }
}
