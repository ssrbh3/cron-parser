package com.sbh.cron.factory;

import com.sbh.cron.exception.CronException;
import com.sbh.cron.parser.ExactParser;
import com.sbh.cron.parser.ListParser;
import com.sbh.cron.parser.Parser;
import com.sbh.cron.parser.RangeParser;
import com.sbh.cron.parser.StepParser;
import com.sbh.cron.parser.WildcardParser;

public class ParserClassFactory {

    private static ParserClassFactory parserClassFactory;

    private ParserClassFactory() {
        super();
    }

    public static ParserClassFactory getParserClassFactory() {

        // this could also be made synchronised to protect against multithreading
        if (parserClassFactory == null) {
            parserClassFactory = new ParserClassFactory();//instance will be created at request time
        }
        return parserClassFactory;
    }

    public Parser getParser(String cronExpression) {

        if (cronExpression.matches("^[0-9]+$")) { // exact match
            return new ExactParser();
        } else if (cronExpression.equals("*")) { // wildcard
            return new WildcardParser();
        } else if (cronExpression.matches(".*,.*")) { // list
            return new ListParser();
        } else if (cronExpression.matches(("[0-9]+-[0-9]+"))) { // Range
            return new RangeParser();
        } else if (cronExpression.matches(".*/.*")) { // step
            return new StepParser();
        }
        throw new CronException(String.format("Invalid expression %s, could not instantiate matching parser", cronExpression));
    }

}