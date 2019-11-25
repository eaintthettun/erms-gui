package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Marks;

public class MarksDao {
	
	public static boolean saveMarks(Marks mks){
		try {
			
			PreparedStatement stm=Connector.CON.prepareStatement(
					"delete from marks where academic_year=? and std_id=? and semester=?");
				stm.setString(1, mks.getAcademic_year());
				stm.setString(2, mks.getStd_id());
				stm.setString(3, mks.getSemester());
				
				stm.executeUpdate();
				
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"insert into marks values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, mks.getAcademic_year());
			stmt.setString(2, mks.getStd_id());
			stmt.setString(3, mks.getRollno());
			stmt.setString(4, mks.getMajor_code());
			stmt.setString(5, mks.getYear());
			stmt.setString(6, mks.getSemester());
			stmt.setString(7, mks.getSubjects());
			stmt.setString(8, mks.getMarks());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteMarksByIDonAcy(String academic_year,String std_id,String semester){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"delete from marks where academic_year=? and std_id=? and semester=?");
			stmt.setString(1, academic_year);
			stmt.setString(2, std_id);
			stmt.setString(3, semester);
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<Marks> getMarksBy(String acy,String maj,String yr,String sem){
		try{
			String sql = "select * from marks where academic_year=? and major_code=? and"
					+ " year=? and semester=? order by cast(substr(rollno,locate('-',rollno)+1) as unsigned)";
			PreparedStatement stmt = Connector.CON.prepareStatement(sql);
			stmt.setString(1, acy);
			stmt.setString(2, maj);
			stmt.setString(3, yr);
			stmt.setString(4, sem);
			
			ArrayList<Marks> mks = new ArrayList<Marks>();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Marks m = new Marks();
				m.setAcademic_year(rs.getString(1));
				m.setStd_id(rs.getString(2));
				m.setRollno(rs.getString(3));
				m.setMajor_code(rs.getString(4));
				m.setYear(rs.getInt(5)+"");
				m.setSemester(rs.getInt(6)+"");
				m.setSubjects(rs.getString(7));
				m.setMarks(rs.getString(8));
				
				mks.add(m);
			}
			
			return mks;
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Marks getMarksByRollNo(String acy,String maj,String yr,String sem,String roll){
		try{
			String sql = "select * from marks where academic_year=? and major_code=? and"
					+ " year=? and semester=? and rollno=?";
			PreparedStatement stmt = Connector.CON.prepareStatement(sql);
			stmt.setString(1, acy);
			stmt.setString(2, maj);
			stmt.setString(3, yr);
			stmt.setString(4, sem);
			stmt.setString(5, roll);
			
			Marks m = new Marks();
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				m.setAcademic_year(rs.getString(1));
				m.setStd_id(rs.getString(2));
				m.setRollno(rs.getString(3));
				m.setMajor_code(rs.getString(4));
				m.setYear(rs.getInt(5)+"");
				m.setSemester(rs.getInt(6)+"");
				m.setSubjects(rs.getString(7));
				m.setMarks(rs.getString(8));
				return m;
			}else return null;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Marks> getAcademicRecordsByID(String std_id){
		try{
			String sql = "select * from marks where std_id=? order by academic_year,semester";
			PreparedStatement stmt = Connector.CON.prepareStatement(sql);
			stmt.setString(1, std_id);
			
			ArrayList<Marks> mks = new ArrayList<Marks>();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Marks m = new Marks();
				m.setAcademic_year(rs.getString(1));
				m.setStd_id(rs.getString(2));
				m.setRollno(rs.getString(3));
				m.setMajor_code(rs.getString(4));
				m.setYear(rs.getInt(5)+"");
				m.setSemester(rs.getInt(6)+"");
				m.setSubjects(rs.getString(7));
				m.setMarks(rs.getString(8));
				
				mks.add(m);
			}
			
			return mks;
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

}
