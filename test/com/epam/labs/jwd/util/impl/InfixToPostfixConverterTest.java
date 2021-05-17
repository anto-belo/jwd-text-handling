package com.epam.labs.jwd.util.impl;

import com.epam.labs.jwd.util.Converter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class InfixToPostfixConverterTest {
    final Converter converter = InfixToPostfixConverter.INSTANCE;

    @Test
    public void testConvert_returnPostfixExpression_always() {
        String exp = "5^(1&2&(3|(4&(5|6&47)|3)|2)|1)";

        List<String> res = converter.convert(exp);

        String correct = "[5, 1, 2, &, 3, 4, 5, 6, 47, &, |, &, 3, |, |, 2, |, &, 1, |, ^]";
        assertEquals(res.toString(), correct);
    }
}
