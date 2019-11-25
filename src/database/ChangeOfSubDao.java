package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ChangeOfSub;

public class ChangeOfSubDao {
	
	public static boolean saveChangeOfSub(ChangeOfSub cos){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"insert into changeofsub values(?,?,?,?)");
			stmt.setString(1, cos.getSub_code());
			stmt.setString(2, cos.getStart_year());
			stmt.setString(3, cos.getEnd_year());
			stmt.setString(4, cos.getSub_name());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static ArrayList<ChangeOfSub> getAllChangeOfSub(){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"select * from changeofsub");
			ResultSet rs = stmt.executeQuery();
			ArrayList<ChangeOfSub> list = new ArrayList<ChangeOfSub>();
			while(rs.next()){
				ChangeOfSub cos = new ChangeOfSub();
				cos.setSub_code(rs.getString(1));
				cos.setStart_year(rs.getString(2));
				cos.setEnd_year(rs.getString(3));
				cos.setSub_name(rs.getString(4));
				list.add(cos);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean deleteChangeOfSub(ChangeOfSub cos){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"delete from changeofsub where sub_code=? and "
				+ "start_year=? and "
				+ "end_year=? and "
				+ "sub_name=?");
			stmt.setString(1, cos.getSub_code());
			stmt.setString(2, cos.getStart_year());
			stmt.setString(3, cos.getEnd_year());
			stmt.setString(4, cos.getSub_name());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean exists(String sub_code){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"select * from changeofsub where sub_code=?");
			stmt.setString(1, sub_code);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getSubjectName(String sub_code, String acy){
		if(acy == null || acy.length()!=9 || !exists(sub_code))
			return SubjectDao.getSubjectCodeAndName().get(sub_code);
		else{
			
			try {
				PreparedStatement stmt=Connector.CON.prepareStatement(
					"select sub_name from changeofsub where sub_code=? "
					+ "and start_year<=? "
					+ "and (end_year='Present' or end_year>=?)");
				stmt.setString(1, sub_code);
				stmt.setString(2, acy.substring(0,4));
				stmt.setString(3, acy.substring(5));
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) return rs.getString(1);
				else return SubjectDao.getSubjectCodeAndName().get(sub_code);
			} catch (SQLException e) {
				e.printStackTrace();
				return SubjectDao.getSubjectCodeAndName().get(sub_code);
			}
		}
	}

}
