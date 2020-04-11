package controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Father;
import model.Mother;
import model.Student;
import model.StudentPhotoModel;
import database.FatherDao;
import database.MajorDao;
import database.MotherDao;
import database.StudentDao;
import utils.Checker;
import utils.MyConstants;
import view.ButtonPane;
import view.RightPane;
import view.StudentView;

public class RegistrationController {
	
	private ButtonPane myButtonPane;
	private RightPane myRightPane;
	private StudentView myStudentView;
	
	private File originalPhoto;
	private ArrayList<String> warning = new ArrayList<String>();
	private String temp;
	
	public RegistrationController(ButtonPane myButtonPane,
			RightPane myRightPane, StudentView myStudentView) {
		
		this.myButtonPane = myButtonPane;
		this.myRightPane = myRightPane;
		this.myStudentView = myStudentView;
		
		this.myButtonPane.getBtnFive().setVisible(false);
		this.myButtonPane.getBtnFour().setVisible(false);
		this.myButtonPane.getBtnThree().setVisible(false);
		
		this.myButtonPane.getBtnTwo().setText("Register");
		this.myButtonPane.getBtnOne().setText("Clear");
		
		this.myStudentView.getLblBack().setVisible(false);
		
		this.myRightPane.getPhotoSectionView().getLblPhotoBox().addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				browsePhoto();
			}
			
		});
		
		this.myButtonPane.getBtnTwo().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				processSave();
			}
			
		});
		
		this.myButtonPane.getBtnOne().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {			
				JOptionPane.showMessageDialog(null,"All Entered Information will be lost!","Information Message",JOptionPane.INFORMATION_MESSAGE);
				clearAllTextfield();
			}
		});	
		
		this.myButtonPane.getBtnTwo().addMouseListener(new ButtonMouseListener(this.myButtonPane.getBtnTwo()));
		this.myButtonPane.getBtnOne().addMouseListener(new ButtonMouseListener(this.myButtonPane.getBtnOne()));
	}
	
	protected void browsePhoto() {
		// TODO Auto-generated method stub
		
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("images","jpg","jpeg","png","gif");
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.addChoosableFileFilter(filter);
		
		int myRet = jfc.showOpenDialog(this.myStudentView);
		if(myRet == JFileChooser.APPROVE_OPTION){
			this.originalPhoto = jfc.getSelectedFile();		
			try {
				BufferedImage bufImg = ImageIO.read(this.originalPhoto);
				bufImg = resize(bufImg);
				bufImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon imgIcon = new ImageIcon(bufImg);
				this.myRightPane.getPhotoSectionView().getLblPhotoBox().setText(null);
				this.myRightPane.getPhotoSectionView().getLblPhotoBox().setIcon(imgIcon);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.myRightPane.getPhotoSectionView().getLblPhotoBox().setText("+ Add Photo");
			}
		}
	}
	
	private BufferedImage resize(BufferedImage bufImg) {
		BufferedImage resized = new BufferedImage(100,100,bufImg.getType());
		Graphics2D g = resized.createGraphics();
		g.drawImage(bufImg, 0, 0, 100, 100, null);
		g.dispose();
		return resized;
	}
	protected void processSave() {
		
		/*
		 * Student Section Starts Here
		 */
		
		Student std = new Student();
		
		temp = this.myStudentView.getTxtNameEN().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Please Enter Student Name (EN)");
		}
		else if(Checker.checkTextLength(temp, 60)){
			warning.add("Student Name (EN) is too long!");
		}
		else{
			std.setStd_name(temp);
		}
//		
		temp = this.myStudentView.getTxtNameMM().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("Please Enter Student Name (MM)");
//		}
//		else if(Checker.checkTextLength(temp, 60)){
//			warning.add("Student Name (MM) is too long!");
//		}
//		else{
			std.setStd_name_mm(temp);
//		}
//		
		temp = this.myStudentView.getTxtEntranceID().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Please Enter Student Entrance ID");
		}
		else if(!Checker.isID(temp)){
			warning.add("Invalid ID Format! It must be \'dd/ddddd\'");
		}
		else{
			std.setStd_id(temp);
		}
		
		if(this.myStudentView.getRadioMale().isSelected())
			std.setStd_gender("M");
		else
			std.setStd_gender("F");
