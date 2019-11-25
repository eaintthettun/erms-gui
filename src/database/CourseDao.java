package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Course;

public class CourseDao {
	
	public static boolean saveCourse(Course c){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"insert into course values(?,?,?,?,?)");
			stmt.setString(1, c.getAcademic_year());
			stmt.setString(2, c.getMajor_code());
			stmt.setString(3, c.getYear());
			stmt.setString(4, c.getSemester());
			stmt.setString(5, c.getSubjects());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean doesCourseExist(Course c){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"select * from course where academic_year=? and major_code=? and "
				+ "year=? and semester=?");
			stmt.setString(1, c.getAcademic_year());
			stmt.setString(2, c.getMajor_code());
			stmt.setString(3, c.getYear());
			stmt.setString(4, c.getSemester());
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getSubsByAcyMajYrSem(String acy,String maj, String yr, String sem){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"select subjects from course where academic_year=? and major_code=? and "
				+ "year=? and semester=?");
			stmt.setString(1, acy);
			stmt.setString(2, maj);
			stmt.setString(3, yr);
			stmt.setString(4, sem);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return rs.getString(1);
			else return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean deleteCourse(Course c){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"delete from course where academic_year=? and major_code=? and "
				+ "year=? and semester=?");
			stmt.setString(1, c.getAcademic_year());
			stmt.setString(2, c.getMajor_code());
			stmt.setString(3, c.getYear());
			stmt.setString(4, c.getSemester());
			
			stmt.executeUpdate();
			
			PreparedStatement stm=Connector.CON.prepareStatement(
					"delete from marks where academic_year=? and major_code=? and "
					+ "year=? and semester=?");
			stm.setString(1, c.getAcademic_year());
			stm.setString(2, c.getMajor_code());
			stm.setString(3, c.getYear());
			stm.setString(4, c.getSemester());
			
			stm.executeUpdate();
				
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<String> getAvailableAcademicYear(){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"select distinct academic_year from course order by academic_year");
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
