package com.sbh.cron.parser;

import com.sbh.cron.factory.ParserClassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WildcardParserTest {

    ParserClassFactory parserClassFactory;

    @BeforeEach
    public void setUp() {
        parserClassFactory = ParserClassFactory.getParserClassFactory();
    }

    @ParameterizedTest
    @ValueSource(strings = {"*"})
    void testParse_wildcard_valid_day_of_week(String input) {
        List<Integer> expected = List.of(0, 1, 2, 3, 4, 5, 6);
        assertEquals(expected, parserClassFactory.getParser(input).parse(input, 0, 6));
    }

}