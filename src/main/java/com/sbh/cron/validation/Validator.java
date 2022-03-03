package com.sbh.cron.validation;

import com.sbh.cron.exception.CronException;

public class Validator {

    public static boolean validate(int min, int max, String[] expressionArr) {

        if (expressionArr.length != 2) {
            throw new CronException(
                    "Invalid length : " + String.join(" ", expressionArr));
        }

        if (Integer.parseInt(expressionArr[0]) < min || Integer.parseInt(expressionArr[1]) > max) {
            throw new CronException(String.format("Expression contains Invalid range, allowed range is between  %s , %s but passed value was %s , %s",
                    min, max, expressionArr[0], expressionArr[1]));
        }

        return true;
    }
}
