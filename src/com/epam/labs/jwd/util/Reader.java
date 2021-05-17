package com.epam.labs.jwd.util;

import com.epam.labs.jwd.exception.IllegalFileFormatException;

public interface Reader {
    String read(String filePath) throws IllegalFileFormatException;
}
