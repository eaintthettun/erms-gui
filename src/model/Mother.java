package model;

public class Mother {
	private int mother_id;
	private String mother_name;
	private String mother_name_mm;
	private String mother_ethnic;
	private String mother_religion;
	private String mother_birth_place;
	private String mother_nrc;
	private String mother_address;
	
	public Mother() {
		this.clearProperties();
	}
	private void clearProperties() {
		this.mother_id = 0;
		this.mother_name = "";
		this.mother_name_mm = "";
		this.mother_ethnic = "";
		this.mother_religion = "";
		this.mother_birth_place = "";
		this.mother_nrc = "";
		this.mother_address = "";
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public String getMother_name_mm() {
		return mother_name_mm;
	}
	public void setMother_name_mm(String mother_name_mm) {
		this.mother_name_mm = mother_name_mm;
	}
	public String getMother_ethnic() {
		return mother_ethnic;
	}
	public void setMother_ethnic(String mother_ethnic) {
		this.mother_ethnic = mother_ethnic;
	}
	public String getMother_religion() {
		return mother_religion;
	}
	public void setMother_religion(String mother_religion) {
		this.mother_religion = mother_religion;
	}
	public String getMother_birth_place() {
		return mother_birth_place;
	}
	public void setMother_birth_place(String mother_birth_place) {
		this.mother_birth_place = mother_birth_place;
	}
	public String getMother_nrc() {
		return mother_nrc;
	}
	public void setMother_nrc(String mother_nrc) {
		this.mother_nrc = mother_nrc;
	}
	public String getMother_address() {
		return mother_address;
	}
	public void setMother_address(String mother_address) {
		this.mother_address = mother_address;
	}
	public int getMother_id() {
		return mother_id;
	}
	public void setMother_id(int mother_id) {
		this.mother_id = mother_id;
	}
	@Override
	public String toString() {
		return mother_name + "|"
				+ mother_name_mm + "|" + mother_ethnic
				+ "|" + mother_religion
				+ "|" + mother_birth_place
				+ "|" + mother_nrc + "|"
				+ mother_address;
	}

}
