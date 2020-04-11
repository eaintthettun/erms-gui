package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.MyConstants;
import model.Father;

public class FatherDao {
	
	public static boolean saveFather(Father f){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"insert into father values(null,?,?,?,?,?,?,?)");
			stmt.setString(1, f.getFather_name());
			stmt.setString(2, f.getFather_name_mm());
			stmt.setString(3, f.getFather_ethnic());
			stmt.setString(4, f.getFather_religion());
			stmt.setString(5, f.getFather_birth_place());
			stmt.setString(6, f.getFather_nrc());
			stmt.setString(7, f.getFather_address());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			MyConstants.ERRORS+=(e.getMessage()+"\n");
			return false;
		}
	}
	
	public static ArrayList<Father> getFatherByName(String father_name){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select * from father where father_name like ?");
			stmt.setString(1, father_name);
			ArrayList<Father> fathers = new ArrayList<Father>();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				Father f=new Father();
				f.setFather_id(rs.getInt(1));
				f.setFather_name(rs.getString(2));
				f.setFather_name_mm(rs.getString(3));
				f.setFather_ethnic(rs.getString(4));
				f.setFather_religion(rs.getString(5));
				f.setFather_birth_place(rs.getString(6));
				f.setFather_nrc(rs.getString(7));
				f.setFather_address(rs.getString(8));
				fathers.add(f);
			}
			return fathers;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Father getFatherByNrc(String father_nrc){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select * from father where father_nrc=? and father_nrc!=''");
			stmt.setString(1, father_nrc);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				Father f=new Father();
				f.setFather_id(rs.getInt(1));
				f.setFather_name(rs.getString(2));
				f.setFather_name_mm(rs.getString(3));
				f.setFather_ethnic(rs.getString(4));
				f.setFather_religion(rs.getString(5));
				f.setFather_birth_place(rs.getString(6));
				f.setFather_nrc(rs.getString(7));
				f.setFather_address(rs.getString(8));
				return f;
			}else return new Father();
		}catch(SQLException e){
			e.printStackTrace();
			return new Father();
		}
	}
	
	public static boolean updateFather(Father father){
		try {

			PreparedStatement stmt=Connector.CON.prepareStatement(
					"update father set father_name=?,father_name_mm=?,father_ethnic=?,father_religion=?,father_birth_place=?,father_address=? where father_nrc=? and father_nrc!=''");
			stmt.setString(1, father.getFather_name());
			stmt.setString(2, father.getFather_name_mm());
			stmt.setString(3, father.getFather_ethnic());
			stmt.setString(4, father.getFather_religion());
			stmt.setString(5, father.getFather_birth_place());
			stmt.setString(6, father.getFather_address());
			stmt.setString(7, father.getFather_nrc());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteFatherByNrc(String father_nrc){
		try {

			PreparedStatement stmt=Connector.CON.prepareStatement(
					"delete from father where father_nrc=? and father_nrc!=''");
			stmt.setString(1,father_nrc);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
}

