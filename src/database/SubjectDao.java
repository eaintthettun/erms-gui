package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SubjectDao {

	public static boolean saveSubject(String sub_code, String sub_name,String dept_id){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"insert into subject(sub_code,sub_name,dept_id) values(?,?,?)");
			stmt.setString(1, sub_code);
			stmt.setString(2, sub_name);
			stmt.setString(3, dept_id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static HashMap<String, String> getSubjectCodeAndName(){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"select * from subject");
			ResultSet rs = stmt.executeQuery();
			HashMap<String, String> subCodeAndName = new HashMap<String, String>();
			while(rs.next()){
				subCodeAndName.put(rs.getString(1), rs.getString(2));
			}
			return subCodeAndName;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList<String[]> getAllSubjects(){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"select * from subject");
			ResultSet rs = stmt.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(rs.next()){
				String[] str = {rs.getString(1),rs.getString(2),rs.getString(3)};
				list.add(str);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
