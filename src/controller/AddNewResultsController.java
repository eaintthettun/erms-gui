package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Course;
import database.AttendDao;
import database.CourseDao;
import database.MajorDao;
import utils.Usage;
import view.AddNewResultsView;

public class AddNewResultsController {
	
	private AddNewResultsView v;
	private String acy,maj;
	private int yr,sem;
	private String oldMajType;
	
	private ArrayList<String> listSubs,rollNos;
	
	public AddNewResultsController(AddNewResultsView view){
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
		
		if("primary".equals(majType))
			for(int i=0;i<primary.length;i++)
				v.getExamSection().getComboYear().addItem(primary[i]);
		else if("higher".equals(majType))
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
	
	private class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(v.getExamSection().getComboMajor()))
				prepareComboYear();
			prepare();
		}
		
	}

}
