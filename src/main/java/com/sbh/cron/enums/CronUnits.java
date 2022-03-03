package com.sbh.cron.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CronUnits {

    MIN(0, 59),
    HOUR(0, 23),
    DAY(1, 31),
    MONTH(1, 12),
    DAY_OF_WEEK(0, 6);

    Integer min;
    Integer max;

}
