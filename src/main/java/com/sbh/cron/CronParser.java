package com.sbh.cron;


import com.sbh.cron.enums.CronUnits;
import com.sbh.cron.exception.CronException;
import com.sbh.cron.factory.ParserClassFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CronParser {

    public static final int FIELD_NAME_LENGTH = 14;
    static Map<String, List<Integer>> resultMap = new LinkedHashMap<>(); // LinkedHM to maintain the insertion order
    static List<String> readableCronResultList = List.of("minute", "hour", "day of month", "month", "day of week", "command");

    public static void main(String[] args) {
        System.out.println(new CronParser().parseAndBuildResponse(args));
    }

    public StringBuilder parseAndBuildResponse(String[] args) {

        if (args.length <= 0) {
            throw new CronException("No cron expression detected");
        }

        String[] expression = args[0].split(" ");

        if (expression.length != 6) {
            throw new CronException("Cron expression length should be equal to 6, please check input");
        }

        ParserClassFactory parserClassFactory = ParserClassFactory.getParserClassFactory();

        int i = 0;

        resultMap.put(readableCronResultList.get(i), parserClassFactory.getParser(expression[i]).parse(expression[i++], CronUnits.MIN.getMin(), CronUnits.MIN.getMax()));
        resultMap.put(readableCronResultList.get(i), parserClassFactory.getParser(expression[i]).parse(expression[i++], CronUnits.HOUR.getMin(), CronUnits.HOUR.getMax()));
        resultMap.put(readableCronResultList.get(i), parserClassFactory.getParser(expression[i]).parse(expression[i++], CronUnits.DAY.getMin(), CronUnits.DAY.getMax()));
        resultMap.put(readableCronResultList.get(i), parserClassFactory.getParser(expression[i]).parse(expression[i++], CronUnits.MONTH.getMin(), CronUnits.MONTH.getMax()));
        resultMap.put(readableCronResultList.get(i), parserClassFactory.getParser(expression[i]).parse(expression[i++], CronUnits.DAY_OF_WEEK.getMin(), CronUnits.DAY_OF_WEEK.getMax()));

        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < readableCronResultList.size() - 1; j++) {
            sb.append(readableCronResultList.get(j))
                    .append(" ".repeat(FIELD_NAME_LENGTH - readableCronResultList.get(j).length()))
                    .append(resultMap.get(readableCronResultList.get(j))
                            .stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(" ")));
            sb.append(System.lineSeparator());
        }
        sb.append(readableCronResultList.get(i)).append(" ".repeat(FIELD_NAME_LENGTH - readableCronResultList.get(i).length())).append(expression[i]);

        return sb;
    }

}
