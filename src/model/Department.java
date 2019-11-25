package model;

public class Department {

	private String dept_id;
	private String dept_name;
	private String dept_name_mm;
	public String getDept_id() {
		return dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public String getDept_name_mm() {
		return dept_name_mm;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public void setDept_name_mm(String dept_name_mm) {
		this.dept_name_mm = dept_name_mm;
	}
	@Override
	public String toString() {
		return "Department [dept_id=" + dept_id + ", dept_name=" + dept_name
				+ ", dept_name_mm=" + dept_name_mm + "]";
	}
}
