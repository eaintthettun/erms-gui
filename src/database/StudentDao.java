package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.Converter;
import utils.MyConstants;
import database.Connector;
import model.StudentPhotoModel;
import model.Student;

public class StudentDao {

	public static boolean saveStudent(Student std) {

		try {

			String sql = "insert into student values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstm = Connector.CON.prepareStatement(sql);

			pstm.setString(1, std.getStd_id());
			pstm.setString(2, std.getStd_name());
			pstm.setString(3, std.getStd_name_mm());
			pstm.setString(4, std.getStd_gender());
			pstm.setString(5, std.getStd_ethnic());
			pstm.setString(6, std.getStd_religion());
			pstm.setString(7, std.getStd_nrc());
			pstm.setString(8,
					Converter.convertDateToStringSQL(std.getStd_dob()));
			pstm.setString(9, std.getStd_mat_id());
			pstm.setString(10, std.getStd_mat_year());
			pstm.setString(11, std.getStd_mat_dept());
			pstm.setString(12, std.getMajor_code());
			pstm.setString(13, std.getFather_nrc());
			pstm.setString(14, std.getMother_nrc());
			pstm.setString(15, std.getStd_birth_place());
			pstm.setString(16, std.getStd_addr_perm());
			pstm.setString(17, std.getStd_addr_curr());
			pstm.setString(18, std.getStd_phone());
			pstm.setString(19, std.getStd_email());

			return pstm.executeUpdate() != 0;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			MyConstants.ERRORS+=(e.getMessage()+"\n");
			return false;
		}
	}

