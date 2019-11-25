package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Course;
import database.Connector;

public class AttendDao {
	
	public static boolean saveAttend(Course c){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"insert into attend values(?,?,?,?,?)");
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

	public static ArrayList<Course> getAttendByAcMajYr(String ac,String maj,String yr){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"select * from attend where academic_year=? and major_code=? and year=? order by cast(substr(rollno,locate('-',rollno)+1) as unsigned)");
			stmt.setString(1, ac);
			stmt.setString(2, maj);
			stmt.setString(3, yr);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Course> list = new ArrayList<Course>();
			while(rs.next()){
				Course c = new Course();
				c.setAcademic_year(ac);
				c.setMajor_code(maj);
				c.setYear(yr);
				c.setSemester(rs.getString(4)); //std_id
				c.setSubjects(rs.getString(5)); //rollno
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<String> getAllStdIDsByAc(String ac) {

		try {

			String sql = "select std_id from attend where academic_year=? order by cast(substr(rollno,locate('-',rollno)+1) as unsigned)";
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			stm.setString(1, ac);
			
			ResultSet rs = stm.executeQuery();

			ArrayList<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<String> getAllStdRollNosBy(String ac,String maj,String yr) {

		try {

			String sql = "select rollno from attend where academic_year=? and major_code=? and year=? order by cast(substr(rollno,locate('-',rollno)+1) as unsigned)";
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			stm.setString(1, ac);
			stm.setString(2, maj);
			stm.setString(3, yr);
			
			ResultSet rs = stm.executeQuery();

			ArrayList<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean deleteAttend(String ac, String std_id){
		try {

			PreparedStatement stmt=Connector.CON.prepareStatement(
					"delete from attend where academic_year=? and std_id=?");
			stmt.setString(1, ac);
			stmt.setString(2, std_id);
			
			stmt.executeUpdate();
			
			PreparedStatement stm=Connector.CON.prepareStatement(
					"delete from marks where academic_year=? and std_id=?");
			stm.setString(1, ac);
			stm.setString(2, std_id);
			
			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getAttendStdID(String acy,String maj,String yr,String roll){
		try {

			String sql = "select * from attend where academic_year=? "
					+ "and major_code=? and year=? and rollno=?";
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			stm.setString(1, acy);
			stm.setString(2, maj);
			stm.setString(3, yr);
			stm.setString(4, roll);
			
			ResultSet rs = stm.executeQuery();
			if(rs.next())
				return rs.getString(4);
			else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
