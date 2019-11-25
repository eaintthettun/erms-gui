package model;

public class ChangeOfSub {
	
	private String sub_code,start_year,end_year,sub_name;

	public String getSub_code() {
		return sub_code;
	}

	public String getStart_year() {
		return start_year;
	}

	public String getEnd_year() {
		return end_year;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}

	public void setStart_year(String start_year) {
		this.start_year = start_year;
	}

	public void setEnd_year(String end_year) {
		this.end_year = end_year;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	@Override
	public String toString() {
		return "ChangeOfSub [sub_code=" + sub_code + ", start_year="
				+ start_year + ", end_year=" + end_year + ", sub_name="
				+ sub_name + "]";
	}
}
