package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MenuPane;

public class MenuController {
	
	private MainController mc;
	private MenuPane myMenuPane;
	
	public MenuController(MainController mc, MenuPane myMenuPane) {
		this.mc = mc;
		this.myMenuPane = myMenuPane;
		
		this.myMenuPane.getBtnHome().addActionListener(new MyButtonListener());
		this.myMenuPane.getBtnStudentRegisteration().addActionListener(new MyButtonListener());
		this.myMenuPane.getBtnShowAllStudents().addActionListener(new MyButtonListener());
		this.myMenuPane.getBtnStudentInfo().addActionListener(new MyButtonListener());
		this.myMenuPane.getBtnAddNewCourse().addActionListener(new MyButtonListener());
		this.myMenuPane.getBtnAcademicYear().addActionListener(new MyButtonListener());
		this.myMenuPane.getBtnAddNewResults().addActionListener(new MyButtonListener());
		this.myMenuPane.getBtnShowResults().addActionListener(new MyButtonListener());
	}
	
	public class MyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(myMenuPane.getBtnHome()) && MainController.MENU_NO!=1)
				mc.prepareHomeView();
			else if(e.getSource().equals(myMenuPane.getBtnStudentRegisteration()) && MainController.MENU_NO!=2)
				mc.prepareRegistration();
			else if(e.getSource().equals(myMenuPane.getBtnShowAllStudents()) && MainController.MENU_NO!=3)
				mc.prepareShowAllStudents();
			else if(e.getSource().equals(myMenuPane.getBtnStudentInfo()) && MainController.MENU_NO!=4)
				mc.prepareStudentInfo();
			else if(e.getSource().equals(myMenuPane.getBtnAddNewCourse()) && MainController.MENU_NO!=5)
				mc.prepareAddNewCourse();
			else if(e.getSource().equals(myMenuPane.getBtnAcademicYear()) && MainController.MENU_NO!=6)
				mc.prepareAcademicYear();
			else if(e.getSource().equals(myMenuPane.getBtnAddNewResults()) && MainController.MENU_NO!=7)
				mc.prepareAddNewResults();
			else if(e.getSource().equals(myMenuPane.getBtnShowResults()) && MainController.MENU_NO!=8)
				mc.prepareResultView();
		}

	}
}
