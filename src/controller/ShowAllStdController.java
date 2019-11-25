package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Student;
import database.FatherDao;
import database.MotherDao;
import database.StudentDao;
import utils.OfficeUsage;
import utils.Usage;
import view.MyDialog;
import view.ShowAllStudentsView;

public class ShowAllStdController {
	
	private ShowAllStudentsView sasv;
	private MainController mc;
	private MyDialog dialog;
	private int row,col;
	private String id;

	public ShowAllStdController(ShowAllStudentsView v,MainController c) {
		this.sasv = v;
		this.mc = c;
		prepareTable();
		this.sasv.getTableStd().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				processClick();
			}
		});
		this.sasv.getComboBox().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prepareTable();
			}
		});
		
		Usage.setIcon(this.sasv.getBtnExportAllTo(), "excel");
		Usage.setIcon(this.sasv.getBtnImportFromExcel(), "excel");
		this.sasv.getBtnExportAllTo().addMouseListener(new ButtonMouseListener(this.sasv.getBtnExportAllTo()));
		this.sasv.getBtnImportFromExcel().addMouseListener(new ButtonMouseListener(this.sasv.getBtnImportFromExcel()));
		this.sasv.getBtnExportAllTo().addActionListener(new MyBtnListener());
		this.sasv.getBtnImportFromExcel().addActionListener(new MyBtnListener());
	}

	protected void processClick() {
		this.row = this.sasv.getTableStd().getSelectedRow();
		this.col = this.sasv.getTableStd().getSelectedColumn();
		id = sasv.getTableStd().getValueAt(row, 1).toString();
		if(col == 7)
			this.mc.prepareStudentUpdate(id);
		if(col == 8)
			Usage.prepareResult(id);
		if(col == 9){
			this.dialog = new MyDialog();
			this.dialog.setVisible(true);
			this.dialog.setLocationRelativeTo(this.sasv);
			this.dialog.getOkButton().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
					dialog.setVisible(false);
					Student std = StudentDao.getStudentByID(id);
					if(StudentDao.deleteStudentByID(id) & 
							FatherDao.deleteFatherByNrc(std.getFather_nrc()) &
							MotherDao.deleteMotherByNrc(std.getMother_nrc())) // Careful Here
						prepareTable();
					else
						JOptionPane.showMessageDialog(sasv, "Error deleting this student in database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			});
			this.dialog.getCancelButton().addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
					dialog.setVisible(false);
				}
			});
		}
	}

	private void prepareTable() {
		this.sasv.getTableModel().removeAll();
		ArrayList<Student> stds = StudentDao.getAllStudents();
		
		if(stds != null){
			
			switch(this.sasv.getComboBox().getSelectedIndex()){
			case 0:Collections.sort(stds, Student.compStdID);break;
			case 1:Collections.sort(stds, Student.compStdName);break;
			case 2:Collections.sort(stds, Student.compStdMajor);break;
			}
			
			Integer no = 1;
			for(Student std:stds){
				Object o[] = new Object[10];
				o[0] = no++;
				o[1] = std.getStd_id();
				o[2] = std.getStd_name();
				o[3] = std.getStd_nrc();
				o[4] = std.getMajor_code();
				o[5] = FatherDao.getFatherByNrc(std.getFather_nrc()).getFather_name();
				o[6] = std.getStd_phone();
				o[7] = new String("Details");
				o[8] = new String("Results");
				o[9] = new String("Delete");
				this.sasv.getTableModel().addRecord(o);
			}
			
		}
	}
	
	private class MyBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(sasv.getBtnExportAllTo()))
				processExportAll();
			else{
				processImportFromExcel();
			}
		}
		
	}

	private void processExportAll() {
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
			ArrayList<Student> stds = StudentDao.getAllStudents();
			if(OfficeUsage.exportAllStudentsToExcel(stds, trueFile)){
				JOptionPane.showMessageDialog(null, "Successfully Exported to Excel!", 
						"Export Success", JOptionPane.INFORMATION_MESSAGE);
			}else
				JOptionPane.showMessageDialog(null, "Unable to Export to Excel!", 
						"Export Failure", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void processImportFromExcel(){
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel Worksheet", "xls","xlsx");
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.addChoosableFileFilter(filter);
		
		int ret = jfc.showOpenDialog(null);
		if(ret == JFileChooser.APPROVE_OPTION){
			File f = jfc.getSelectedFile();
			
			HashMap<String,Object> info = OfficeUsage.importStdInfoFromOthers(f);
			String msg = "Total : " + (Integer)info.get("total");
			msg += "\nSuccess : " + (Integer)info.get("success");
			msg += "\nFailure : " + ((Integer)info.get("total") - (Integer)info.get("success"));
			String err = (String)info.get("error");
			msg += err.length() > 0 ? "\n\nErrors : \n"+err : "";
			JOptionPane.showMessageDialog(null, msg, 
						"Import Status", JOptionPane.INFORMATION_MESSAGE);
			prepareTable();
		}
	}
}