	public static boolean saveStudentPhoto(StudentPhotoModel photo) {

		String sql = "insert into student_photo values(?,?,?,?)";

		try {
			PreparedStatement pstm = Connector.CON.prepareStatement(sql);
			pstm.setString(1, photo.getStd_id());
			pstm.setString(2, photo.getStd_name());
			pstm.setInt(3, photo.getPhoto_size());
			pstm.setBlob(4, photo.getPhoto_data());
			pstm.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public static Student getStudentByID(String id) {
		String sql = "select * from student where std_id=?";
		try {
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			stm.setString(1, id);
			ResultSet rs = stm.executeQuery();
			Student stfm = new Student();
			if (rs.next()) {

				stfm.setStd_id(rs.getString(1));
				stfm.setStd_name(rs.getString(2));
				stfm.setStd_name_mm(rs.getString(3));
				stfm.setStd_gender(rs.getString(4));
				stfm.setStd_ethnic(rs.getString(5));
				stfm.setStd_religion(rs.getString(6));
				stfm.setStd_nrc(rs.getString(7));
				stfm.setStd_dob(rs.getDate(8));
				stfm.setStd_mat_id(rs.getString(9));
				stfm.setStd_mat_year(rs.getString(10));
				stfm.setStd_mat_dept(rs.getString(11));
				stfm.setMajor_code(rs.getString(12));
				stfm.setFather_nrc(rs.getString(13));
				stfm.setMother_nrc(rs.getString(14));
				stfm.setStd_birth_place(rs.getString(15));
				stfm.setStd_addr_perm(rs.getString(16));
				stfm.setStd_addr_curr(rs.getString(17));
				stfm.setStd_phone(rs.getString(18));
				stfm.setStd_email(rs.getString(19));

				return stfm;
			} else
				return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static ArrayList<Student> getStudentByName(String name) {
		String sql = "select * from student where std_name=?";
		try {
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			stm.setString(1, name);
			ResultSet rs = stm.executeQuery();

			ArrayList<Student> list = new ArrayList<Student>();
			while (rs.next()) {
				Student stfm = new Student();
				stfm.setStd_id(rs.getString(1));
				stfm.setStd_name(rs.getString(2));
				stfm.setStd_name_mm(rs.getString(3));
				stfm.setStd_gender(rs.getString(4));
				stfm.setStd_ethnic(rs.getString(5));
				stfm.setStd_religion(rs.getString(6));
				stfm.setStd_nrc(rs.getString(7));
				stfm.setStd_dob(rs.getDate(8));
				stfm.setStd_mat_id(rs.getString(9));
				stfm.setStd_mat_year(rs.getString(10));
				stfm.setStd_mat_dept(rs.getString(11));
				stfm.setMajor_code(rs.getString(12));
				stfm.setFather_nrc(rs.getString(13));
				stfm.setMother_nrc(rs.getString(14));
				stfm.setStd_birth_place(rs.getString(15));
				stfm.setStd_addr_perm(rs.getString(16));
				stfm.setStd_addr_curr(rs.getString(17));
				stfm.setStd_phone(rs.getString(18));
				stfm.setStd_email(rs.getString(19));

				list.add(stfm);
			}

			return list == null || list.size() == 0 ? null : list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static StudentPhotoModel getStudentPhotoByID(String id) {
		String sql = "select * from student_photo where std_id=?";
		try {
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			stm.setString(1, id);
			ResultSet rs = stm.executeQuery();
			StudentPhotoModel pm = new StudentPhotoModel();
			if (rs.next()) {
				pm.setStd_id(rs.getString(1));
				pm.setStd_name(rs.getString(2));
				pm.setPhoto_size(rs.getInt(3));
				pm.setPhoto_byte(rs.getBytes(4));
			}

			return pm;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Student> getAllStudents() {

		try {

			String sql = "select * from student";
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			ArrayList<Student> list = new ArrayList<Student>();
			while (rs.next()) {
				Student stfm = new Student();

				stfm.setStd_id(rs.getString(1));
				stfm.setStd_name(rs.getString(2));
				stfm.setStd_name_mm(rs.getString(3));
				stfm.setStd_gender(rs.getString(4));
				stfm.setStd_ethnic(rs.getString(5));
				stfm.setStd_religion(rs.getString(6));
				stfm.setStd_nrc(rs.getString(7));
				stfm.setStd_dob(rs.getDate(8));
				stfm.setStd_mat_id(rs.getString(9));
				stfm.setStd_mat_year(rs.getString(10));
				stfm.setStd_mat_dept(rs.getString(11));
				stfm.setMajor_code(rs.getString(12));
				stfm.setFather_nrc(rs.getString(13));
				stfm.setMother_nrc(rs.getString(14));
				stfm.setStd_birth_place(rs.getString(15));
				stfm.setStd_addr_perm(rs.getString(16));
				stfm.setStd_addr_curr(rs.getString(17));
				stfm.setStd_phone(rs.getString(18));
				stfm.setStd_email(rs.getString(19));

				list.add(stfm);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static boolean deleteStudentByID(String id) {

		try {

			String sql1 = "delete from student where std_id=?";
			String sql2 = "delete from student_photo where std_id=?";
			String sql3 = "delete from attend where std_id=?";
			String sql4 = "delete from marks where std_id=?";
			String sql5 = "delete from father where father_nrc=?";
			String sql6 = "delete from mother where mother_nrc=?";
			PreparedStatement stm1 = Connector.CON.prepareStatement(sql1);
			PreparedStatement stm2 = Connector.CON.prepareStatement(sql2);
			PreparedStatement stm3 = Connector.CON.prepareStatement(sql3);
			PreparedStatement stm4 = Connector.CON.prepareStatement(sql4);
			PreparedStatement stm5 = Connector.CON.prepareStatement(sql5);
			PreparedStatement stm6 = Connector.CON.prepareStatement(sql6);
			stm1.setString(1, id);
			stm2.setString(1, id);
			stm3.setString(1, id);
			stm4.setString(1, id);
			stm5.setString(1, getStudentByID(id).getFather_nrc());
			stm6.setString(1, getStudentByID(id).getMother_nrc());
			
			stm1.executeUpdate();
			stm2.executeUpdate();
			stm3.executeUpdate();
			stm4.executeUpdate();
			stm5.executeUpdate();
			stm6.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean updateStudent(Student std) {

		try {

			String sql = "update student"
					+ " set std_name=?,std_name_mm=?,std_gender=?,"
					+ "std_ethnic=?,std_religion=?,std_nrc=?,"
					+ "std_dob=?,std_mat_id=?,std_mat_year=?,"
					+ "std_mat_dept=?,major_code=?,father_nrc=?,"
					+ "mother_nrc=?,std_birth_place=?,std_addr_perm=?,"
					+ "std_addr_curr=?,std_phone=?,std_email=?"
					+ " where std_id=?";

			PreparedStatement pstm = Connector.CON.prepareStatement(sql);

			pstm.setString(1, std.getStd_name());
			pstm.setString(2, std.getStd_name_mm());
			pstm.setString(3, std.getStd_gender());
			pstm.setString(4, std.getStd_ethnic());
			pstm.setString(5, std.getStd_religion());
			pstm.setString(6, std.getStd_nrc());
			pstm.setString(7,
					Converter.convertDateToStringSQL(std.getStd_dob()));
			pstm.setString(8, std.getStd_mat_id());
			pstm.setString(9, std.getStd_mat_year());
			pstm.setString(10, std.getStd_mat_dept());
			pstm.setString(11, std.getMajor_code());
			pstm.setString(12, std.getFather_nrc());
			pstm.setString(13, std.getMother_nrc());
			pstm.setString(14, std.getStd_birth_place());
			pstm.setString(15, std.getStd_addr_perm());
			pstm.setString(16, std.getStd_addr_curr());
			pstm.setString(17, std.getStd_phone());
			pstm.setString(18, std.getStd_email());
			pstm.setString(19, std.getStd_id());

			pstm.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean updateStudentPhoto(StudentPhotoModel photo) {

		String sql = "update student_photo set std_name=?,"
				+ " photo_size=?,photo_data=? where std_id=?";

		try {
			PreparedStatement pstm = Connector.CON.prepareStatement(sql);

			pstm.setString(1, photo.getStd_name());
			pstm.setInt(2, photo.getPhoto_size());
			pstm.setBlob(3, photo.getPhoto_data());
			pstm.setString(4, photo.getStd_id());

			pstm.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public static Integer getNoOfStudents() {
		String sql = "select count(*) from student";
		try {
			PreparedStatement pstm = Connector.CON.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery(sql);
			if (rs.next())
				return rs.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public static ArrayList<String> getAllStdIDs() {

		try {

			String sql = "select std_id from student";
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
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

}
