package ru.javaops.restaurant_voting.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);


    // public static final LocalTime START_OF_VOTING_TIME = LocalTime.of(00,00,00);
    public static final LocalTime END_OF_VOTING_TIME = LocalTime.of(11,0,0);

    private DateTimeUtil() {
    }



    public static String toString(LocalDate dt) {
        return dt == null ? "" : dt.format(DATE_TIME_FORMATTER);
    }
}
