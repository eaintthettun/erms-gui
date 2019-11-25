package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
	
	public static boolean checkLoginDB(String email,String password){
		
		String sql = "select * from `admin` where admin_name=? and admin_password=md5(?)";
		try {
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			stm.setString(1, email);
			stm.setString(2, password);
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) return true;
			else return false;
		} catch (SQLException e) {

			e.printStackTrace();
			
			return false;
		}
		
	}
	
	public static boolean createAdmin(String email,String password)
	{
		String sql = "insert into `admin` values(?,md5(?))";
		try {
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			stm.setString(1, email);
			stm.setString(2, password);
			stm.executeUpdate();
			
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			
			return false;
		}
	}

}
