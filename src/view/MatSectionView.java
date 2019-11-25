package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;

public class MatSectionView extends JPanel {
	private JTextField txtDeptMat;
	private JTextField txtYearOfMat;
	private JTextField txtGrade10RollNo;
	private JLabel lblGradeRollNo;
	private JLabel lblYearOfMatriculation;
	private JLabel lblDepartment;

	/**
	 * Create the panel.
	 */
	public MatSectionView() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Matriculation Exam Section ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(255, 250, 250));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(109dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		add(getLblGradeRollNo(), "2, 2, fill, default");
		add(getTxtGrade10RollNo(), "4, 2, fill, center");
		add(getLblYearOfMatriculation(), "2, 4, fill, default");
		add(getTxtYearOfMat(), "4, 4, fill, center");
		add(getLblDepartment(), "2, 6, fill, default");
		add(getTxtDeptMat(), "4, 6, fill, center");

	}

	public JTextField getTxtDeptMat() {
		if (txtDeptMat == null) {
			txtDeptMat = new JTextField();
			txtDeptMat.setForeground(Color.BLUE);
			txtDeptMat.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtDeptMat.setColumns(10);
		}
		return txtDeptMat;
	}
	public JTextField getTxtYearOfMat() {
		if (txtYearOfMat == null) {
			txtYearOfMat = new JTextField();
			txtYearOfMat.setForeground(Color.BLUE);
			txtYearOfMat.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtYearOfMat.setColumns(10);
		}
		return txtYearOfMat;
	}
	public JTextField getTxtGrade10RollNo() {
		if (txtGrade10RollNo == null) {
			txtGrade10RollNo = new JTextField();
			txtGrade10RollNo.setForeground(Color.BLUE);
			txtGrade10RollNo.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtGrade10RollNo.setColumns(10);
		}
		return txtGrade10RollNo;
	}
	public JLabel getLblGradeRollNo() {
		if (lblGradeRollNo == null) {
			lblGradeRollNo = new JLabel("Grade-10 Roll No :");
			lblGradeRollNo.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblGradeRollNo;
	}
	public JLabel getLblYearOfMatriculation() {
		if (lblYearOfMatriculation == null) {
			lblYearOfMatriculation = new JLabel("Year Of Matriculation :");
			lblYearOfMatriculation.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblYearOfMatriculation;
	}
	public JLabel getLblDepartment() {
		if (lblDepartment == null) {
			lblDepartment = new JLabel("Passed Department :");
			lblDepartment.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblDepartment;
	}
}
