package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Course;
import model.Marks;
import database.AttendDao;
import database.CourseDao;
import database.MajorDao;
import database.MarksDao;
import database.StudentDao;
import utils.Checker;
import utils.Usage;
import view.MultipleResultTab;
import view.SingleResultTab;

public class ShowResultsController {
	
	private SingleResultTab srst;
	private MultipleResultTab v;
	private String acy,maj,oldMajType;
	private int yr,sem;
	
	private ArrayList<String> listSubs,rollNos;
	private ArrayList<Marks> mks;
	
	private String stdIdForRes;
	
	public ShowResultsController(String stdId, SingleResultTab tab){
		this.srst = tab;
		setStdIdForRes(stdId);
		
		this.srst.getStudentSection().getBtnAdd().setEnabled(false);
		this.srst.getStudentSection().getBtnAdd().addActionListener(new MySingleRsAction());
		this.srst.getStudentSection().getTxtEntranceID().addActionListener(new MySingleRsAction());
		
		this.srst.getStudentSection().getTxtEntranceID().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String code = srst.getStudentSection().getTxtEntranceID().getText();
				if(Checker.isID(code)){
					ArrayList<String> ids = StudentDao.getAllStdIDs();
					if(ids != null && !ids.isEmpty() && ids.contains(code)){
						srst.getStudentSection().getTxtStudentName().setText(StudentDao.getStudentByID(code).getStd_name());
						srst.getStudentSection().getBtnAdd().setEnabled(true);
					}
				}else{
					srst.getStudentSection().getTxtStudentName().setText(null);
					srst.getStudentSection().getBtnAdd().setEnabled(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.srst.getStudentSection().getTxtEntranceID().setText(stdId);
		if(stdId!=null)
			this.srst.getStudentSection().getTxtStudentName().setText(
					StudentDao.getStudentByID(stdId).getStd_name());
		prepareAcademicRecords();
	}
	
	public ShowResultsController(SingleResultTab tab, MultipleResultTab view){
		this(null,tab);
		this.v = view;
		prepareComboYear();
		prepare();
		v.getExamSection().getComboAcdYr().addActionListener(new MyActionListener());
		v.getExamSection().getComboMajor().addActionListener(new MyActionListener());
		v.getExamSection().getComboYear().addActionListener(new MyActionListener());
		v.getExamSection().getSpinner().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				prepare();
			}
		});
		
	}
	
	private void prepareComboYear(){
		String majCode = v.getExamSection().getComboMajor().getSelectedItem().toString();
		String[] primary = {"First","Second","Third","Fourth","Fifth","Sixth","Dip","MS","ME","PhD"};
		String[] higher = {"Dip","MS","ME","PhD"};
		
		String majType = MajorDao.getMajorType(majCode);
		
		if(this.oldMajType == null || !majType.equals(this.oldMajType)){
			v.getExamSection().getComboYear().setEnabled(true);
			v.getExamSection().getComboYear().removeAllItems();
		if("primary".equals(MajorDao.getMajorType(majCode)))
			for(int i=0;i<primary.length;i++)
				v.getExamSection().getComboYear().addItem(primary[i]);
		else if("higher".equals(MajorDao.getMajorType(majCode)))
			for(int i=0;i<higher.length;i++)
				v.getExamSection().getComboYear().addItem(higher[i]);
		else
			v.getExamSection().getComboYear().setEnabled(false);
		}
		
		this.oldMajType = majType;
	}

	private void prepare() {
		acy = v.getExamSection().getComboAcdYr().getSelectedItem().toString();
		maj = v.getExamSection().getComboMajor().getSelectedItem().toString();
		
		if(!v.getExamSection().getComboYear().isEnabled()){
			if(this.maj.equals("D.F.T")||this.maj.equals("EIA/EMS")||this.maj.equals("DESP"))
				this.yr = 7;
			else
				this.yr = 8;
		}else{
			if("higher".equals(MajorDao.getMajorType(maj)))
				this.yr = v.getExamSection().getComboYear().getSelectedIndex()+1+6;
			if("primary".equals(MajorDao.getMajorType(maj)))
				this.yr = v.getExamSection().getComboYear().getSelectedIndex()+1;
		}
		
		sem = (int)v.getExamSection().getSpinner().getValue();
		
		Course c = new Course();
		c.setAcademic_year(acy);
		c.setMajor_code(maj);
		
		if("primary".equals(MajorDao.getMajorType(maj)))
			c.setYear(yr+"");
		else if("higher".equals(MajorDao.getMajorType(maj)))
			c.setYear(yr+6+"");
		else
			c.setYear(yr+"");
		
		c.setSemester(sem+"");
		
		listSubs = Usage.separateSubCodes(CourseDao.getSubsByAcyMajYrSem(acy, maj, c.getYear(), sem+""));
		rollNos = AttendDao.getAllStdRollNosBy(acy, maj, c.getYear());
		
		v.getExamSection().getSubjectListPane().setListOfSubCodes(listSubs);
		v.getExamSection().getSubjectListPane().setAcy(acy);
		v.getExamSection().getSubjectListPane().prepareSubjects();
		
		v.getAddMarkSection().setListOfSubCodes(listSubs);
		v.getAddMarkSection().setListOfRollNos(rollNos);
		
		v.getAddMarkSection().setCourse(c);
		
		v.getAddMarkSection().prepareStudents();
		
	}
	
	private class MySingleRsAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(srst.getStudentSection().getBtnAdd().isEnabled() && 
					(e.getSource().equals(srst.getStudentSection().getBtnAdd()) ||
							e.getSource().equals(srst.getStudentSection().getTxtEntranceID()))){
				setStdIdForRes(srst.getStudentSection().getTxtEntranceID().getText());
				prepareAcademicRecords();
				srst.getDetailSection().setRecords(null);
				srst.getDetailSection().prepareDetails();
			}
			
		}
		
	}
	
	protected void prepareAcademicRecords(){
		
		mks = this.stdIdForRes==null ? null:MarksDao.getAcademicRecordsByID(this.stdIdForRes);
		this.srst.getAcrSection().setRecords(mks);
		this.srst.getAcrSection().prepareRecords();
		
		if(!(mks==null || mks.isEmpty())){
			this.srst.getAcrSection().getTableAddedResults().addMouseListener(new MouseListener() {
				
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
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					tableClicked();
				}
			});
		}
	}
	
	protected void tableClicked(){
		int rowIndex = this.srst.getAcrSection().getTableAddedResults().getSelectedRow();
		int colIndex = this.srst.getAcrSection().getTableAddedResults().getSelectedColumn();
		
		if(colIndex == 8){
			// Delete Result
			int ret = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected record", "Delete Result", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(ret == JOptionPane.YES_OPTION){
				Marks mk = this.mks.get(rowIndex);
				if(MarksDao.deleteMarksByIDonAcy(mk.getAcademic_year(), mk.getStd_id(), mk.getSemester())){
					prepareAcademicRecords();
					if(mk.equals(this.srst.getDetailSection().getRecords())){
						this.srst.getDetailSection().setRecords(null);
						this.srst.getDetailSection().prepareDetails();
					}
				}else
					JOptionPane.showMessageDialog(null, "Unable to delete the selected record in database", 
							"Database Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(colIndex == 7){
			this.srst.getDetailSection().setRecords(mks.get(rowIndex));
			this.srst.getDetailSection().setAcy(acy);
			this.srst.getDetailSection().prepareDetails();
		}
	}
	
	private class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(v.getExamSection().getComboMajor()))
				prepareComboYear();
			prepare();
		}
		
	}
	
	public void setStdIdForRes(String stdIdForRes){
		this.stdIdForRes = stdIdForRes;
	}
	public String getStdIdForRes(){
		return this.stdIdForRes;
	}

}