//		
		temp = this.myStudentView.getTxtEthnicGroup().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("Student Ethnic Type is missing");
//		}else if(Checker.checkTextLength(temp, 20)){
//			warning.add("Student Ethnic Type should be less than 20 characters");
//		}else{
			std.setStd_ethnic(temp);
//		}
//		
		temp = this.myStudentView.getTxtReligion().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("Student Religion is missing");
//		}else if(Checker.checkTextLength(temp, 30)){
//			warning.add("Student Religion should be less than 30 characters");
//		}else{
			std.setStd_religion(temp);
//		}
//		
		temp = this.myStudentView.getTxtNRCNo().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("Please Enter Student NRC No.");
//		}else if(Checker.checkTextLength(temp, 30)){
//			warning.add("Student Student NRC No. too long");
//		}else{
			std.setStd_nrc(temp);
//		}
//		
//		if(this.myStudentView.getDateChooser().getDate()!=null){
			std.setStd_dob(this.myStudentView.getDateChooser().getDate());
//		}else
//			warning.add("Please Choose Student Date Of Birth");
		
		int index = this.myStudentView.getComboBoxMajor().getSelectedIndex();
		std.setMajor_code(MajorDao.getPrimaryMajorCode().get(index));
		
		temp = this.myStudentView.getTxtPhone().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("Please Enter Student Phone No.");
//		}
//		else if(!temp.matches("\\d*")){
//			warning.add("Please Enter The Correct Phone No.");
//		}
//		else if(Checker.checkTextLength(temp, 15)){
//			warning.add("Phone No. is too long!");
//		}
//		else{
			std.setStd_phone(temp);
//		}
//		
		temp = this.myStudentView.getTxtEmail().getText();
//		if(Checker.checkTextLength(temp, 50)){
//			warning.add("Email too long!");
//		}
//		else{
			std.setStd_email(temp);
//		}
//		
		temp = this.myStudentView.getTxtBirthPlace().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("Please Enter Student Birth Place");
//		}else if(Checker.checkTextLength(temp, 150)){
//			warning.add("Birth Place is too long!");
//		}
//		else{
			std.setStd_birth_place(temp);
//		}
//		
		temp = this.myStudentView.getTxtPermAddr().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("Please Enter Student Permanent Address");
//		}else if(Checker.checkTextLength(temp, 150)){
//			warning.add("Permanent Address is too long!");
//		}
//		else{
			std.setStd_addr_perm(temp);
//		}
//		
		temp = this.myStudentView.getTxtCurrentAddr().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("Student's current address is missing");
//		}else if(Checker.checkTextLength(temp, 150)){
//			warning.add("Current Address is too long!");
//		}
//		else{
			std.setStd_addr_curr(temp);
//		}
		
		/*
		 * Matriculation Section Starts Here
		 */
		
		temp = this.myRightPane.getMatSectionView().getTxtGrade10RollNo().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("Please Enter Grade-10 Roll No.");
//		}else if(Checker.checkTextLength(temp, 20)){
//			warning.add("Student's Grade-10 Roll No. is too long!");
//		}
//		else{
			std.setStd_mat_id(temp);
//		}
//		
		temp = this.myRightPane.getMatSectionView().getTxtYearOfMat().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("Student's Matriculated Year is missing");
//		}else if(temp.length()!=4){
//			warning.add("Student's Matriculated Year must be 4 digits");
//		}
//		else{
			std.setStd_mat_year(temp);
//		}
//		
		temp = this.myRightPane.getMatSectionView().getTxtDeptMat().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add("You can't leave the Dept. where Student is matriculated as blank");
//		}else if(Checker.checkTextLength(temp, 50)){
//			warning.add("String overflow at Passed Dept.");
//		}
//		else{
			std.setStd_mat_dept(temp);
//		}
//		
		/*
		 * Parents Section (Father) Starts Here
		 */
		
		Father f = new Father();
//		
		temp = this.myRightPane.getParentsView().getTxtFatherAddr().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Father Job/Address"));
