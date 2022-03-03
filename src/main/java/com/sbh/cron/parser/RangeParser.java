package com.sbh.cron.parser;

import com.sbh.cron.validation.Validator;

import java.util.List;
import java.util.stream.IntStream;

public class RangeParser implements Parser {
    @Override
    public List<Integer> parse(String cronExpression, Integer min, Integer max) {

        String[] expressionArr = cronExpression.split("-");
        Validator.validate(min, max, expressionArr);

        return IntStream.rangeClosed(Integer.parseInt(expressionArr[0]), Integer.parseInt(expressionArr[1]))
                .boxed().toList();
    }
}
