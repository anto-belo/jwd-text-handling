package com.epam.labs.jwd.parser.impl;

import com.epam.labs.jwd.parser.Parser;

public abstract class AbstractParser implements Parser {
    protected Parser nextParser;

    public AbstractParser() {
    }

    public AbstractParser(Parser nextParser) {
        this.nextParser = nextParser;
    }
}
