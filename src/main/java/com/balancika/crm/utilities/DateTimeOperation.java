package com.balancika.crm.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeOperation {

	public LocalDateTime convertStringToLocalDateTime(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy h:mm a");
		if(date == null || date.equals("")){
			return null;
		}
		LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
		return localDateTime;
	}
	
	public String reverseLocalDateTimeToString(LocalDateTime date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy h:mm a");
		if(date == null){
			return null;
		}
		String reverseDate = date.format(formatter);
		return reverseDate;
	}
	
	public String reverseLocalDateTimeToFormate(LocalDateTime date, String formate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate);
		if(date == null){
			return null;
		}
		String reverseDate = date.format(formatter);
		return reverseDate;
	}
	
	public Date convertStringToDate(String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date convertDate = dateFormat.parse(date);
			return convertDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
