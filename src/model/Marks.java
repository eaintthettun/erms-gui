package model;

public class Marks {
	
	private String academic_year,std_id,rollno,major_code,year,semester,subjects,marks;

	public String getAcademic_year() {
		return academic_year;
	}

	public String getStd_id() {
		return std_id;
	}

	public String getRollno() {
		return rollno;
	}

	public String getMajor_code() {
		return major_code;
	}

	public String getYear() {
		return year;
	}

	public String getSemester() {
		return semester;
	}

	public String getSubjects() {
		return subjects;
	}

	public String getMarks() {
		return marks;
	}

	public void setAcademic_year(String academic_year) {
		this.academic_year = academic_year;
	}

	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public void setMajor_code(String major_code) {
		this.major_code = major_code;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Marks [academic_year=" + academic_year + ", std_id=" + std_id
				+ ", rollno=" + rollno + ", major_code=" + major_code
				+ ", year=" + year + ", semester=" + semester + ", subjects="
				+ subjects + ", marks=" + marks + "]";
	}

}
