package br.com.dock.tech.processoseletivo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

	public static java.sql.Date converterStringData(String data) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = format.parse(data);
        java.sql.Date formatedDate = new java.sql.Date(parsed.getTime());
		return formatedDate;
	}

}
