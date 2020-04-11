package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.MyConstants;
import model.Mother;

public class MotherDao {
	public static boolean saveMother(Mother m){

		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"insert into mother values(null,?,?,?,?,?,?,?)");
			stmt.setString(1, m.getMother_name());
			stmt.setString(2, m.getMother_name_mm());
			stmt.setString(3, m.getMother_ethnic());
			stmt.setString(4, m.getMother_religion());
			stmt.setString(5, m.getMother_birth_place());
			stmt.setString(6, m.getMother_nrc());
			stmt.setString(7, m.getMother_address());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			MyConstants.ERRORS+=(e.getMessage()+"\n");
			return false;
		}	
	}
	
	public static ArrayList<Mother> getMotherByName(String mother_name){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select * from mother where mother_name like ?");
			stmt.setString(1, mother_name);
			ArrayList<Mother> mothers = new ArrayList<Mother>();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				Mother m=new Mother();
				m.setMother_id(rs.getInt(1));
				m.setMother_name(rs.getString(2));
				m.setMother_name_mm(rs.getString(3));
				m.setMother_ethnic(rs.getString(4));
				m.setMother_religion(rs.getString(5));
				m.setMother_birth_place(rs.getString(6));
				m.setMother_nrc(rs.getString(7));
				m.setMother_address(rs.getString(8));
				mothers.add(m);
			}
			return mothers;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Mother getMotherByNrc(String mother_nrc){
		try{
			PreparedStatement stmt=Connector.CON.prepareStatement(
					"select * from mother where mother_nrc=? and mother_nrc!=''");
			stmt.setString(1, mother_nrc);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				Mother m=new Mother();
				m.setMother_id(rs.getInt(1));
				m.setMother_name(rs.getString(2));
				m.setMother_name_mm(rs.getString(3));
				m.setMother_ethnic(rs.getString(4));
				m.setMother_religion(rs.getString(5));
				m.setMother_birth_place(rs.getString(6));
				m.setMother_nrc(rs.getString(7));
				m.setMother_address(rs.getString(8));
				return m;
			}else return new Mother();
		}catch(SQLException e){
			e.printStackTrace();
			return new Mother();
		}
	}
	
	public static boolean updateMother(Mother mother){
		try {

			PreparedStatement stmt=Connector.CON.prepareStatement(
					"update mother set mother_name=?,mother_name_mm=?,mother_ethnic=?,mother_religion=?,mother_birth_place=?,mother_address=? where mother_nrc=? and mother_nrc!=''");
			stmt.setString(1, mother.getMother_name());
			stmt.setString(2, mother.getMother_name_mm());
			stmt.setString(3, mother.getMother_ethnic());
			stmt.setString(4, mother.getMother_religion());
			stmt.setString(5, mother.getMother_birth_place());
			stmt.setString(6, mother.getMother_address());
			stmt.setString(7, mother.getMother_nrc());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteMotherByNrc(String mother_nrc){
		try {

			PreparedStatement stmt=Connector.CON.prepareStatement(
					"delete from mother where mother_nrc=? and mother_nrc!=''");
			stmt.setString(1,mother_nrc);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}

}
