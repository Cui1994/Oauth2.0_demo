package com.cui.demo.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

  public static Date getDateAfterSeconds(Date date, Integer seconds) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.SECOND, seconds);
    return cal.getTime();
  }

}
