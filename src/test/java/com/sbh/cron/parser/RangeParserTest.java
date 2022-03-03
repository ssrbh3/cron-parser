package com.sbh.cron.parser;

import com.sbh.cron.exception.CronException;
import com.sbh.cron.factory.ParserClassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RangeParserTest {

    ParserClassFactory parserClassFactory;

    @BeforeEach
    public void setUp() {
        parserClassFactory = ParserClassFactory.getParserClassFactory();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-10"})
    void testParse_range_valid_day(String input) {
        List<Integer> expected = IntStream.rangeClosed(1, 10)
                .boxed().collect(Collectors.toList());
        assertEquals(expected, parserClassFactory.getParser(input).parse(input, 1, 31));
    }


    @ParameterizedTest
    @ValueSource(strings = {"*-5"})
    void testParse_range_invalid_day(String input) {
        CronException cronException = assertThrows(CronException.class, () -> parserClassFactory.getParser(input).parse(input, 1, 31));
        assertEquals("Invalid expression *-5, could not instantiate matching parser", cronException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-5"})
    void testParse_range_invalid_length_day(String input) {
        CronException cronException = assertThrows(CronException.class, () -> parserClassFactory.getParser(input).parse(input, 1, 31));
        assertEquals("Invalid expression -5, could not instantiate matching parser",
                cronException.getMessage());
    }
}