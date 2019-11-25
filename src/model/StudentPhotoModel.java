package model;

import java.io.InputStream;
import java.util.Arrays;

public class StudentPhotoModel {
	
	private String std_id;
	private String std_name;
	private int photo_size;
	private InputStream photo_data;
	private byte[] photo_byte;
	
	public String getStd_id() {
		return std_id;
	}
	public String getStd_name() {
		return std_name;
	}
	public int getPhoto_size() {
		return photo_size;
	}
	public InputStream getPhoto_data() {
		return photo_data;
	}
	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}
	public void setStd_name(String std_name) {
		this.std_name = std_name;
	}
	public void setPhoto_size(int photo_size) {
		this.photo_size = photo_size;
	}
	public void setPhoto_data(InputStream photo_data) {
		this.photo_data = photo_data;
	}
	public byte[] getPhoto_byte() {
		return photo_byte;
	}
	public void setPhoto_byte(byte[] photo_byte) {
		this.photo_byte = photo_byte;
	}
	@Override
	public String toString() {
		return "StudentPhotoModel [std_id=" + std_id + ", std_name=" + std_name
				+ ", photo_size=" + photo_size + ", photo_data=" + photo_data
				+ ", photo_byte=" + Arrays.toString(photo_byte) + "]";
	}

}
