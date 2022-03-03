package com.sbh.cron.parser;

import com.sbh.cron.factory.ParserClassFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListParser implements Parser {

    @Override
    public List<Integer> parse(String cronExpression, Integer min, Integer max) {

        String[] expressionArr = cronExpression.split(",");

        List<Integer> resultList = new ArrayList<>();

        ParserClassFactory parserClassFactory = ParserClassFactory.getParserClassFactory();

        Arrays.stream(expressionArr).forEach(str -> resultList.addAll(parserClassFactory.getParser(str).parse(str, min, max)));

        return resultList.stream().distinct().sorted().collect(Collectors.toList());
    }
}
