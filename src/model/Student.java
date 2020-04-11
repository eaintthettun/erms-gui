package model;

import java.util.Comparator;
import java.util.Date;

import utils.Converter;

public class Student {

	private String std_id;
	private String std_name;
	private String std_name_mm;
	private String std_gender;
	private String std_ethnic;
	private String std_religion;
	private String std_nrc;
	private Date std_dob;
	private String std_mat_id;
	private String std_mat_year;
	private String std_mat_dept;
	private String major_code;
	private String father_nrc;
	private String mother_nrc;
	private String std_birth_place;
	private String std_addr_perm;
	private String std_addr_curr;
	private String std_phone;
	private String std_email;
	
	public Student() {
		this.std_id = "";
		this.std_name = "";
		this.std_name_mm = "";
		this.std_gender = "";
		this.std_ethnic = "";
		this.std_religion = "";
		this.std_nrc = "";
		this.std_dob = null;
		this.std_mat_id = "";
		this.std_mat_year = "";
		this.std_mat_dept = "";
		this.major_code = "";
		this.father_nrc = "";
		this.mother_nrc = "";
		this.std_birth_place = "";
		this.std_addr_perm = "";
		this.std_addr_curr = "";
		this.std_phone = "";
		this.std_email = "";
	}
	
	public static Comparator<Student> compStdID = new Comparator<Student>() {
		
		@Override
		public int compare(Student s1, Student s2) {
			String id1 = s1.getStd_id();
			String id2 = s2.getStd_id();
			return id1.compareTo(id2);
		}
	};
	
	public static Comparator<Student> compStdName = new Comparator<Student>() {
		
		@Override
		public int compare(Student s1, Student s2) {
			String name1 = s1.getStd_name().toUpperCase();
			String name2 = s2.getStd_name().toUpperCase();
			return name1.compareTo(name2);
		}
	};
	
	public static Comparator<Student> compStdMajor = new Comparator<Student>() {
		
		@Override
		public int compare(Student s1, Student s2) {
			String maj1 = s1.getMajor_code().toUpperCase();
			String maj2 = s2.getMajor_code().toUpperCase();
			return maj1.compareTo(maj2);
		}
	};

	public String getStd_id() {
		return std_id;
	}

	public String getStd_name() {
		return std_name;
	}

	public String getStd_name_mm() {
		return std_name_mm;
	}

	public String getStd_gender() {
		return std_gender;
	}

	public String getStd_ethnic() {
		return std_ethnic;
	}

	public String getStd_religion() {
		return std_religion;
	}

	public String getStd_nrc() {
		return std_nrc;
	}

	public Date getStd_dob() {
		return std_dob;
	}

	public String getStd_mat_id() {
		return std_mat_id;
	}

	public String getStd_mat_year() {
		return std_mat_year;
	}

	public String getStd_mat_dept() {
		return std_mat_dept;
	}

	public String getMajor_code() {
		return major_code;
	}

	public String getFather_nrc() {
		return father_nrc;
	}

	public String getMother_nrc() {
		return mother_nrc;
	}

	public String getStd_birth_place() {
		return std_birth_place;
	}

	public String getStd_addr_perm() {
		return std_addr_perm;
	}

	public String getStd_addr_curr() {
		return std_addr_curr;
	}

	public String getStd_phone() {
		return std_phone;
	}

	public String getStd_email() {
		return std_email;
	}

	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}

	public void setStd_name(String std_name) {
		this.std_name = std_name;
	}

	public void setStd_name_mm(String std_name_mm) {
		this.std_name_mm = std_name_mm;
	}

	public void setStd_gender(String std_gender) {
		this.std_gender = std_gender;
	}

	public void setStd_ethnic(String std_ethic) {
		this.std_ethnic = std_ethic;
	}

	public void setStd_religion(String std_religion) {
		this.std_religion = std_religion;
	}

	public void setStd_nrc(String std_nrc) {
		this.std_nrc = std_nrc;
	}

	public void setStd_dob(Date std_dob) {
		this.std_dob = std_dob;
	}

	public void setStd_mat_id(String std_mat_id) {
		this.std_mat_id = std_mat_id;
	}

	public void setStd_mat_year(String std_mat_year) {
		this.std_mat_year = std_mat_year;
	}

	public void setStd_mat_dept(String std_mat_dept) {
		this.std_mat_dept = std_mat_dept;
	}

	public void setMajor_code(String major_code) {
		this.major_code = major_code;
	}

	public void setFather_nrc(String father_nrc) {
		this.father_nrc = father_nrc;
	}

	public void setMother_nrc(String mother_nrc) {
		this.mother_nrc = mother_nrc;
	}

	public void setStd_birth_place(String std_birth_place) {
		this.std_birth_place = std_birth_place;
	}

	public void setStd_addr_perm(String std_addr_perm) {
		this.std_addr_perm = std_addr_perm;
	}

	public void setStd_addr_curr(String std_addr_curr) {
		this.std_addr_curr = std_addr_curr;
	}

	public void setStd_phone(String std_phone) {
		this.std_phone = std_phone;
	}

	public void setStd_email(String std_email) {
		this.std_email = std_email;
	}

	@Override
	public String toString() {
		return std_id + "|" + std_name
				+ "|" + std_name_mm + "|" + std_gender
				+ "|" + std_ethnic + "|" + std_religion
				+ "|" + std_nrc + "|" + Converter.convertDateToString(std_dob)
				+ "|" + std_mat_id + "|"
				+ std_mat_year + "|" + std_mat_dept
				+ "|" + major_code + "|" + father_nrc
				+ "|" + mother_nrc + "|"
				+ std_birth_place + "|" + std_addr_perm
				+ "|" + std_addr_curr + "|"
				+ std_phone + "|" + std_email;
	}

}
