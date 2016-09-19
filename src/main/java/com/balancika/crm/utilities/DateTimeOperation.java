package com.balancika.crm.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
