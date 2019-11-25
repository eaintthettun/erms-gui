package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
import utils.OfficeUsage;
import view.ButtonPane;
import view.RightPane;
import view.StudentView;

public class RegistrationUpdateController {

	private MainController myMainController;
	private ButtonPane myButtonPane;
	private RightPane myRightPane;
	private StudentView myStudentView;
	private String id;

	private File originalPhoto;
	private ArrayList<String> warning = new ArrayList<String>();
	private String temp;
	private boolean isEditing;
	
	private ButtonMouseListener oneML;
	private ButtonMouseListener twoML;

	public RegistrationUpdateController(MainController mc,ButtonPane myButtonPane,
			RightPane myRightPane, StudentView myStudentView, String std_id) {

		this.myMainController = mc;
		this.myButtonPane = myButtonPane;
		this.myRightPane = myRightPane;
		this.myStudentView = myStudentView;
		this.id = std_id;
		
		this.oneML = new ButtonMouseListener(this.myButtonPane.getBtnOne());
		this.twoML = new ButtonMouseListener(this.myButtonPane.getBtnTwo());

		this.myButtonPane.getBtnFive().setVisible(false);
		this.myButtonPane.getBtnFour().setVisible(false);
		
		this.myButtonPane.getBtnThree().setText("Export To Excel");
		this.myButtonPane.getBtnTwo().setText("Edit");
		this.myButtonPane.getBtnOne().setText("Undo");

		this.myStudentView.getLblBack().setVisible(true);
		this.myStudentView.getTxtEntranceID().setEditable(false);
		this.myStudentView.getTxtEntranceID().setForeground(Color.MAGENTA);
		this.myRightPane.getParentsView().getTxtFatherNRC().setEditable(false);
		this.myRightPane.getParentsView().getTxtFatherNRC().setForeground(Color.MAGENTA);
		this.myRightPane.getParentsView().getTxtMotherNRC().setEditable(false);
		this.myRightPane.getParentsView().getTxtMotherNRC().setForeground(Color.MAGENTA);

		prepareData();
		setEditing(false);

		this.myRightPane.getPhotoSectionView().getLblPhotoBox()
				.addMouseListener(new MouseAdapter() {

					@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if (isEditing)
						browsePhoto();
				}

		});
		
		this.myButtonPane.getBtnThree().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel Worksheet", "xls","xlsx");
				jfc.setAcceptAllFileFilterUsed(false);
				jfc.addChoosableFileFilter(filter);
				
				int ret = jfc.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					
					String desFile = f.getAbsolutePath();
					
					if(!desFile.endsWith(".xls") || !desFile.endsWith(".xlsx")){
						desFile = desFile+".xlsx";
					}
					
