package com.sbh.cron.parser;

import com.sbh.cron.exception.CronException;
import com.sbh.cron.factory.ParserClassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StepParserTest {

    ParserClassFactory parserClassFactory;

    @BeforeEach
    public void setUp() {
        parserClassFactory = ParserClassFactory.getParserClassFactory();
    }

    @ParameterizedTest
    @ValueSource(strings = {"*/10"})
    void testParse_step_valid_month(String input) {
        List<Integer> expected = List.of(1, 11);
        assertEquals(expected, parserClassFactory.getParser(input).parse(input, 1, 12));
    }

    @Test
    void testParse_step_invalid_month() {
        String input = "1/10";
        CronException cronException = assertThrows(CronException.class, () -> parserClassFactory.getParser(input).parse(input, 1, 12));
        assertEquals("Only */step is supported, provided input expression was 1/10", cronException.getMessage());
    }

}