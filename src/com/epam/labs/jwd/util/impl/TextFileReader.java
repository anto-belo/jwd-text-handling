package com.epam.labs.jwd.util.impl;

import com.epam.labs.jwd.exception.IllegalFileFormatException;
import com.epam.labs.jwd.util.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public enum TextFileReader implements Reader {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(TextFileReader.class);

    @Override
    public String read(String filePath) throws IllegalFileFormatException {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            String illegalFormatMsg = "Given file can't be read: " + filePath;
            LOGGER.error(illegalFormatMsg);
            throw new IllegalFileFormatException(illegalFormatMsg);
        }
    }
}
