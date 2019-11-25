package utils;

public class MyConstants {
	
	public static String ERRORS;
	
	public static String msgRequired(String txt){
		return txt+" is required";
	}
	
	public static String msgLength(String txt,int max){
		return txt+" must be less than "+max+" characters";
	}
	public static String msgLength(String txt,int min, int max){
		return txt+" must be between "+min+" and "+max+" characters";
	}
}
