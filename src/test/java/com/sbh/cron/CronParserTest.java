package com.sbh.cron;

import com.sbh.cron.exception.CronException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CronParserTest {

    private CronParser cronParser;

    @BeforeEach
    public void setUp() {
        cronParser = new CronParser();
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", "*/15 0 1-5,1-15,20-22 * 1-5", "*/100 0 1-5 * 1-5 /usr/bin/find"})
    public void testParseAndBuildResponse_InvalidInputs(String input) {
        assertThrows(CronException.class, () -> cronParser.parseAndBuildResponse(new String[]{input}));
    }

    @Test
    public void testParseAndBuildResponse_complexCron() throws IOException {

        Path path = Paths.get("src", "test", "resources", "response1");
        String expected = Files.readString(path);

        assertEquals(expected, cronParser.parseAndBuildResponse(new String[]{"*/15 0 1-5,1-15,20-22 * 1-5 /usr/bin/find"}).toString());
    }

    @Test
    public void testParseAndBuildResponse_simpleCron() throws IOException {

        Path path = Paths.get("src", "test", "resources", "response2");
        String expected = Files.readString(path);
        assertEquals(expected, cronParser.parseAndBuildResponse(new String[]{"*/15 0 1,5 * 1-5 /usr/bin/find"}).toString());
    }

    @Test
    public void testParseAndBuildResponse_simplestCron() throws IOException {

        Path path = Paths.get("src", "test", "resources", "response3");
        String expected = Files.readString(path);
        assertEquals(expected, cronParser.parseAndBuildResponse(new String[]{"* * * * * /usr/bin/find"}).toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"****", " ", "1/12 * * * * /usr/bin/find"})
    public void testParseAndBuildResponse_Errors(String input) {
       assertThrows(CronException.class , ()->  cronParser.parseAndBuildResponse(new String[]{input}));
    }

}