//		}else if(Checker.checkTextLength(temp, 150)){
//			warning.add(MyConstants.msgLength("Father Job/Address", 150));
//		}
//		else{
			f.setFather_address(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtFatherBirthPlace().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Father Birth Place"));
//		}else if(Checker.checkTextLength(temp, 150)){
//			warning.add(MyConstants.msgLength("Father Birth Place", 150));
//		}
//		else{
			f.setFather_birth_place(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtFatherEthnic().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Father Ethnic Type"));
//		}else if(Checker.checkTextLength(temp, 20)){
//			warning.add(MyConstants.msgLength("Father Ethnic Type", 20));
//		}
//		else{
			f.setFather_ethnic(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtFatherNameEN().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Father Name (EN)"));
//		}else if(Checker.checkTextLength(temp, 60)){
//			warning.add(MyConstants.msgLength("Father Name (EN)", 60));
//		}
//		else{
			f.setFather_name(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtFatherNameMM().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Father Name (MM)"));
//		}else if(Checker.checkTextLength(temp, 60)){
//			warning.add(MyConstants.msgLength("Father Name (MM)", 60));
//		}
//		else{
			f.setFather_name_mm(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtFatherNRC().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Father NRC No."));
//		}else if(Checker.checkTextLength(temp, 30)){
//			warning.add(MyConstants.msgLength("Father NRC No.", 30));
//		}
//		else{
			std.setFather_nrc(temp);
			f.setFather_nrc(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtFatherReligion().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Father Religion"));
//		}else if(Checker.checkTextLength(temp, 30)){
//			warning.add(MyConstants.msgLength("Father Religion", 30));
//		}
//		else{
			f.setFather_religion(temp);
//		}
//		
//		/*
//		 * Parents Section (Mother) Starts Here
//		 */
//		
		Mother m = new Mother();
//		
		temp = this.myRightPane.getParentsView().getTxtMotherAddr().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Mother Job/Address"));
//		}else if(Checker.checkTextLength(temp, 150)){
//			warning.add(MyConstants.msgLength("Mother Job/Address", 150));
//		}
//		else{
			m.setMother_address(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtMotherBirthPlace().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Mother Birth Place"));
//		}else if(Checker.checkTextLength(temp, 150)){
//			warning.add(MyConstants.msgLength("Mother Birth Place", 150));
//		}
//		else{
			m.setMother_birth_place(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtMotherEthnic().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Mother Ethnic Type"));
//		}else if(Checker.checkTextLength(temp, 20)){
//			warning.add(MyConstants.msgLength("Mother Ethnic Type", 20));
//		}
//		else{
			m.setMother_ethnic(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtMotherNameEN().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Mother Name (EN)"));
//		}else if(Checker.checkTextLength(temp, 60)){
//			warning.add(MyConstants.msgLength("Mother Name (EN)", 60));
//		}
//		else{
			m.setMother_name(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtMotherNameMM().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Mother Name (MM)"));
//		}else if(Checker.checkTextLength(temp, 60)){
//			warning.add(MyConstants.msgLength("Mother Name (MM)", 60));
//		}
//		else{
			m.setMother_name_mm(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtMotherNRC().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Mother NRC No."));
//		}else if(Checker.checkTextLength(temp, 30)){
//			warning.add(MyConstants.msgLength("Mother NRC No.", 30));
//		}
//		else{
			std.setMother_nrc(temp);
			m.setMother_nrc(temp);
//		}
//		
		temp = this.myRightPane.getParentsView().getTxtMotherReligion().getText();
//		if(Checker.checkRequired(temp)){
//			warning.add(MyConstants.msgRequired("Mother Religion"));
//		}else if(Checker.checkTextLength(temp, 30)){
//			warning.add(MyConstants.msgLength("Mother Religion", 30));
//		}
//		else{
			m.setMother_religion(temp);
//		}
//		
		/*
		 * Photo Section Starts Here
		 */
		
		if(this.originalPhoto!=null && this.originalPhoto.length()>2200000)
			this.warning.add("Cannot stick too large photo!\nPhoto Size must be less than 2.2MB");
		
		if(this.warning.isEmpty() && this.warning.size()==0){
			
			if(this.originalPhoto!=null){
				StudentPhotoModel photo = new StudentPhotoModel();
				photo.setStd_id(std.getStd_id());
				photo.setStd_name(std.getStd_name());
				photo.setPhoto_size((int)this.originalPhoto.length());
				
				try {
					photo.setPhoto_data(new FileInputStream(this.originalPhoto));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(!StudentDao.saveStudentPhoto(photo))
					JOptionPane.showMessageDialog(this.myStudentView,"Error Saving Photo In Database","Error Message",JOptionPane.ERROR_MESSAGE);
			}

			if(!(StudentDao.saveStudent(std) & FatherDao.saveFather(f) & MotherDao.saveMother(m)))
//			if(!(StudentDao.saveStudent(std)))
				JOptionPane.showMessageDialog(this.myStudentView,"Error Saving Student Info In Database","Error Message",JOptionPane.ERROR_MESSAGE);
			else{
				JOptionPane.showMessageDialog(this.myStudentView, "Sucessfully Registered","Information Message",JOptionPane.INFORMATION_MESSAGE);
				clearAllTextfield();
			}
		}else{

			String message = "";
			if(this.warning.size()>5)
				message = "You missed a lot of data or made a lot of errors!\n";
			else{
				for(String e:this.warning)
					message = message+e+"\n";
			}
			
			JOptionPane.showMessageDialog(this.myStudentView, message,"Warnings",JOptionPane.WARNING_MESSAGE);		
		}
		
		this.warning.clear();
	}
	private void clearAllTextfield() {
		
		this.warning.clear();
		
		//Clear All Text Fields in Student Section
		this.myStudentView.getTxtBirthPlace().setText(null);
		this.myStudentView.getTxtCurrentAddr().setText(null);
		this.myStudentView.getTxtEmail().setText(null);
		this.myStudentView.getTxtEntranceID().setText(null);
		this.myStudentView.getTxtEthnicGroup().setText(null);
		this.myStudentView.getTxtNameEN().setText(null);
		this.myStudentView.getTxtNameMM().setText(null);
		this.myStudentView.getTxtNRCNo().setText(null);
		this.myStudentView.getTxtPermAddr().setText(null);
		this.myStudentView.getTxtPhone().setText(null);
		this.myStudentView.getTxtReligion().setText(null);
		
		//Clear All Text Fields in Photo Section
		this.originalPhoto = null;
		this.myRightPane.getPhotoSectionView().getLblPhotoBox().setIcon(null);
		this.myRightPane.getPhotoSectionView().getLblPhotoBox().setText("+ Add Photo");

		//Clear All Text Fields in Matriculation Section
		this.myRightPane.getMatSectionView().getTxtDeptMat().setText(null);
		this.myRightPane.getMatSectionView().getTxtGrade10RollNo().setText(null);
		this.myRightPane.getMatSectionView().getTxtYearOfMat().setText(null);
		
		//Clear All Text Fields in Parents Section
		this.myRightPane.getParentsView().getTxtFatherAddr().setText(null);
		this.myRightPane.getParentsView().getTxtFatherBirthPlace().setText(null);
		this.myRightPane.getParentsView().getTxtFatherEthnic().setText(null);
		this.myRightPane.getParentsView().getTxtFatherNameEN().setText(null);
		this.myRightPane.getParentsView().getTxtFatherNameMM().setText(null);
		this.myRightPane.getParentsView().getTxtFatherNRC().setText(null);
		this.myRightPane.getParentsView().getTxtFatherReligion().setText(null);
		
		this.myRightPane.getParentsView().getTxtMotherAddr().setText(null);
		this.myRightPane.getParentsView().getTxtMotherBirthPlace().setText(null);
		this.myRightPane.getParentsView().getTxtMotherEthnic().setText(null);
		this.myRightPane.getParentsView().getTxtMotherNameEN().setText(null);
		this.myRightPane.getParentsView().getTxtMotherNameMM().setText(null);
		this.myRightPane.getParentsView().getTxtMotherNRC().setText(null);
		this.myRightPane.getParentsView().getTxtMotherReligion().setText(null);
		
	}

}
