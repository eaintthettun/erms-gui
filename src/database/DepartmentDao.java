package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Department;

public class DepartmentDao {

	public static ArrayList<Department> getAllDepts(){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"select * from department");
			ResultSet rs = stmt.executeQuery();
			ArrayList<Department> depts = new ArrayList<Department>();
			while(rs.next()){
				Department dept = new Department();
				dept.setDept_id(rs.getString(1));
				dept.setDept_name(rs.getString(2));
				dept.setDept_name_mm(rs.getString(3));
				depts.add(dept);
			}
			return depts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<String> getAllDeptIDs(){
		try {
			PreparedStatement stmt=Connector.CON.prepareStatement(
				"select * from department");
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> depts = new ArrayList<String>();
			while(rs.next()){
				depts.add(rs.getString(1));
			}
			return depts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
