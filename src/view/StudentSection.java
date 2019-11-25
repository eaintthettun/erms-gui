package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

import controller.ButtonMouseListener;

public class StudentSection extends JPanel {
	
	private JLabel lblEntranceID;
	private JTextField txtEntranceID;
	private JTextField txtStudentName;
	private JButton btnAdd;

	/**
	 * Create the panel.
	 */
	public StudentSection() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Student Section : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(220, 220, 220));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("70dlu"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("70dlu"),
				ColumnSpec.decode("10dlu"),
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("120dlu", true), Sizes.constant("140dlu", true)), 1),
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("70dlu"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		add(getLblEntranceID(),"2, 2, right, fill");
		add(getTxtEntranceID(),"4, 2, fill, fill");
		add(getTxtStudentName(),"6, 2, fill, fill");
		add(getBtnAdd(),"8, 2, left, fill");
	}
	
	public JLabel getLblEntranceID() {
		if (lblEntranceID == null) {
			lblEntranceID = new JLabel("Enter Student Entrance ID :");
			lblEntranceID.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblEntranceID;
	}
	public JTextField getTxtEntranceID() {
		if (txtEntranceID == null) {
			txtEntranceID = new JTextField();
			txtEntranceID.setForeground(Color.BLUE);
			txtEntranceID.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			txtEntranceID.setColumns(10);
		}
		return txtEntranceID;
	}
	public JTextField getTxtStudentName() {
		if (txtStudentName == null) {
			txtStudentName = new JTextField();
			txtStudentName.setBackground(new Color(255, 255, 240));
			txtStudentName.setForeground(Color.MAGENTA);
			txtStudentName.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			txtStudentName.setEditable(false);
			txtStudentName.setColumns(10);
		}
		return txtStudentName;
	}
	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Search");
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setFont(new Font("Century Gothic", Font.BOLD, 18));
			btnAdd.setFocusPainted(false);
			btnAdd.setBackground(new Color(0, 62, 62));
			btnAdd.addMouseListener(new ButtonMouseListener(btnAdd));
		}
		return btnAdd;
	}

}
