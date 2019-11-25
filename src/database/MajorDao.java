package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MajorDao {
	
	public static HashMap<String,String> getMajorCodeAndName(){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select * from major");	
			
			HashMap<String,String> major = new HashMap<String, String>();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				major.put(rs.getString(1), rs.getString(2));
			}
			return major;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<String> getMajorCode(){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select * from major");	
			
			ArrayList<String> codes = new ArrayList<String>();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				codes.add(rs.getString(1));
			}
			return codes;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<String> getPrimaryMajorCode(){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select * from major where major_type=?");
			stmt.setString(1, "primary");
			
			ArrayList<String> codes = new ArrayList<String>();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				codes.add(rs.getString(1));
			}
			return codes;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<String> getMajorName(){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select * from major");	
			
			ArrayList<String> names = new ArrayList<String>();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				names.add(rs.getString(2));
			}
			return names;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<String> getPrimaryMajorName(){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select * from major where major_type=?");
			stmt.setString(1, "primary");
			
			ArrayList<String> names = new ArrayList<String>();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				names.add(rs.getString(2));
			}
			return names;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getMajorType(String major_code){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select major_type from major where major_code=?");
			stmt.setString(1, major_code);
			
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) return rs.getString(1);
			else return null;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getDeptIdByMaj(String major_code){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select dept_id from major where major_code=?");
			stmt.setString(1, major_code);
			
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) return rs.getString(1);
			else return "";
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

}
