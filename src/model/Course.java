package model;

public class Course {
	
	private String academic_year;
	private String major_code;
	private String year;
	private String semester;
	private String subjects;
	
	public String getAcademic_year() {
		return academic_year;
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
	public void setAcademic_year(String academic_year) {
		this.academic_year = academic_year;
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
	@Override
	public String toString() {
		return "Course [academic_year=" + academic_year + ", major_code="
				+ major_code + ", year=" + year + ", semester=" + semester
				+ ", subjects=" + subjects + "]";
	}

}
