package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.Course;
import database.ChangeOfSubDao;
import database.CourseDao;
import database.MajorDao;
import database.SubjectDao;
import utils.Checker;
import utils.Usage;
import view.AddNewCourseView;
import view.AllSubjectView;

public class AddNewCourseController {

	private AddNewCourseView ancv;
	
	private ArrayList<String> warnings;
	private ArrayList<String> subCodes;
	private String temp;
	private String oldMajType;
	
	public AddNewCourseController(AddNewCourseView ancv){
		this.ancv = ancv;
		
		this.subCodes = new ArrayList<String>();
		this.warnings = new ArrayList<String>();
		
		comboMajorChosen();
		
		this.ancv.getSubjectSection().getTxtSubjectCode().addActionListener(new MyActionListener());
		this.ancv.getSubjectSection().getTxtSubjectName().addActionListener(new MyActionListener());
		this.ancv.getSubjectSection().getBtnCreateSubject().addActionListener(new MyActionListener());
		this.ancv.getCourseSection().getTxtSubjectCode().addActionListener(new MyActionListener());
		this.ancv.getCourseSection().getBtnAdd().addActionListener(new MyActionListener());
		this.ancv.getCourseSection().getBtnCreateCourse().addActionListener(new MyActionListener());
		this.ancv.getCourseSection().getComboMajor().addActionListener(new MyActionListener());
		
		this.ancv.getCourseSection().getBtnAdd().setEnabled(false);
		
		this.ancv.getCourseSection().getTxtSubjectCode().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String code = AddNewCourseController.this.ancv.getCourseSection().getTxtSubjectCode().getText();
				if(Checker.isSubjectCode(code)){
					HashMap<String, String> subCN = SubjectDao.getSubjectCodeAndName();
					if(subCN != null && subCN.get(code)!=null){
						String acy = AddNewCourseController.this.ancv.getCourseSection().getTxtAcadYearStart().getText()+"-"
								+ AddNewCourseController.this.ancv.getCourseSection().getTxtAcadYearEnd().getText();
						AddNewCourseController.this.ancv.getCourseSection().getTxtSubjectName().setText(ChangeOfSubDao.getSubjectName(code, acy));
						AddNewCourseController.this.ancv.getCourseSection().getBtnAdd().setEnabled(true);
					}
				}else{
					AddNewCourseController.this.ancv.getCourseSection().getTxtSubjectName().setText(null);
					AddNewCourseController.this.ancv.getCourseSection().getBtnAdd().setEnabled(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.ancv.getCourseSection().getTableAddedSubs().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				tableClicked();
			}
			
		});
		
		this.ancv.getSubjectSection().getLblSeeAllSubjects().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				AddNewCourseController.this.ancv.getSubjectSection().getLblSeeAllSubjects().setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				AddNewCourseController.this.ancv.getSubjectSection().getLblSeeAllSubjects().setForeground(SystemColor.textHighlight);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							JFrame f = new JFrame();
							
							Usage.setFrameIcon(f);
							
							f.setVisible(true);
							f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							f.setBounds(300,100,750,550);
							f.setResizable(false);
							
							AllSubjectView allSubView = new AllSubjectView();
							allSubView.setVisible(true);
							allSubView.setSubjects(SubjectDao.getAllSubjects());
							allSubView.prepareView();
							
							JScrollPane jsp = new JScrollPane();
							jsp.setViewportView(allSubView);
							
							f.add(jsp);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
	
	protected void tableClicked() {

		int rowIndex = this.ancv.getCourseSection().getTableAddedSubs().getSelectedRow();
		int colIndex = this.ancv.getCourseSection().getTableAddedSubs().getSelectedColumn();
		
		if(colIndex == 3){
			Object[] recd = this.ancv.getCourseSection().getTableModel().getSelectedRecord(rowIndex);
			this.subCodes.remove(recd[1].toString());
			prepareTable();
		}
		
		if(colIndex == 4 && rowIndex!=0){
			Collections.swap(this.subCodes, rowIndex, rowIndex-1);
			prepareTable();
		}
	}

