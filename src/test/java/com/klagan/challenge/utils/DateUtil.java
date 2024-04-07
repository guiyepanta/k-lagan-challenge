package com.klagan.challenge.utils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDateTime getDateFromString(String dateInString) throws ParseException {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	LocalDateTime dateTime = LocalDateTime.parse(dateInString, formatter);

	return dateTime;
    }
}
