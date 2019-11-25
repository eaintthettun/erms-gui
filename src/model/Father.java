package model;

public class Father {
	private int father_id;
	private String father_name;
	private String father_name_mm;
	private String father_ethnic;
	private String father_religion;
	private String father_birth_place;
	private String father_nrc;
	private String father_address;
	
	public Father() {
		this.clearProperties();
	}
	
	private void clearProperties() {
		this.father_id = 0;
		this.father_name = "";
		this.father_name_mm = "";
		this.father_ethnic = "";
		this.father_religion = "";
		this.father_birth_place = "";
		this.father_nrc = "";
		this.father_address = "";
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public String getFather_name_mm() {
		return father_name_mm;
	}
	public void setFather_name_mm(String father_name_mm) {
		this.father_name_mm = father_name_mm;
	}
	public String getFather_ethnic() {
		return father_ethnic;
	}
	public void setFather_ethnic(String father_ethnic) {
		this.father_ethnic = father_ethnic;
	}
	public String getFather_religion() {
		return father_religion;
	}
	public void setFather_religion(String father_religion) {
		this.father_religion = father_religion;
	}
	public String getFather_birth_place() {
		return father_birth_place;
	}
	public void setFather_birth_place(String father_birth_place) {
		this.father_birth_place = father_birth_place;
	}
	public String getFather_nrc() {
		return father_nrc;
	}
	public void setFather_nrc(String father_nrc) {
		this.father_nrc = father_nrc;
	}
	public String getFather_address() {
		return father_address;
	}
	public void setFather_address(String father_address) {
		this.father_address = father_address;
	}
	public int getFather_id() {
		return father_id;
	}
	public void setFather_id(int father_id) {
		this.father_id = father_id;
	}
	@Override
	public String toString() {
		return father_name + "|"
				+ father_name_mm + "|" + father_ethnic
				+ "|" + father_religion
				+ "|" + father_birth_place
				+ "|" + father_nrc + "|"
				+ father_address;
	}
	
}
