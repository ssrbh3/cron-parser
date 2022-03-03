package com.sbh.cron.parser;

import java.util.List;

public interface Parser {
    List<Integer> parse(String cronExpression, Integer min, Integer max);
}
