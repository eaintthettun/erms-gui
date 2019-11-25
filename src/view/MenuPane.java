package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Font;

public class MenuPane extends JPanel {
	private JButton btnHome;
	private JButton btnStudentRegisteration;
	private JButton btnShowAllStudents;
	private JButton btnAddNewResults;
	private JButton btnShowResults;
	private JButton btnAddNewCourse;
	private JButton btnAcademicYear;
	private JButton btnStudentInfo;

	/**
	 * Create the panel.
	 */
	public MenuPane() {
		setBorder(new MatteBorder(20, 40, 20, 40, Color.decode("#cccccc")));
		setBackground(Color.decode("#cccccc"));
		setLayout(new GridLayout(0, 1, 20, 20));
		setAutoscrolls(true);
		add(getBtnHome());
		add(getBtnStudentRegisteration());
		add(getBtnShowAllStudents());
		add(getBtnStudentInfo());
		add(getBtnAddNewCourse());
		add(getBtnAcademicYear());
		add(getBtnAddNewResults());
		add(getBtnShowResults());

	}

	public JButton getBtnHome() {
		if (btnHome == null) {
			btnHome = new JButton("Home");
			designButton(btnHome);
		}
		return btnHome;
	}
	public JButton getBtnStudentRegisteration() {
		if (btnStudentRegisteration == null) {
			btnStudentRegisteration = new JButton("Student Registration");
			designButton(btnStudentRegisteration);
		}
		return btnStudentRegisteration;
	}
	public JButton getBtnShowAllStudents() {
		if (btnShowAllStudents == null) {
			btnShowAllStudents = new JButton("Show All Students");
			designButton(btnShowAllStudents);
		}
		return btnShowAllStudents;
	}
	public JButton getBtnStudentInfo() {
		if (btnStudentInfo == null) {
			btnStudentInfo = new JButton("Search Student");
			designButton(btnStudentInfo);
		}
		return btnStudentInfo;
	}
	public JButton getBtnAddNewResults() {
		if (btnAddNewResults == null) {
			btnAddNewResults = new JButton("Add New Results");
			designButton(btnAddNewResults);
		}
		return btnAddNewResults;
	}
	public JButton getBtnShowResults() {
		if (btnShowResults == null) {
			btnShowResults = new JButton("Show Results");
			designButton(btnShowResults);
		}
		return btnShowResults;
	}
	public JButton getBtnAddNewCourse() {
		if (btnAddNewCourse == null) {
			btnAddNewCourse = new JButton("Add New Course");
			designButton(btnAddNewCourse);
		}
		return btnAddNewCourse;
	}
	public JButton getBtnAcademicYear() {
		if (btnAcademicYear == null) {
			btnAcademicYear = new JButton("Academic Year");
			designButton(btnAcademicYear);
		}
		return btnAcademicYear;
	}
	
	/*
	 * Own Code
	 */
	
	private void designButton(JButton b){
		b.setFocusPainted(false);
		b.setFont(new Font("Century Gothic", Font.BOLD, 17));
		b.setForeground(Color.WHITE);
		b.setBackground(Color.decode("#103443"));
	}
}