					File trueFile = new File(desFile);
					Student std = StudentDao.getStudentByID(
							RegistrationUpdateController.this.myStudentView.getTxtEntranceID().getText());
					if(OfficeUsage.exportStudentToExcel(std, trueFile))
						JOptionPane.showMessageDialog(null, "Successfully Exported to Excel!", 
								"Export Success", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Unable to Export to Excel!", 
								"Export Failure", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.myButtonPane.getBtnTwo().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (RegistrationUpdateController.this.myButtonPane.getBtnTwo()
						.getText().equals("Done")) {
					processUpdate();
					originalPhoto = null;
				} else {
					setEditing(true);
				}
			}

		});

		this.myButtonPane.getBtnOne().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setEditing(false);
				prepareData();
				originalPhoto = null;
			}
		});
		
		this.myButtonPane.getBtnThree().addMouseListener(new ButtonMouseListener(this.myButtonPane.getBtnThree()));
		this.myButtonPane.getBtnTwo().addMouseListener(new ButtonMouseListener(this.myButtonPane.getBtnTwo()));
		this.myButtonPane.getBtnOne().addMouseListener(new ButtonMouseListener(this.myButtonPane.getBtnOne()));
		
		this.myStudentView.getLblBack().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(MainController.MENU_NO == 3)
					myMainController.prepareShowAllStudents();
				if(MainController.MENU_NO == 4)
					myMainController.prepareStudentInfo1(id);
			}
			
		});
	}

	protected void browsePhoto() {
		// TODO Auto-generated method stub

		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("images",
				"jpg", "jpeg", "png", "gif");
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.addChoosableFileFilter(filter);

		int myRet = jfc.showOpenDialog(this.myStudentView);
		if (myRet == JFileChooser.APPROVE_OPTION) {
			this.originalPhoto = jfc.getSelectedFile();
			try {
				BufferedImage bufImg = ImageIO.read(this.originalPhoto);
				bufImg = resize(bufImg);
				bufImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon imgIcon = new ImageIcon(bufImg);
				this.myRightPane.getPhotoSectionView().getLblPhotoBox()
						.setText(null);
				this.myRightPane.getPhotoSectionView().getLblPhotoBox()
						.setIcon(imgIcon);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.myRightPane.getPhotoSectionView().getLblPhotoBox()
						.setText("+ Add Photo");
			}
		}
	}

	private BufferedImage resize(BufferedImage bufImg) {
		BufferedImage resized = new BufferedImage(100, 100, bufImg.getType());
		Graphics2D g = resized.createGraphics();
		g.drawImage(bufImg, 0, 0, 100, 100, null);
		g.dispose();
		return resized;
	}

	protected void processUpdate() {
		
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
		
		temp = this.myStudentView.getTxtNameMM().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Please Enter Student Name (MM)");
		}
		else if(Checker.checkTextLength(temp, 60)){
			warning.add("Student Name (MM) is too long!");
		}
		else{
			std.setStd_name_mm(temp);
		}
		
		std.setStd_id(this.id);
		
		if(this.myStudentView.getRadioMale().isSelected())
			std.setStd_gender("M");
		else
			std.setStd_gender("F");
		
		temp = this.myStudentView.getTxtEthnicGroup().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Student Ethnic Type is missing");
		}else if(Checker.checkTextLength(temp, 20)){
			warning.add("Student Ethnic Type should be less than 20 characters");
		}else{
			std.setStd_ethnic(temp);
		}
		
		temp = this.myStudentView.getTxtReligion().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Student Religion is missing");
		}else if(Checker.checkTextLength(temp, 30)){
			warning.add("Student Religion should be less than 30 characters");
		}else{
			std.setStd_religion(temp);
		}
		
		temp = this.myStudentView.getTxtNRCNo().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Please Enter Student NRC No.");
		}else if(Checker.checkTextLength(temp, 30)){
			warning.add("Student Student NRC No. too long");
		}else{
			std.setStd_nrc(temp);
		}
		
		if(this.myStudentView.getDateChooser().getDate()!=null){
			std.setStd_dob(this.myStudentView.getDateChooser().getDate());
		}else
			warning.add("Please Choose Student Date Of Birth");
		
		int index = this.myStudentView.getComboBoxMajor().getSelectedIndex();
		std.setMajor_code(MajorDao.getPrimaryMajorCode().get(index));
		
		temp = this.myStudentView.getTxtPhone().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Please Enter Student Phone No.");
		}
		else if(!temp.matches("\\d*")){
			warning.add("Please Enter The Correct Phone No.");
		}
		else if(Checker.checkTextLength(temp, 15)){
			warning.add("Phone No. is too long!");
		}
		else{
			std.setStd_phone(temp);
		}
		
		temp = this.myStudentView.getTxtEmail().getText();
		if(Checker.checkTextLength(temp, 50)){
			warning.add("Email too long!");
		}
		else{
			std.setStd_email(temp);
		}
		
		temp = this.myStudentView.getTxtBirthPlace().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Please Enter Student Birth Place");
		}else if(Checker.checkTextLength(temp, 150)){
			warning.add("Birth Place is too long!");
		}
		else{
			std.setStd_birth_place(temp);
		}
		
		temp = this.myStudentView.getTxtPermAddr().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Please Enter Student Permanent Address");
		}else if(Checker.checkTextLength(temp, 150)){
			warning.add("Permanent Address is too long!");
		}
		else{
			std.setStd_addr_perm(temp);
		}
		
		temp = this.myStudentView.getTxtCurrentAddr().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Student's current address is missing");
		}else if(Checker.checkTextLength(temp, 150)){
			warning.add("Current Address is too long!");
		}
		else{
			std.setStd_addr_curr(temp);
		}
		
		/*
		 * Matriculation Section Starts Here
		 */
		
		temp = this.myRightPane.getMatSectionView().getTxtGrade10RollNo().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Please Enter Grade-10 Roll No.");
		}else if(Checker.checkTextLength(temp, 10)){
			warning.add("Student's Grade-10 Roll No. is too long!");
		}
		else{
			std.setStd_mat_id(temp);
		}
		
		temp = this.myRightPane.getMatSectionView().getTxtYearOfMat().getText();
		if(Checker.checkRequired(temp)){
			warning.add("Student's Matriculated Year is missing");
		}else if(temp.length()!=4){
			warning.add("Student's Matriculated Year must be 4 digits");
		}
		else{
			std.setStd_mat_year(temp);
		}
		
		temp = this.myRightPane.getMatSectionView().getTxtDeptMat().getText();
		if(Checker.checkRequired(temp)){
			warning.add("You can't leave the Dept. where Student is matriculated as blank");
		}else if(Checker.checkTextLength(temp, 50)){
			warning.add("String overflow at Passed Dept.");
		}
		else{
			std.setStd_mat_dept(temp);
		}
		
		/*
		 * Parents Section (Father) Starts Here
		 */
		
		Father f = new Father();
		
		temp = this.myRightPane.getParentsView().getTxtFatherAddr().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Father Job/Address"));
		}else if(Checker.checkTextLength(temp, 150)){
			warning.add(MyConstants.msgLength("Father Job/Address", 150));
		}
		else{
			f.setFather_address(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtFatherBirthPlace().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Father Birth Place"));
		}else if(Checker.checkTextLength(temp, 150)){
			warning.add(MyConstants.msgLength("Father Birth Place", 150));
		}
		else{
			f.setFather_birth_place(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtFatherEthnic().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Father Ethnic Type"));
		}else if(Checker.checkTextLength(temp, 20)){
			warning.add(MyConstants.msgLength("Father Ethnic Type", 20));
		}
		else{
			f.setFather_ethnic(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtFatherNameEN().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Father Name (EN)"));
		}else if(Checker.checkTextLength(temp, 60)){
			warning.add(MyConstants.msgLength("Father Name (EN)", 60));
		}
		else{
			f.setFather_name(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtFatherNameMM().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Father Name (MM)"));
		}else if(Checker.checkTextLength(temp, 60)){
			warning.add(MyConstants.msgLength("Father Name (MM)", 60));
		}
		else{
			f.setFather_name_mm(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtFatherNRC().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Father NRC No."));
		}else if(Checker.checkTextLength(temp, 30)){
			warning.add(MyConstants.msgLength("Father NRC No.", 30));
		}
		else{
			std.setFather_nrc(temp);
			f.setFather_nrc(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtFatherReligion().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Father Religion"));
		}else if(Checker.checkTextLength(temp, 30)){
			warning.add(MyConstants.msgLength("Father Religion", 30));
		}
		else{
			f.setFather_religion(temp);
		}
		
		/*
		 * Parents Section (Mother) Starts Here
		 */
		
		Mother m = new Mother();
		
		temp = this.myRightPane.getParentsView().getTxtMotherAddr().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Mother Job/Address"));
		}else if(Checker.checkTextLength(temp, 150)){
			warning.add(MyConstants.msgLength("Mother Job/Address", 150));
		}
		else{
			m.setMother_address(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtMotherBirthPlace().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Mother Birth Place"));
		}else if(Checker.checkTextLength(temp, 150)){
			warning.add(MyConstants.msgLength("Mother Birth Place", 150));
		}
		else{
			m.setMother_birth_place(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtMotherEthnic().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Mother Ethnic Type"));
		}else if(Checker.checkTextLength(temp, 20)){
			warning.add(MyConstants.msgLength("Mother Ethnic Type", 20));
		}
		else{
			m.setMother_ethnic(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtMotherNameEN().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Mother Name (EN)"));
		}else if(Checker.checkTextLength(temp, 60)){
			warning.add(MyConstants.msgLength("Mother Name (EN)", 60));
		}
		else{
			m.setMother_name(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtMotherNameMM().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Mother Name (MM)"));
		}else if(Checker.checkTextLength(temp, 60)){
			warning.add(MyConstants.msgLength("Mother Name (MM)", 60));
		}
		else{
			m.setMother_name_mm(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtMotherNRC().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Mother NRC No."));
		}else if(Checker.checkTextLength(temp, 30)){
			warning.add(MyConstants.msgLength("Mother NRC No.", 30));
		}
		else{
			std.setMother_nrc(temp);
			m.setMother_nrc(temp);
		}
		
		temp = this.myRightPane.getParentsView().getTxtMotherReligion().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Mother Religion"));
		}else if(Checker.checkTextLength(temp, 30)){
			warning.add(MyConstants.msgLength("Mother Religion", 30));
		}
		else{
			m.setMother_religion(temp);
		}
		
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
				
				StudentPhotoModel photoModel = StudentDao.getStudentPhotoByID(this.id);
				if(photoModel.getPhoto_size()<=0){
					if(!StudentDao.saveStudentPhoto(photo))
						JOptionPane.showMessageDialog(this.myStudentView,"Error Saving Photo In Database","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!StudentDao.updateStudentPhoto(photo))
					JOptionPane.showMessageDialog(this.myStudentView,"Error Updating Photo In Database","Error",JOptionPane.ERROR_MESSAGE);
			}
			//System.out.println(std);
			//System.out.println(f);
			//System.out.println(m);
			if (!(StudentDao.updateStudent(std) & FatherDao.updateFather(f)
					& MotherDao.updateMother(m)))
				JOptionPane.showMessageDialog(this.myStudentView,
						"Error Updating Student Info In Database",
						"Error Message", JOptionPane.ERROR_MESSAGE);
			else {
				JOptionPane.showMessageDialog(this.myStudentView,
						"Sucessfully Updated", "Information Message",
						JOptionPane.INFORMATION_MESSAGE);
				prepareData();
				setEditing(false);
			}
		} else {

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
/*
	private void clearAllTextfield() {

		this.warning.clear();

		// Clear All Text Fields in Student Section
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

		// Clear All Text Fields in Photo Section
		this.originalPhoto = null;
		this.myRightPane.getPhotoSectionView().getLblPhotoBox().setIcon(null);
		this.myRightPane.getPhotoSectionView().getLblPhotoBox()
				.setText("+ Add Photo");

		// Clear All Text Fields in Matriculation Section
		this.myRightPane.getMatSectionView().getTxtDeptMat().setText(null);
		this.myRightPane.getMatSectionView().getTxtGrade10RollNo()
				.setText(null);
		this.myRightPane.getMatSectionView().getTxtYearOfMat().setText(null);

		// Clear All Text Fields in Parents Section
		this.myRightPane.getParentsView().getTxtFatherAddr().setText(null);
		this.myRightPane.getParentsView().getTxtFatherBirthPlace()
				.setText(null);
		this.myRightPane.getParentsView().getTxtFatherEthnic().setText(null);
		this.myRightPane.getParentsView().getTxtFatherNameEN().setText(null);
		this.myRightPane.getParentsView().getTxtFatherNameMM().setText(null);
		this.myRightPane.getParentsView().getTxtFatherNRC().setText(null);
		this.myRightPane.getParentsView().getTxtFatherReligion().setText(null);

		this.myRightPane.getParentsView().getTxtMotherAddr().setText(null);
		this.myRightPane.getParentsView().getTxtMotherBirthPlace()
				.setText(null);
		this.myRightPane.getParentsView().getTxtMotherEthnic().setText(null);
		this.myRightPane.getParentsView().getTxtMotherNameEN().setText(null);
		this.myRightPane.getParentsView().getTxtMotherNameMM().setText(null);
		this.myRightPane.getParentsView().getTxtMotherNRC().setText(null);
		this.myRightPane.getParentsView().getTxtMotherReligion().setText(null);

	}
*/
	protected void prepareData() {
		
		Student std;
		StudentPhotoModel spm;
		
		if(Checker.isID(this.id)){
			std = StudentDao.getStudentByID(this.id);
			spm = StudentDao.getStudentPhotoByID(this.id);
		}else{
			std = StudentDao.getStudentByName(this.id).get(0);
			spm = StudentDao.getStudentPhotoByID(std.getStd_id());
		}
		
		if (spm.getPhoto_size()>0) {
			byte[] data = spm.getPhoto_byte();
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			try {
				BufferedImage img = ImageIO.read(bais);
				img = resize(img);
				img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(img);
				this.myRightPane.getPhotoSectionView().getLblPhotoBox()
						.setText("");
				this.myRightPane.getPhotoSectionView().getLblPhotoBox()
						.setIcon(icon);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (std != null) {

			Father f = FatherDao.getFatherByNrc(std.getFather_nrc());
			Mother m = MotherDao.getMotherByNrc(std.getMother_nrc());

			// Set All Text Fields in Student Section
			this.myStudentView.getTxtBirthPlace().setText(
					std.getStd_birth_place());
			this.myStudentView.getTxtCurrentAddr().setText(
					std.getStd_addr_curr());
			this.myStudentView.getTxtEmail().setText(std.getStd_email());
			this.myStudentView.getTxtEntranceID().setText(std.getStd_id());
			this.myStudentView.getTxtEthnicGroup().setText(std.getStd_ethnic());
			this.myStudentView.getTxtNameEN().setText(std.getStd_name());
			this.myStudentView.getTxtNameMM().setText(std.getStd_name_mm());
			this.myStudentView.getTxtNRCNo().setText(std.getStd_nrc());
			this.myStudentView.getTxtPermAddr().setText(std.getStd_addr_perm());
			this.myStudentView.getTxtPhone().setText(std.getStd_phone());
			this.myStudentView.getTxtReligion().setText(std.getStd_religion());

			// Set All Text Fields in Matriculation Section
			this.myRightPane.getMatSectionView().getTxtDeptMat()
					.setText(std.getStd_mat_dept());
			this.myRightPane.getMatSectionView().getTxtGrade10RollNo()
					.setText(std.getStd_mat_id());
			this.myRightPane.getMatSectionView().getTxtYearOfMat()
					.setText(std.getStd_mat_year());

			// Set All Text Fields in Parents Section
			this.myRightPane.getParentsView().getTxtFatherAddr()
					.setText(f.getFather_address());
			this.myRightPane.getParentsView().getTxtFatherBirthPlace()
					.setText(f.getFather_birth_place());
			this.myRightPane.getParentsView().getTxtFatherEthnic()
					.setText(f.getFather_ethnic());
			this.myRightPane.getParentsView().getTxtFatherNameEN()
					.setText(f.getFather_name());
			this.myRightPane.getParentsView().getTxtFatherNameMM()
					.setText(f.getFather_name_mm());
			this.myRightPane.getParentsView().getTxtFatherNRC()
					.setText(f.getFather_nrc());
			this.myRightPane.getParentsView().getTxtFatherReligion()
					.setText(f.getFather_religion());

			this.myRightPane.getParentsView().getTxtMotherAddr()
					.setText(m.getMother_address());
			this.myRightPane.getParentsView().getTxtMotherBirthPlace()
					.setText(m.getMother_birth_place());
			this.myRightPane.getParentsView().getTxtMotherEthnic()
					.setText(m.getMother_ethnic());
			this.myRightPane.getParentsView().getTxtMotherNameEN()
					.setText(m.getMother_name());
			this.myRightPane.getParentsView().getTxtMotherNameMM()
					.setText(m.getMother_name_mm());
			this.myRightPane.getParentsView().getTxtMotherNRC()
					.setText(m.getMother_nrc());
			this.myRightPane.getParentsView().getTxtMotherReligion()
					.setText(m.getMother_religion());

			// Set Student's gender, DOB & Major
			if (std.getStd_gender().equals("M"))
				this.myStudentView.getRadioMale().setSelected(true);
			else
				this.myStudentView.getRadioFemale().setSelected(true);

			this.myStudentView.getDateChooser().setDate(std.getStd_dob());
			this.myStudentView.getComboBoxMajor().setSelectedItem(
					MajorDao.getMajorCodeAndName().get(std.getMajor_code()));
		}

	}

	protected void setEditing(boolean b) {
		this.isEditing = b;

		// Set Editable() on All Text Fields in Student Section
		this.myStudentView.getTxtBirthPlace().setEditable(b);
		this.myStudentView.getTxtCurrentAddr().setEditable(b);
		this.myStudentView.getTxtEmail().setEditable(b);
		this.myStudentView.getTxtEntranceID().setEditable(b);
		this.myStudentView.getTxtEthnicGroup().setEditable(b);
		this.myStudentView.getTxtNameEN().setEditable(b);
		this.myStudentView.getTxtNameMM().setEditable(b);
		this.myStudentView.getTxtNRCNo().setEditable(b);
		this.myStudentView.getTxtPermAddr().setEditable(b);
		this.myStudentView.getTxtPhone().setEditable(b);
		this.myStudentView.getTxtReligion().setEditable(b);

		// Set Editable() on All Text Fields in Matriculation Section
		this.myRightPane.getMatSectionView().getTxtDeptMat().setEditable(b);
		this.myRightPane.getMatSectionView().getTxtGrade10RollNo()
				.setEditable(b);
		this.myRightPane.getMatSectionView().getTxtYearOfMat().setEditable(b);

		// Set Editable() on All Text Fields in Parents Section
		this.myRightPane.getParentsView().getTxtFatherAddr().setEditable(b);
		this.myRightPane.getParentsView().getTxtFatherBirthPlace()
				.setEditable(b);
		this.myRightPane.getParentsView().getTxtFatherEthnic().setEditable(b);
		this.myRightPane.getParentsView().getTxtFatherNameEN().setEditable(b);
		this.myRightPane.getParentsView().getTxtFatherNameMM().setEditable(b);
		this.myRightPane.getParentsView().getTxtFatherNRC().setEditable(b);
		this.myRightPane.getParentsView().getTxtFatherReligion().setEditable(b);

		this.myRightPane.getParentsView().getTxtMotherAddr().setEditable(b);
		this.myRightPane.getParentsView().getTxtMotherBirthPlace()
				.setEditable(b);
		this.myRightPane.getParentsView().getTxtMotherEthnic().setEditable(b);
		this.myRightPane.getParentsView().getTxtMotherNameEN().setEditable(b);
		this.myRightPane.getParentsView().getTxtMotherNameMM().setEditable(b);
		this.myRightPane.getParentsView().getTxtMotherNRC().setEditable(b);
		this.myRightPane.getParentsView().getTxtMotherReligion().setEditable(b);

		this.myStudentView.getRadioFemale().setEnabled(b);
		this.myStudentView.getRadioMale().setEnabled(b);
		this.myStudentView.getComboBoxMajor().setEnabled(b);
		this.myStudentView.getDateChooser().setEnabled(b);
		this.myButtonPane.getBtnOne().setEnabled(b);
		this.myButtonPane.getBtnThree().setEnabled(!b);

		if (b) {
			this.myButtonPane.getBtnTwo().setText("Done");
			this.myButtonPane.getBtnTwo().addMouseListener(this.twoML);
			this.myButtonPane.getBtnOne().addMouseListener(this.oneML);
		} else {
			this.myButtonPane.getBtnTwo().setText("Edit");
			this.myButtonPane.getBtnTwo().removeMouseListener(this.twoML);
			this.myButtonPane.getBtnOne().removeMouseListener(this.oneML);
		}
	}

}
