package com.sbh.cron.parser;

import java.util.List;
import java.util.stream.IntStream;

public class WildcardParser implements Parser {

    @Override
    public List<Integer> parse(String cronExpression, Integer min, Integer max) {
        return IntStream.rangeClosed(min, max)
                .boxed().toList();
    }
}
