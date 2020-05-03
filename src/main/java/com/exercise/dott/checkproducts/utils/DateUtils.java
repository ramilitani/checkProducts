package com.exercise.dott.checkproducts.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtils {

    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATETIME_FORMAT);

    public static final LocalDateTime toLocalDateTime(Date data) {
        return new java.sql.Timestamp(data.getTime()).toLocalDateTime();
    }
}
