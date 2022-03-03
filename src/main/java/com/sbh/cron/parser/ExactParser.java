package com.sbh.cron.parser;

import com.sbh.cron.exception.CronException;

import java.util.List;

public class ExactParser implements Parser {

    @Override
    public List<Integer> parse(String cronExpression, Integer min, Integer max) {

        int expressionVal = Integer.parseInt(cronExpression);
        if (expressionVal < min || expressionVal > max) {
            throw new CronException(String.format("Expression value out of bounds, range is %s, %s",
                    min, max));
        }
        return List.of(expressionVal);
    }
}
