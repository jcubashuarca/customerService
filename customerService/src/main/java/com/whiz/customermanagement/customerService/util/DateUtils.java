package com.whiz.customermanagement.customerService.util;

import lombok.extern.slf4j.Slf4j;
import com.whiz.customermanagement.customerService.config.AppProperties;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtils {

    public static Date formatDate(String pattern, String dateString) {
        try {
            return new SimpleDateFormat(pattern).parse(dateString);
        } catch (ParseException e) {
            log.error("can't convert string to date : " + e.getMessage(), e);
            return null;
        }
    }

    public static Date getAttemptDeathDateByAge(AppProperties config, int age) {
        Calendar calendar = Calendar.getInstance();
        int remainingAge = new BigDecimal(config.getLifeExpectancyMan())
                .subtract(new BigDecimal(age)).intValue();
        int year = calendar.get(Calendar.YEAR);
        log.info("current year: {}", year);
        log.info("current age: {}", age);
        log.info("current attempt remaining age: {}", remainingAge);
        calendar.add(Calendar.YEAR, remainingAge);
        return calendar.getTime();
    }

    public static String getStringFromDate(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }


}
