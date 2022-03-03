package com.sbh.cron.parser;

import com.sbh.cron.exception.CronException;
import com.sbh.cron.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class StepParser implements Parser {

    @Override
    public List<Integer> parse(String cronExpression, Integer min, Integer max) {

        Integer firstVal;
        String[] expressionArr = cronExpression.split("/");

        if (expressionArr[0].equals("*")) {
            firstVal = min;
            expressionArr[0] = min.toString();
        }
        else if(Integer.parseInt(expressionArr[0]) > min && Integer.parseInt(expressionArr[0]) <= max && Integer.parseInt(expressionArr[0]) > Integer.parseInt(expressionArr[1]) ){
            firstVal = Integer.parseInt(expressionArr[0]);
        }
        else
            throw new CronException("Only */step is supported, provided input expression was " + cronExpression);

        Validator.validate(min, max, expressionArr);

        List<Integer> list = new ArrayList<>();
        list.add(firstVal);

        int expressionVal = Integer.parseInt(expressionArr[1]);

        for (int i = firstVal + expressionVal; max / i > 0 ; i = i + expressionVal) {
            list.add(i);
        }

        return list;
    }
}
