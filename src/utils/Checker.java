package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Connector;
import database.MajorDao;
import database.StudentDao;

public class Checker {
	
	public static boolean checkRequired(String txt){
		return txt==null || txt.isEmpty() ? true : false;
	}
	
	public static boolean checkTextLength(String txt) {
		return txt.length()>50;
	}
	
	public static boolean checkTextLength(String txt,int max) {
		return txt.length()>max;
	}

	public static boolean checkPasswordLength(String password,int min,int max) {
		return password.length()<min || password.length()>max;
	}
	
	public static boolean isNumeric(String st)
	{
		//this works for both float and integer
		String pattern = "\\d*.\\d*";
		return st.matches(pattern);
	}
	
	public static boolean checkAdminData()
	{
		String sql = "select * from `admin`";
		
		
		try {
			Statement stm = Connector.CON.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			if(rs.next()) return true;
			else return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean checkCurrentAdminExists()
	{
		String sql = "select * from `current_admin`";
		
		
		try {
			Statement stm = Connector.CON.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			if(rs.next()) return true;
			else return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isSubjectCode(String code){
		return code.matches("^[a-zA-Z]{1,6}-\\d{5}$");
	}
	
	public static boolean isID(String id){
		return id.matches("\\d{2}/\\d{5}");
	}
	
	public static boolean isMajorCompatible(String std_id,String major_code){
		String dept_id_byStd = MajorDao.getDeptIdByMaj(StudentDao.getStudentByID(std_id).getMajor_code());
		String dept_id_byMaj = MajorDao.getDeptIdByMaj(major_code);
		return dept_id_byStd.equals(dept_id_byMaj);
	}
	
	public static boolean isAllEmpty(ArrayList<String> str){
		for(String st:str)
			if(!(st == null || st.isEmpty())) return false;
		return true;
	}
	
	public static boolean isExcellent(String sub_code, int m){
		
		if(m<=100){
			if(sub_code.startsWith("M-")||sub_code.startsWith("E-")||sub_code.startsWith("HSS-")){
				if(m>=75) return true;
				else return false;
			}else{
				if(m>=85) return true;
				else return false;
			}
		}else{
			if(m>=850) return true; else return false;
		}
	}
	
	public static boolean isExcellent(String sub_code, int m, String year){
		
		if(Integer.parseInt(year)>6) return false;
		
		if(m<=100){
			if(sub_code.startsWith("M-")||sub_code.startsWith("E-")||sub_code.startsWith("HSS-")){
				if(m>=75) return true;
				else return false;
			}else{
				if(m>=85) return true;
				else return false;
			}
		}else{
			if(m>=850) return true; else return false;
		}
	}

}
