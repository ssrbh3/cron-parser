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

class ListParserTest {

    ParserClassFactory parserClassFactory;

    @BeforeEach
    public void setUp() {
        parserClassFactory = ParserClassFactory.getParserClassFactory();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,10"})
    void testParse_list_valid_hour(String input) {
        List<Integer> expected = List.of(1, 10);
        assertEquals(expected, parserClassFactory.getParser(input).parse(input, 0, 23));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-5,10-15,*"})
    void testParse_list_valid_complex_hour(String input) {
        List<Integer> expected = IntStream.rangeClosed(0, 23)
                .boxed().collect(Collectors.toList());
        assertEquals(expected, parserClassFactory.getParser(input).parse(input, 0, 23));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,-1", "60", "90"})
    void testParse_list_invalid_hour(String input) {
        assertThrows(CronException.class, () -> parserClassFactory.getParser(input).parse(input, 0, 23));
    }

}