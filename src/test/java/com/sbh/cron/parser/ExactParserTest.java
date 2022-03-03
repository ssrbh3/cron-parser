package com.sbh.cron.parser;

import com.sbh.cron.exception.CronException;
import com.sbh.cron.factory.ParserClassFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExactParserTest {

    ParserClassFactory parserClassFactory;

    @BeforeEach
    public void setUp() {
        parserClassFactory = ParserClassFactory.getParserClassFactory();
    }

    @ParameterizedTest
    @CsvSource({"2,2", "10,10"})
    void testParse_exact_valid_minute(String input, String expected) {
        Assertions.assertEquals(List.of(Integer.parseInt(expected)), parserClassFactory.getParser(input).parse(input, 0, 59));
    }

    @ParameterizedTest
    @ValueSource(strings = {"60", "90"})
    void testParse_exact_invalid_minute(String input) {
        CronException cronException = assertThrows(CronException.class, () -> parserClassFactory.getParser(input).parse(input, 0, 59));
        assertEquals("Expression value out of bounds, range is 0, 59", cronException.getMessage());
    }
}