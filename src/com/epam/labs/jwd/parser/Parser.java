package com.epam.labs.jwd.parser;

import com.epam.labs.jwd.entity.TextComponent;

public interface Parser {
    TextComponent parse(String toParse);
}
