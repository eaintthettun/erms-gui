package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	
	public static void prepareDatabase()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306";
			
			Connection con = DriverManager.getConnection(url,"root","rootpassword");
			Statement stm = con.createStatement();
			
			//Statement stm = Connector.DB_CON.createStatement();
			
			String sql = "create database if not exists erms";
			
			stm.execute(sql);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void prepareAdminTable()
	{
		try {
			
			Statement stm = Connector.CON.createStatement();
			
			String sql1 = "create table if not exists `admin`("
					+ "admin_name varchar(50) primary key,"
					+ "admin_password varchar(150)"
					+ ")";
			
			stm.executeUpdate(sql1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void prepareTables()
	{
		try {
			Statement stm = Connector.CON.createStatement();
			
			String sql2 = "create table if not exists student("
					+ "std_id varchar(8) primary key,"
					+ "std_name varchar(200),"
					+ "std_name_mm varchar(200),"
					+ "std_gender char(1),"
					+ "std_ethnic varchar(100),"
					+ "std_religion varchar(150),"
					+ "std_nrc varchar(50),"
					+ "std_dob date,"
					+ "std_mat_id varchar(50),"
					+ "std_mat_year varchar(4),"
					+ "std_mat_dept varchar(100),"
					+ "major_code varchar(10),"
					+ "father_nrc varchar(50),"
					+ "mother_nrc varchar(50),"
					+ "std_birth_place varchar(300),"
					+ "std_addr_perm varchar(300),"
					+ "std_addr_curr varchar(300),"
					+ "std_phone varchar(150),"
					+ "std_email varchar(100)"
					+ ")";
			
			String sql3 = "create table if not exists student_photo("
					+ "std_id varchar(8) unique,"
					+ "std_name varchar(200),"
					+ "photo_size int,"
					+ "photo_data longblob"
					+ ")";
			
			String sql4 = "create table if not exists father("
					+ "father_id int primary key auto_increment,"
					+ "father_name varchar(200),"
					+ "father_name_mm varchar(200),"
					+ "father_ethnic varchar(100),"
					+ "father_religion varchar(150),"
					+ "father_birth_place varchar(300),"
					+ "father_nrc varchar(50),"
					+ "father_address varchar(300)"
					+ ")";
			
			String sql5 = "create table if not exists mother("
					+ "mother_id int primary key auto_increment,"
					+ "mother_name varchar(200),"
					+ "mother_name_mm varchar(200),"
					+ "mother_ethnic varchar(100),"
					+ "mother_religion varchar(150),"
					+ "mother_birth_place varchar(300),"
					+ "mother_nrc varchar(50),"
					+ "mother_address varchar(300)"
					+ ")";
			
			String sql6 = "create table if not exists major("
					+ "major_code varchar(10) unique,"
					+ "major_name varchar(200),"
					+ "major_name_mm varchar(300),"
					+ "major_type varchar(10),"
					+ "dept_id varchar(10),"
					+ "major_order int primary key auto_increment"
					+ ")";
			
			String sql7 = "create table if not exists `current_admin`("
					+ "admin_name varchar(200),"
					+ "start_time varchar(11)"
					+ ")";
			
			String sql8 = "create table if not exists subject("
					+ "sub_code varchar(10) primary key,"
					+ "sub_name varchar(300),"
					+ "dept_id varchar(10),"
					+ "sub_pass int default 50,"
					+ "sub_distinction int default 70,"
					+ "sub_excellent int default 85,"
					+ "sub_max int default 100"
					+ ")";

			String sql9 = "create table if not exists department("
					+ "dept_id varchar(10) unique,"
					+ "dept_name varchar(300) unique,"
					+ "dept_name_mm varchar(350),"
					+ "dept_order int primary key auto_increment"
					+ ")";

			String sql10 = "create table if not exists course("
					+ "academic_year varchar(9),"
					+ "major_code varchar(10),"
					+ "year int,"
					+ "semester int,"
					+ "subjects varchar(200)"
					+ ")";

			String sql11 = "create table if not exists attend("
					+ "academic_year varchar(9),"
					+ "major_code varchar(10),"
					+ "year int,"
					+ "std_id varchar(8),"
					+ "rollno varchar(20)"
					+ ")";

			String sql12 = "create table if not exists marks("
					+ "academic_year varchar(9),"
					+ "std_id varchar(8),"
					+ "rollno varchar(20),"
					+ "major_code varchar(10),"
					+ "year int,"
					+ "semester int,"
					+ "subjects varchar(200),"
					+ "marks varchar(150)"
					+ ")";
			
			String sql13 = "create table if not exists changeofsub("
					+ "sub_code varchar(10),"
					+ "start_year varchar(4),"
					+ "end_year varchar(7),"
					+ "sub_name varchar(300)"
					+ ")";

			
			/*
			String sql8 = "create or replace view results as "
					+ "select enr_std_id as student_id,"
					+ "sum(enr_period) as total_period,"
					+ "count(distinct enr_eve_id) as total_events "
					+ "from enroll group by enr_std_id";
			*/
			stm.executeUpdate(sql2);
			stm.executeUpdate(sql3);
			stm.executeUpdate(sql4);
			stm.executeUpdate(sql5);
			stm.executeUpdate(sql6);
			stm.executeUpdate(sql7);
			stm.executeUpdate(sql8);
			stm.executeUpdate(sql9);
			stm.executeUpdate(sql10);
			stm.executeUpdate(sql11);
			stm.executeUpdate(sql12);
			stm.executeUpdate(sql13);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void preparePicture(){
		try {
			Statement stm = Connector.CON.createStatement();
			
			String sql1 = "create table if not exists picture("
					+ "pic_name varchar(50) primary key,"
					+ "pic_data longblob"
					+ ")";
			stm.executeUpdate(sql1);
			
			if(getPicture("bgPic")==null && new File("res"+File.separator+"bgPic.jpg").exists()){
				String sql2 = "insert into picture values(?,?)";
				PreparedStatement pstm = Connector.CON.prepareStatement(sql2);
				pstm.setString(1, "bgPic");
				pstm.setBlob(2, new FileInputStream(new File("res"+File.separator+"bgPic.jpg")));
				pstm.executeUpdate();
			}
			if(getPicture("ErmsLogo")==null && new File("res"+File.separator+"ErmsLogo.png").exists()){
				String sql2 = "insert into picture values(?,?)";
				PreparedStatement pstm = Connector.CON.prepareStatement(sql2);
				pstm.setString(1, "ErmsLogo");
				pstm.setBlob(2, new FileInputStream(new File("res"+File.separator+"ErmsLogo.png")));
				pstm.executeUpdate();
			}
			if(getPicture("ytuLogo")==null && new File("res"+File.separator+"ytuLogo.png").exists()){
				String sql2 = "insert into picture values(?,?)";
				PreparedStatement pstm = Connector.CON.prepareStatement(sql2);
				pstm.setString(1, "ytuLogo");
				pstm.setBlob(2, new FileInputStream(new File("res"+File.separator+"ytuLogo.png")));
				pstm.executeUpdate();
			}
			
			if(getPicture("excel")==null && new File("res"+File.separator+"excel.png").exists()){
				String sql2 = "insert into picture values(?,?)";
				PreparedStatement pstm = Connector.CON.prepareStatement(sql2);
				pstm.setString(1, "excel");
				pstm.setBlob(2, new FileInputStream(new File("res"+File.separator+"excel.png")));
				pstm.executeUpdate();
			}
			if(getPicture("word")==null && new File("res"+File.separator+"word.png").exists()){
				String sql2 = "insert into picture values(?,?)";
				PreparedStatement pstm = Connector.CON.prepareStatement(sql2);
				pstm.setString(1, "word");
				pstm.setBlob(2, new FileInputStream(new File("res"+File.separator+"word.png")));
				pstm.executeUpdate();
			}
			
		} catch (SQLException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static byte[] getPicture(String pic_name){
		String sql = "select * from picture where pic_name=?";
		try {
			PreparedStatement stm = Connector.CON.prepareStatement(sql);
			stm.setString(1, pic_name);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				return rs.getBytes(2);
			}else
				return null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void prepareMajorData(){
		String sql1 = "select * from major";
		String[] sql2 = {
				
			// 12 Primary Majors
			"insert into major values("
				+ "'C',"
				+ "'Civil Engineering',"
				+ "'á�¿á€™á€­á€³á‚•á€»á€• á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬',"
				+ "'primary',"
				+ "'CE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'Mech',"
				+ "'Mechanical Engineering',"
				+ "'á€…á€€á€¹á€™á‚ˆ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬',"
				+ "'primary',"
				+ "'ME',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'EP',"
				+ "'Electrical Power Engineering',"
				+ "'á€œá€½á€ºá€•á€¹á€…á€…á€¹á€…á€¼á€™á€¹á€¸á€¡á€¬á€¸ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬',"
				+ "'primary',"
				+ "'EP',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'EC',"
				+ "'Electronic Engineering',"
				+ "'á€¡á€®á€œá€€á€¹á€‘á€±á€›á€¬á€”á€…á€¹ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬',"
				+ "'primary',"
				+ "'EcE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'CEIT',"
				+ "'Computer Engineering and Information Technology',"
				+ "'á€€á€¼á€”á€¹á€•á€ºá€´á€�á€¬ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬ á‚�á€½á€„á€¹á€· á€žá€�á€„á€¹á€¸á€¡á€�á€ºá€€á€¹á€¡á€œá€€á€¹ á€”á€Šá€¹á€¸á€•á€Šá€¬',"
				+ "'primary',"
				+ "'IT',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'McE',"
				+ "'Mechatronic Engineering',"
				+ "'á€™á€€á� á€¬á€‘á€±á€›á€¬á€”á€…á€¹ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬',"
				+ "'primary',"
				+ "'McE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'ChE',"
				+ "'Chemical Engineering',"
				+ "'á€“á€¬á€�á€¯ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬',"
				+ "'primary',"
				+ "'ChE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'Tex',"
				+ "'Textile Engineering',"
				+ "'á€�á€ºá€Šá€¹á€™á€½á€ºá€„á€¹ á‚�á€½á€„á€¹á€· á€¡á€‘á€Šá€¹ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬',"
				+ "'primary',"
				+ "'TE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'Mn',"
				+ "'Mining Engineering',"
				+ "'á€žá€�á�±á€³á€�á€°á€¸á€±á€–á€¬á€¹á€±á€›á€¸ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬',"
				+ "'primary',"
				+ "'Min',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'PE',"
				+ "'Petroleum Engineering',"
				+ "'á€±á€›á€”á€¶ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬',"
				+ "'primary',"
				+ "'PE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'Met',"
				+ "'Metallurgical Engineering',"
				+ "'á€žá€�á�±á€³á€±á€—á€’ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬',"
				+ "'primary',"
				+ "'Met',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'Arch',"
				+ "'Architecture',"
				+ "'á€—á€­á€žá€¯á€€á€¬',"
				+ "'primary',"
				+ "'Arch',"
				+ "null"
				+ ")",
			
			// Higher Majors
			"insert into major values("
				+ "'CSE',"
				+ "'Structural Engineering',"
				+ "'Structural Engineering',"
				+ "'higher',"
				+ "'CE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'CWRE',"
				+ "'Water Resources Engineering',"
				+ "'Water Resources Engineering',"
				+ "'higher',"
				+ "'CE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'CGE',"
				+ "'Geotechnical Engineering',"
				+ "'Geotechnical Engineering',"
				+ "'higher',"
				+ "'CE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'CTE',"
				+ "'Transportation Engineering',"
				+ "'Transportation Engineering',"
				+ "'higher',"
				+ "'CE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'CM',"
				+ "'Construction Management',"
				+ "'Construction Management',"
				+ "'higher',"
				+ "'CE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'Env.E',"
				+ "'Environmental Engineering',"
				+ "'Environmental Engineering',"
				+ "'higher',"
				+ "'CE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'Env.M',"
				+ "'Environmental Planning and Management',"
				+ "'Environmental Planning and Management',"
				+ "'higher',"
				+ "'CE',"
				+ "null"
				+ ")",
				//
			"insert into major values("
				+ "'IE',"
				+ "'Industrial Engineering',"
				+ "'Industrial Engineering',"
				+ "'higher',"
				+ "'ME',"
				+ "null"
				+ ")",
			
			"insert into major values("
				+ "'RE',"
				+ "'Renewable Energy Engineering',"
				+ "'Renewable Energy Engineering',"
				+ "'higher',"
				+ "'ME',"
				+ "null"
				+ ")",
				
			"insert into major values("
				+ "'RES',"
				+ "'Renewable Energy System',"
				+ "'Renewable Energy System',"
				+ "'higher',"
				+ "'EP',"
				+ "null"
				+ ")",
				
			"insert into major values("
				+ "'BioMET',"
				+ "'Biomedical Engineering Technology',"
				+ "'Biomedical Engineering Technology',"
				+ "'higher',"
				+ "'McE',"
				+ "null"
				+ ")",
				
			"insert into major values("
				+ "'Arc.Met',"
				+ "'Experimental Archaeometallurgy',"
				+ "'Experimental Archaeometallurgy',"
				+ "'higher',"
				+ "'Met',"
				+ "null"
				+ ")",
				
			"insert into major values("
				+ "'Ext.Met',"
				+ "'Extractive Metallurgical Engineering',"
				+ "'Extractive Metallurgical Engineering',"
				+ "'higher',"
				+ "'Met',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'Adapt.Met',"
				+ "'Addaptive Metallurgical Engineering',"
				+ "'Addaptive Metallurgical Engineering',"
				+ "'higher',"
				+ "'Met',"
				+ "null"
				+ ")",
				
			"insert into major values("
				+ "'E.Geol',"
				+ "'Engineering Geology',"
				+ "'Engineering Geology',"
				+ "'higher',"
				+ "'Geol',"
				+ "null"
				+ ")",
				
			"insert into major values("
				+ "'D.F.T',"
				+ "'Diploma in Food Technology',"
				+ "'Diploma in Food Technology',"
				+ "'other',"
				+ "'ChE',"
				+ "null"
				+ ")",
			"insert into major values("
				+ "'M.F.T',"
				+ "'Master of Food Technology',"
				+ "'Master of Food Technology',"
				+ "'other',"
				+ "'ChE',"
				+ "null"
				+ ")",
				
			"insert into major values("
				+ "'EIA/EMS',"
				+ "'Environmental Impact Assement and Environmental Management System',"
				+ "'Diploma in Environmental Impact Assement and Environmental Management System',"
				+ "'other',"
				+ "'Min',"
				+ "null"
				+ ")",
				
			"insert into major values("
				+ "'EAM',"
				+ "'Environmental Assement and Management',"
				+ "'Master of Environmental Assement and Management',"
				+ "'other',"
				+ "'Min',"
				+ "null"
				+ ")",
				
			"insert into major values("
				+ "'DESP',"
				+ "'Diploma in English for Specific Purpose',"
				+ "'Diploma in English for Specific Purpose',"
				+ "'other',"
				+ "'E',"
				+ "null"
				+ ")",
						
			"insert into major values("
				+ "'MESP',"
				+ "'Master of English for Specific Purpose',"
				+ "'Master of English for Specific Purpose',"
				+ "'other',"
				+ "'E',"
				+ "null"
				+ ")"
			};
		try{
			Statement stm = Connector.CON.createStatement();
			ResultSet rs = stm.executeQuery(sql1);
			if(!rs.next()){
				for(int i=0;i<sql2.length;i++){
					stm.executeUpdate(sql2[i]);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void prepareDeptData(){
		String sql1 = "select * from department";
		String sql2[] = {
				// 12 Major departments
				"insert into department values("
					+ "'CE',"
					+ "'Department of Civil Engineering',"
					+ "'á�¿á€™á€­á€³á‚•á€»á€• á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'ME',"
					+ "'Department of Mechanical Engineering',"
					+ "'á€…á€€á€¹á€™á‚ˆ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'EP',"
					+ "'Department of Electrical Power Engineering',"
					+ "'á€œá€½á€ºá€•á€¹á€…á€…á€¹á€…á€¼á€™á€¹á€¸á€¡á€¬á€¸ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'EcE',"
					+ "'Department of Electronic Engineering',"
					+ "'á€¡á€®á€œá€€á€¹á€‘á€±á€›á€¬á€”á€…á€¹ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'IT',"
					+ "'Department of Computer Engineering and Information Technology',"
					+ "'á€€á€¼á€”á€¹á€•á€ºá€´á€�á€¬ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬ á‚�á€½á€„á€¹á€· á€žá€�á€„á€¹á€¸á€¡á€�á€ºá€€á€¹á€¡á€œá€€á€¹ á€”á€Šá€¹á€¸á€•á€Šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'McE',"
					+ "'Department of Mechatronic Engineering',"
					+ "'á€™á€€á� á€¬á€‘á€±á€›á€¬á€”á€…á€¹ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'ChE',"
					+ "'Department of Chemical Engineering',"
					+ "'á€“á€¬á€�á€¯ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'TE',"
					+ "'Department of Textile Engineering',"
					+ "'á€�á€ºá€Šá€¹á€™á€½á€ºá€„á€¹ á‚�á€½á€„á€¹á€· á€¡á€‘á€Šá€¹ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'Min',"
					+ "'Department of Mining Engineering',"
					+ "'á€žá€�á�±á€³á€�á€°á€¸á€±á€–á€¬á€¹á€±á€›á€¸ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'PE',"
					+ "'Department of Petroleum Engineering',"
					+ "'á€±á€›á€”á€¶ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'Met',"
					+ "'Department of Metallurgical Engineering',"
					+ "'á€žá€�á�±á€³á€±á€—á€’ á€¡á€„á€¹á€‚á€ºá€„á€¹á€”á€®á€šá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'Arch',"
					+ "'Department of Architecture',"
					+ "'á€—á€­á€žá€¯á€€á€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
					
				// Other Minor Departments
				"insert into department values("
					+ "'M',"
					+ "'Department of Myanmar',"
					+ "'á€»á€™á€”á€¹á€™á€¬á€…á€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'E',"
					+ "'Department of English',"
					+ "'á€¡á€‚á�¤á€œá€­á€•á€¹á€…á€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'EM',"
					+ "'Department of Engineering Mathematics',"
					+ "'á€žá€�á�¤á€ºá€¬á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'EPh',"
					+ "'Department of Engineering Physics',"
					+ "'á€›á€°á€•á€±á€—á€’á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'ECh',"
					+ "'Department of Engineering Chemistry',"
					+ "'á€“á€¬á€�á€¯á€±á€—á€’á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'HSS',"
					+ "'Department of Humanities and Social Sciences',"
					+ "'á€œá€°á€™á‚ˆá€±á€›á€¸á€žá€­á€•á�¸á€¶á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'Geol',"
					+ "'Department of Geology',"
					+ "'á€˜á€°á€™á€­á€±á€—á€’á€Œá€¬á€”',"
					+ "null"
					+ ")",
				"insert into department values("
					+ "'WS',"
					+ "'Department of Workshop',"
					+ "'á€¡á€œá€¯á€•á€¹á€›á€¶á€¯á€Œá€¬á€”',"
					+ "null"
					+ ")"
		};
		try{
			Statement stm = Connector.CON.createStatement();
			ResultSet rs = stm.executeQuery(sql1);
			if(!rs.next()){
				for(int i=0;i<sql2.length;i++)
					stm.executeUpdate(sql2[i]);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
