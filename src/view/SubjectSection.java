package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.jgoodies.forms.layout.Sizes;

import controller.ButtonMouseListener;
import database.DepartmentDao;

public class SubjectSection extends JPanel {
	private JTextField txtSubjectCode;
	private JLabel lblEnterSubjectCode;
	private JLabel lblChooseDepartment;
	private JComboBox comboBox;
	private JLabel lblEnterFullSubName;
	private JTextField txtSubjectName;
	private JButton btnCreateSubject;
	private JLabel lblSeeAllSubjects;

	/**
	 * Create the panel.
	 */
	public SubjectSection() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Add New Subject : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(220, 220, 220));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("75dlu"),
				ColumnSpec.decode("max(107dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("100dlu"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("25dlu", false)), 0),
				}));
		add(getLblChooseDepartment(), "2, 2, right, fill");
		add(getComboBox(), "4, 2, fill, fill");
		add(getLblEnterSubjectCode(), "6, 2, right, fill");
		add(getTxtSubjectCode(), "8, 2, fill, fill");
		add(getLblEnterFullSubName(), "2, 4, right, fill");
		add(getTxtSubjectName(), "4, 4, 5, 1, fill, fill");
		add(getLblSeeAllSubjects(), "2, 6, left, center");
		add(getBtnCreateSubject(), "8, 6, right, fill");
	}

	public JTextField getTxtSubjectCode() {
		if (txtSubjectCode == null) {
			txtSubjectCode = new JTextField();
			txtSubjectCode.setForeground(Color.BLUE);
			txtSubjectCode.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			txtSubjectCode.setColumns(10);
		}
		return txtSubjectCode;
	}
	public JLabel getLblEnterSubjectCode() {
		if (lblEnterSubjectCode == null) {
			lblEnterSubjectCode = new JLabel("Enter Subject Code :  ");
			lblEnterSubjectCode.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblEnterSubjectCode;
	}
	public JLabel getLblChooseDepartment() {
		if (lblChooseDepartment == null) {
			lblChooseDepartment = new JLabel("Choose Department :  ");
			lblChooseDepartment.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblChooseDepartment;
	}
	public JComboBox getComboBox() {
		if (comboBox == null) {
			ArrayList<String> prefixes =DepartmentDao.getAllDeptIDs();
			prefixes.add("BChE");
			prefixes.add("BioMET");
			prefixes.add("EG");
			prefixes.add("FT");
			prefixes.add("MFT");
			prefixes.add("PGT");
			prefixes.add("ESP");
			prefixes.add("Ar");
			prefixes.add("MSTF");
			comboBox = new JComboBox(prefixes.toArray());
			comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return comboBox;
	}
	public JLabel getLblEnterFullSubName() {
		if (lblEnterFullSubName == null) {
			lblEnterFullSubName = new JLabel("Enter Full Subject Name :  ");
			lblEnterFullSubName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblEnterFullSubName;
	}
	public JTextField getTxtSubjectName() {
		if (txtSubjectName == null) {
			txtSubjectName = new JTextField();
			txtSubjectName.setForeground(Color.BLUE);
			txtSubjectName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			txtSubjectName.setColumns(10);
		}
		return txtSubjectName;
	}
	public JButton getBtnCreateSubject() {
		if (btnCreateSubject == null) {
			btnCreateSubject = new JButton("Create Subject");
			designButton(btnCreateSubject);
		}
		return btnCreateSubject;
	}
	
	private void designButton(final JButton b){
		b.setFont(new Font("Century Gothic", Font.BOLD, 18));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setBackground(new Color(0, 128, 128).darker().darker());
		b.addMouseListener(new ButtonMouseListener(b));
	}
	public JLabel getLblSeeAllSubjects() {
		if (lblSeeAllSubjects == null) {
			lblSeeAllSubjects = new JLabel("See All Subjects");
			lblSeeAllSubjects.setForeground(Color.BLUE);
			lblSeeAllSubjects.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
		}
		return lblSeeAllSubjects;
	}
}
