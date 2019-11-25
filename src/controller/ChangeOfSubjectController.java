package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import model.ChangeOfSub;
import database.ChangeOfSubDao;
import database.SubjectDao;
import utils.Checker;
import utils.MyConstants;
import view.ChangeOfSubjectView;

public class ChangeOfSubjectController {
	
	private ChangeOfSubjectView cosv;
	
	public ChangeOfSubjectController(ChangeOfSubjectView cosv){
		this.cosv = cosv;
		comboStatus();
		prepareData();
		
		this.cosv.getComboBox().addActionListener(new MyActionListener());
		
		this.cosv.getTxtEndYear().addActionListener(new MyActionListener());
		this.cosv.getTxtNewSubjectName().addActionListener(new MyActionListener());
		this.cosv.getTxtStartYear().addActionListener(new MyActionListener());
		this.cosv.getTxtSubjectCode().addActionListener(new MyActionListener());
		this.cosv.getBtnEnter().addActionListener(new MyActionListener());
		
		this.cosv.getTxtSubjectCode().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String code = ChangeOfSubjectController.this.cosv.getTxtSubjectCode().getText();
				if(Checker.isSubjectCode(code)){
					HashMap<String, String> subCN = SubjectDao.getSubjectCodeAndName();
					if(subCN != null && subCN.get(code)!=null){
						ChangeOfSubjectController.this.cosv.getTxtSubjectName().setText(subCN.get(code));
						ChangeOfSubjectController.this.cosv.getBtnEnter().setEnabled(true);
					}
				}else{
					ChangeOfSubjectController.this.cosv.getTxtSubjectName().setText(null);
					ChangeOfSubjectController.this.cosv.getBtnEnter().setEnabled(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		this.cosv.getTableStd().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				tableClicked();
			}
			
		});
	}

	protected void tableClicked() {
		int rowIndex = cosv.getTableStd().getSelectedRow();
		int colIndex = cosv.getTableStd().getSelectedColumn();
		
		if(colIndex == 5){
			ChangeOfSub cos = new ChangeOfSub();
			
			cos.setSub_code(cosv.getTableStd().getValueAt(rowIndex, 1).toString());
			cos.setStart_year(cosv.getTableStd().getValueAt(rowIndex, 2).toString());
			cos.setEnd_year(cosv.getTableStd().getValueAt(rowIndex, 3).toString());
			cos.setSub_name(cosv.getTableStd().getValueAt(rowIndex, 4).toString());
			
			ChangeOfSubDao.deleteChangeOfSub(cos);
			
			prepareData();
			
		}
	}

	private void prepareData() {
		cosv.getTableModel().removeAll();
		
		ArrayList<ChangeOfSub> list = ChangeOfSubDao.getAllChangeOfSub();
		
		int i=0;
		for(ChangeOfSub l:list){
			Object o[] = new Object[6];
			o[0] = ++i;
			o[1] = l.getSub_code();
			o[2] = l.getStart_year();
			o[3] = l.getEnd_year();
			o[4] = l.getSub_name();
			o[5] = "Remove";
			cosv.getTableModel().addRecord(o);
		}
	}

	private void comboStatus(){
		if(cosv.getComboBox().getSelectedIndex() == 0)
			cosv.getTxtEndYear().setEnabled(false);
		else
			cosv.getTxtEndYear().setEnabled(true);
	}
	
	protected void processEnter(){
		ArrayList<String> warning = new ArrayList<String>();
		String temp;
		
		ChangeOfSub cos =new ChangeOfSub();
		cos.setSub_code(cosv.getTxtSubjectCode().getText());
		
		temp = cosv.getTxtStartYear().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("Start Year"));
		}else if(!temp.matches("\\d{4}")){
			warning.add("Start year must be 4-digit");
		}else
			cos.setStart_year(temp);
		
		if(cosv.getComboBox().getSelectedIndex() == 0)
			cos.setEnd_year("Present");
		else{
			temp = cosv.getTxtEndYear().getText();
			if(Checker.checkRequired(temp)){
				warning.add(MyConstants.msgRequired("End Year"));
			}else if(!temp.matches("\\d{4}")){
				warning.add("End year must be 4-digit");
			}else
				cos.setEnd_year(temp);
		}
		
		if(cos.getStart_year()!=null && cos.getEnd_year()!=null && cos.getStart_year().compareTo(cos.getEnd_year())>=0)
			warning.add("Start Year must be earlier than End Year");
		
		temp = cosv.getTxtNewSubjectName().getText();
		if(Checker.checkRequired(temp)){
			warning.add(MyConstants.msgRequired("New Subject Name"));
		}else if(Checker.checkTextLength(temp, 100)){
			warning.add("New Subject Name is too long");
		}else
			cos.setSub_name(temp);
		
		if(warning.isEmpty() && warning.size()==0){
			
			if(!ChangeOfSubDao.saveChangeOfSub(cos))
				JOptionPane.showMessageDialog(this.cosv,"Error Saving Information In Database","Error Message",JOptionPane.ERROR_MESSAGE);
			else{
				JOptionPane.showMessageDialog(this.cosv, "Sucessfully Saved","Information Message",JOptionPane.INFORMATION_MESSAGE);
				clearAllTextfield();
			}
		}else{

			String message = "";
			
			for(String e:warning)
				message = message+e+"\n";
			
			JOptionPane.showMessageDialog(this.cosv, message,"Warnings",JOptionPane.WARNING_MESSAGE);		
		}
		
		warning.clear();
	}
	
	private void clearAllTextfield() {
		
		cosv.getTxtEndYear().setText(null);
		cosv.getTxtNewSubjectName().setText(null);
		cosv.getTxtStartYear().setText(null);
		cosv.getTxtSubjectCode().setText(null);
		cosv.getTxtSubjectName().setText(null);
		
		cosv.getBtnEnter().setEnabled(false);
	}

	private class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(cosv.getComboBox())){
				comboStatus();
			}else if(cosv.getBtnEnter().isEnabled()){
				processEnter();
				prepareData();
			}
		}
		
	}
}
