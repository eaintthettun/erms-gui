package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import utils.Converter;

public class CurrentAdminDao {

	public static boolean addCurrentAdmin(String name)
	{
		String sql = "insert into `current_admin` values(?,?)";
		try {
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			stm.setString(1, name);
			stm.setString(2, Converter.convertTimeToString(new Date()));
			stm.executeUpdate();
			
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			
			return false;
		}
	}
	public static boolean removeCurrentAdmin()
	{
		String sql = "delete from `current_admin`";
		try {
			PreparedStatement stm = Connector.CON.prepareStatement(sql);

			stm.executeUpdate();
			
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			
			return false;
		}
	}
}
