package ru.wmtool.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public Calendar parseStr(String date) throws ParseException{
		Calendar cal = Calendar.getInstance();
		@SuppressWarnings("deprecation")
		Date dat = new Date(date);
		cal.setTime(dat);
		return cal;
	}
	
	public Calendar parseStr(String date,String dateFormat) throws ParseException{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		cal.setTime(formatter.parse(date));
		return cal;
	}
	
	public String parseCalendar(Calendar calendar, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		String formattedDate = formatter.format(calendar.getTime());
		return formattedDate;
	}
}