	private class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(ancv.getSubjectSection().getBtnCreateSubject())
					|| e.getSource().equals(ancv.getSubjectSection().getTxtSubjectCode())
					|| e.getSource().equals(ancv.getSubjectSection().getTxtSubjectName())){
				processCreateSubject();
			}else if(e.getSource().equals(ancv.getCourseSection().getBtnAdd())
					|| (e.getSource().equals(ancv.getCourseSection().getTxtSubjectCode())
							&& ancv.getCourseSection().getBtnAdd().isEnabled())){
				processAddSubject();
			}else if(e.getSource().equals(ancv.getCourseSection().getBtnCreateCourse())){
				processCreateCourse();
			}else if(e.getSource().equals(ancv.getCourseSection().getComboMajor())){
				comboMajorChosen();
			}
		}
	}
	
	private void processCreateSubject(){
		this.warnings.clear();
		
		String dept_id = this.ancv.getSubjectSection().getComboBox().getSelectedItem().toString();
		String sub_digit = this.ancv.getSubjectSection().getTxtSubjectCode().getText();
		String sub_name = this.ancv.getSubjectSection().getTxtSubjectName().getText();
		
		if(Checker.checkRequired(sub_digit)){
			this.warnings.add("Please enter subject code");
		}else if(!sub_digit.matches("\\d{5}")){
			this.warnings.add("Subject Code must have exactly 5 digits!");
		}
		
		String sub_code = dept_id+"-"+sub_digit;
		
		if(Checker.checkRequired(sub_name)){
			this.warnings.add("Please enter subject name");
		}else if(sub_name.length()>100){
			this.warnings.add("Subject Name is too LONG!");
		}
		
		if(dept_id.equals("BChE")||dept_id.equals("FT")||dept_id.equals("MFT"))
			dept_id = "ChE";
		else if(dept_id.equals("BioMET"))
			dept_id = "McE";
		else if(dept_id.equals("EG"))
			dept_id = "Geol";
		else if(dept_id.equals("ESP"))
			dept_id = "E";
		else if(dept_id.equals("PGT")||dept_id.equals("MSTF"))
			dept_id = "TE";
		else if(dept_id.equals("Ar"))
			dept_id = "Met";
		
		if(this.warnings.size()==0){
			if(SubjectDao.saveSubject(sub_code, sub_name, dept_id)){
				JOptionPane.showMessageDialog(ancv, "The New Subject has been successfully created", "Success", JOptionPane.INFORMATION_MESSAGE);
				this.ancv.getSubjectSection().getTxtSubjectCode().setText(null);
				this.ancv.getSubjectSection().getTxtSubjectName().setText(null);
			}else
				JOptionPane.showMessageDialog(ancv, "Error Saving Subject in Database!\n"
						+ "Duplicate Subject Code is possible", "Error", JOptionPane.ERROR_MESSAGE);
		}else{
			String message = "";
			for(String e:this.warnings)
				message = message+e+"\n";
			JOptionPane.showMessageDialog(this.ancv, message,"Warnings",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	protected void processCreateCourse() {
		this.warnings.clear();
		Course c = new Course();
		
		String start = this.ancv.getCourseSection().getTxtAcadYearStart().getText();
		String end = this.ancv.getCourseSection().getTxtAcadYearEnd().getText();
		if(Checker.checkRequired(start) || Checker.checkRequired(end)){
			this.warnings.add("Academic Year is incomplete");
		}else if(!(start.matches("\\d{4}")&&end.matches("\\d{4}"))){
			this.warnings.add("Academic Year (Start or End) must have 4 digits each");
		}else if(start.compareTo(end)!=-1){
			this.warnings.add("Academic Year (Start) must be one year earlier than Academic Year (End)!");
		}else{
			c.setAcademic_year(start+"-"+end);
		}
		
		temp = this.ancv.getCourseSection().getComboMajor().getSelectedItem().toString();
		c.setMajor_code(temp);
		
		if("primary".equals(MajorDao.getMajorType(temp)))
			c.setYear(this.ancv.getCourseSection().getComboYear().getSelectedIndex()+1+"");
		else if("higher".equals(MajorDao.getMajorType(temp)))
			c.setYear(this.ancv.getCourseSection().getComboYear().getSelectedIndex()+7+"");
		else{
			if(temp.equals("D.F.T")||temp.equals("EIA/EMS")||temp.equals("DESP"))
				c.setYear(7+"");
			else
				c.setYear(8+"");
		}
		
		c.setSemester(this.ancv.getCourseSection().getSpinner().getValue().toString());
		
		if(this.subCodes.isEmpty() || this.subCodes.size()==0)
			this.warnings.add("Please Add Subjects");
		else if(this.subCodes.size()<2){
			this.warnings.add("There should have at least 2 subjects in one course");
		}else{
			c.setSubjects(Usage.concatSubCodes(this.subCodes));
		}
		
		if(this.warnings.size()==0){
			if(CourseDao.doesCourseExist(c)){
			int ret = JOptionPane.showConfirmDialog(ancv, "The Course for\n"
					+ "Academic Year : "+ c.getAcademic_year()+"\n"
					+ "Major : "+ c.getMajor_code()+"\n"
					+ "Year : "+ c.getYear()+"\n"
					+ "Major : "+ c.getSemester()+"\n"
					+ "already exist!\n\n"
					+ "Replacing will erase mark contents related to the former course.\n"
					+ "Do You Want To Replace?\n"
					, "Duplicate Course", JOptionPane.YES_NO_OPTION);
			
			if(ret == JOptionPane.YES_OPTION){
				if(CourseDao.deleteCourse(c) && CourseDao.saveCourse(c)){
					JOptionPane.showMessageDialog(ancv, "The new course has been successfully created", "Success", JOptionPane.INFORMATION_MESSAGE);
					reset();
					prepareTable();
				}else
					JOptionPane.showMessageDialog(ancv, "Error Creating Course in Database!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			}else{
				if(CourseDao.saveCourse(c)){
					JOptionPane.showMessageDialog(ancv, "The new course has been successfully created", "Success", JOptionPane.INFORMATION_MESSAGE);
					reset();
					prepareTable();
				}else
					JOptionPane.showMessageDialog(ancv, "Error Creating Course in Database!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			String message = "";
			for(String e:this.warnings)
				message = message+e+"\n";
			JOptionPane.showMessageDialog(this.ancv, message,"Warnings",JOptionPane.WARNING_MESSAGE);
		}
				
	}
	
	private void reset(){
		this.ancv.getCourseSection().getTxtAcadYearStart().setText(null);
		this.ancv.getCourseSection().getTxtAcadYearEnd().setText(null);
		this.ancv.getCourseSection().getTxtSubjectCode().setText(null);
		this.ancv.getCourseSection().getTxtSubjectName().setText(null);
		this.ancv.getCourseSection().getBtnAdd().setEnabled(false);
		this.subCodes.clear();
	}

	protected void comboMajorChosen() {
		String majCode = this.ancv.getCourseSection().getComboMajor().getSelectedItem().toString();
		String majName = MajorDao.getMajorCodeAndName().get(majCode);
		this.ancv.getCourseSection().getTxtMajorName().setText(majName);
		
		String[] primary = {"First","Second","Third","Fourth","Fifth","Sixth","Dip","MS","ME","PhD"};
		String[] higher = {"Dip","MS","ME","PhD"};
		
		String majType = MajorDao.getMajorType(majCode);
		
		if(this.oldMajType == null || !majType.equals(this.oldMajType)){
			
			this.ancv.getCourseSection().getComboYear().setEnabled(true);
			this.ancv.getCourseSection().getComboYear().removeAllItems();
			
			if("primary".equals(majType))
				for(int i=0;i<primary.length;i++)
					this.ancv.getCourseSection().getComboYear().addItem(primary[i]);
			else if("higher".equals(majType))
				for(int i=0;i<higher.length;i++)
					this.ancv.getCourseSection().getComboYear().addItem(higher[i]);
			else
				this.ancv.getCourseSection().getComboYear().setEnabled(false);
		}
		
		this.oldMajType = majType;
	}

	private void processAddSubject(){
		String co = this.ancv.getCourseSection().getTxtSubjectCode().getText();
		
		if(this.subCodes.contains(co)){
			JOptionPane.showMessageDialog(ancv, "The subject you're trying to include already exist!", "Duplicate Subject", JOptionPane.WARNING_MESSAGE);
		}else{	
			this.subCodes.add(co);
			prepareTable();
		}
	}
	
	private void prepareTable(){
		this.ancv.getCourseSection().getTableModel().removeAll();
		
		Integer no = 1;
		for(String subCode:this.subCodes){
			Object[] o = new Object[5];
			o[0] = no++;
			o[1] = subCode;
			o[2] = SubjectDao.getSubjectCodeAndName().get(subCode);
			o[3] = new String("Remove");
			o[4] = new String(" ^^^^");
			this.ancv.getCourseSection().getTableModel().addRecord(o);
		}
	}
}
