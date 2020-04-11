package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy E");
	private static SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
	private static SimpleDateFormat sql = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String convertDateToString(Date d)
	{
		return sdf.format(d);
	}
	
	public static Date convertStringToDate(String dateStr)
	{
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String convertDateToStringSQL(Date d)
	{
		try {
			return sql.format(d);
		} catch(Exception e) {
			return null;
		}
		
	}
	
	public static Date convertStringToDateSQL(String dateStr)
	{
		try {
			return sql.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String convertTimeToString(Date d)
	{
		return time.format(d);
	}
	
	public static Date convertStringToTime(String dateStr)
	{
		try {
			return time.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
