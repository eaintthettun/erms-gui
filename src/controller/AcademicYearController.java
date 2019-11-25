package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.AttendDao;
import database.MajorDao;
import database.StudentDao;
import model.Course;
import utils.Checker;
import utils.MyConstants;
import view.AcademicYearView;

public class AcademicYearController {
	
	private AcademicYearView ayv;
	
	private String ac,maj,oldMajType;
	private int yr;
	
	private ArrayList<String> warnings;
	
	public AcademicYearController(AcademicYearView v){
		this.ayv = v;
		
		this.warnings = new ArrayList<String>();
		
		this.ayv.getRegistrationSection().getBtnAdd().setEnabled(false);
		
		prepareComboYear();
		prepareTable();
		
		this.ayv.getAcademicSection().getComboAcdYr().addActionListener(new MyActionListener());
		this.ayv.getAcademicSection().getComboMajor().addActionListener(new MyActionListener());
		this.ayv.getAcademicSection().getComboYear().addActionListener(new MyActionListener());
		
		this.ayv.getRegistrationSection().getTxtRollNo().addActionListener(new MyActionListener());
		this.ayv.getRegistrationSection().getBtnAdd().addActionListener(new MyActionListener());
		
		this.ayv.getRegistrationSection().getTxtEntranceID().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String code = AcademicYearController.this.ayv.getRegistrationSection().getTxtEntranceID().getText();
				if(Checker.isID(code)){
					ArrayList<String> ids = StudentDao.getAllStdIDs();
					if(ids != null && !ids.isEmpty() && ids.contains(code)){
						AcademicYearController.this.ayv.getRegistrationSection().getTxtStudentName().setText(StudentDao.getStudentByID(code).getStd_name());
						AcademicYearController.this.ayv.getRegistrationSection().getBtnAdd().setEnabled(true);
					}
				}else{
					AcademicYearController.this.ayv.getRegistrationSection().getTxtStudentName().setText(null);
					AcademicYearController.this.ayv.getRegistrationSection().getBtnAdd().setEnabled(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.ayv.getRegistrationSection().getTableAddedStds().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				tableClicked();
			}
			
		});
		
	}
	
	private void prepareComboYear() {
		String majCode = ayv.getAcademicSection().getComboMajor().getSelectedItem().toString();
		
		String[] primary = {"First","Second","Third","Fourth","Fifth","Sixth","Dip","MS","ME","PhD"};
		String[] higher = {"Dip","MS","ME","PhD"};
		
		String majType = MajorDao.getMajorType(majCode);
		
		if(this.oldMajType == null || !majType.equals(this.oldMajType)){
			ayv.getAcademicSection().getComboYear().setEnabled(true);
			ayv.getAcademicSection().getComboYear().removeAllItems();
		if("primary".equals(majType))
			for(int i=0;i<primary.length;i++)
				ayv.getAcademicSection().getComboYear().addItem(primary[i]);
		else if("higher".equals(majType))
			for(int i=0;i<higher.length;i++)
				ayv.getAcademicSection().getComboYear().addItem(higher[i]);
		else
			ayv.getAcademicSection().getComboYear().setEnabled(false);
		}
		
		this.oldMajType = majType;
	}

	private void prepareTable(){
		this.ac = this.ayv.getAcademicSection().getComboAcdYr().getSelectedItem().toString();
		this.maj = this.ayv.getAcademicSection().getComboMajor().getSelectedItem().toString();
		
		if(!this.ayv.getAcademicSection().getComboYear().isEnabled()){
			if(this.maj.equals("D.F.T")||this.maj.equals("EIA/EMS")||this.maj.equals("DESP"))
				this.yr = 7;
			else
				this.yr = 8;
		}else{
			if("higher".equals(MajorDao.getMajorType(maj)))
				this.yr = this.ayv.getAcademicSection().getComboYear().getSelectedIndex()+1+6;
			if("primary".equals(MajorDao.getMajorType(maj)))
				this.yr = this.ayv.getAcademicSection().getComboYear().getSelectedIndex()+1;
		}
		
		ArrayList<Course> list = AttendDao.getAttendByAcMajYr(this.ac, this.maj, this.yr+"");
		
		this.ayv.getRegistrationSection().getTableModel().removeAll();
		
		Integer no = 1;
		for(Course c:list){
			Object[] o = new Object[5];
			o[0] = no++;
			o[1] = c.getSemester();
			o[2] = c.getSubjects();
			o[3] = StudentDao.getStudentByID(c.getSemester()).getStd_name();
			o[4] = new String("Remove");
			this.ayv.getRegistrationSection().getTableModel().addRecord(o);
		}

	}
	
	protected void tableClicked() {

		int rowIndex = this.ayv.getRegistrationSection().getTableAddedStds().getSelectedRow();
		int colIndex = this.ayv.getRegistrationSection().getTableAddedStds().getSelectedColumn();
		
		if(colIndex == 4){
			
			int ret = JOptionPane.showConfirmDialog(null, "Removing added student from this academic year "
					+ "will cause to erase related results or achievements.\n"
					+ "Are You Sure You Want To Remove?", "Remove Student", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(ret == JOptionPane.YES_OPTION){
				Object[] recd = this.ayv.getRegistrationSection().getTableModel().getSelectedRecord(rowIndex);
				if(AttendDao.deleteAttend(this.ac, recd[1].toString()))
					prepareTable();
				else
					JOptionPane.showMessageDialog(this.ayv, "Error Removing student","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void processAddStudent(){
		
		this.warnings.clear();
		
		String rollno = this.ayv.getRegistrationSection().getTxtRollNo().getText();
		String stdid = this.ayv.getRegistrationSection().getTxtEntranceID().getText();
		
		String rollNoFull = "";
		switch(this.yr){
		case 1: rollNoFull+="I.";break;
		case 2: rollNoFull+="II.";break;
		case 3: rollNoFull+="III.";break;
		case 4: rollNoFull+="IV.";break;
		case 5: rollNoFull+="V.";break;
		case 6: rollNoFull+="VI.";break;
		case 7: rollNoFull+="Dip.";break;
		case 8: rollNoFull+="M.S.";break;
		case 9: rollNoFull+="M.E.";break;
		case 10: rollNoFull+="PhD.";break;
		default:standAloneMaj(rollNoFull);
		}
		
		rollNoFull+=this.maj+" - "+rollno;
		
		if(Checker.checkRequired(rollno)){
			this.warnings.add(MyConstants.msgRequired("New Roll No"));
		}else if(!rollno.matches("\\d{1,}")){
			this.warnings.add("Please Enter only digits for Student Roll No.\n"
					+ "The prefix of roll no will be correctly taken care of :)");
		}else if(!Checker.isMajorCompatible(stdid, this.maj)){
			this.warnings.add("Student Major mismatch!\n"
					+ "You can't register "+this.ayv.getRegistrationSection().getTxtStudentName().getText()
					+ " in this field.");
		}else if(AttendDao.getAllStdIDsByAc(this.ac).contains(stdid)){
			this.warnings.add(this.ayv.getRegistrationSection().getTxtStudentName().getText()
					+" has been registered for "+this.ac+" academic year!\n"
					+ "Please check your registration.");
		}else if(AttendDao.getAllStdRollNosBy(this.ac, this.maj, this.yr+"").contains(rollNoFull)){
			this.warnings.add("Roll No : "+rollNoFull+" has been taken!\n"
					+ "Please delete the existing Roll No in this academic year or choose a new one.");
		}
		
		if(this.warnings.isEmpty()){
			Course c = new Course();
			
			c.setAcademic_year(this.ac);
			c.setMajor_code(this.maj);
			c.setYear(this.yr+"");
			c.setSemester(stdid);
			c.setSubjects(rollNoFull);
			
			if(AttendDao.saveAttend(c))
				prepareTable();
			else
				JOptionPane.showMessageDialog(this.ayv, "Error adding student in database", "Error", JOptionPane.ERROR_MESSAGE);
		}else{
			String message = "";
			for(String e:this.warnings)
				message = message+e+"\n";
			JOptionPane.showMessageDialog(this.ayv, message,"Warnings",JOptionPane.WARNING_MESSAGE);
		}
	}

	private void standAloneMaj(String rollNoFull) {
		if(!this.ayv.getAcademicSection().getComboYear().isEnabled()){
			if(this.maj.equals("EIA/EMS"))
				rollNoFull+="Dip.";
			if(this.maj.equals("EAM"))
				rollNoFull+="M.S.";
		}
	}

	private class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(ayv.getRegistrationSection().getBtnAdd())
					|| (e.getSource().equals(ayv.getRegistrationSection().getTxtRollNo()) 
					&& ayv.getRegistrationSection().getBtnAdd().isEnabled())){
				processAddStudent();
			}
			else if(e.getSource().equals(ayv.getAcademicSection().getComboYear())){
				prepareTable();
			}
			else{
				prepareComboYear();
				prepareTable();
			}
		}
	}

}
