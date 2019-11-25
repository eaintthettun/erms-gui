package view;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import com.jgoodies.forms.layout.Sizes;

import database.CourseDao;
import database.MajorDao;

public class AcademicSection extends JPanel {
	private JLabel lblAcademicYear;
	private JLabel lblYear;
	private JLabel lblSelectMajor;
	private JComboBox comboMajor;
	private JComboBox comboAcdYr;
	private JComboBox comboYear;
	/**
	 * Create the panel.
	 */
	public AcademicSection() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Academic Section : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(220, 220, 220));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("98dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("20dlu"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("20dlu"),
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("100dlu"),},
			new RowSpec[] {
				RowSpec.decode("10dlu"),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("23dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("23dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("8dlu"),}));
		add(getLblAcademicYear(), "4, 2, center, fill");
		add(getLblSelectMajor(), "6, 2, center, fill");
		add(getLblYear(), "8, 2, center, fill");
		add(getComboAcdYr(), "4, 4, fill, fill");
		add(getComboMajor(), "6, 4, fill, fill");
		add(getComboYear(), "8, 4, fill, fill");
	}

	public JLabel getLblAcademicYear() {
		if (lblAcademicYear == null) {
			lblAcademicYear = new JLabel("Academic Year");
			lblAcademicYear.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblAcademicYear;
	}
	public JLabel getLblSelectMajor() {
		if (lblSelectMajor == null) {
			lblSelectMajor = new JLabel("Select Major");
			lblSelectMajor.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblSelectMajor;
	}
	public JLabel getLblYear() {
		if (lblYear == null) {
			lblYear = new JLabel("Select Year");
			lblYear.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblYear;
	}
	
	public JComboBox getComboAcdYr() {
		if (comboAcdYr == null) {
			ArrayList<String> acys = CourseDao.getAvailableAcademicYear();
			String[] obj = {"No Academic Year"};
			
			if(acys == null || acys.isEmpty())
				comboAcdYr = new JComboBox(obj);
			else
				comboAcdYr = new JComboBox(acys.toArray());
			
			comboAcdYr.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return comboAcdYr;
	}
	public JComboBox getComboMajor() {
		if (comboMajor == null) {
			comboMajor = new JComboBox(MajorDao.getMajorCode().toArray());
			comboMajor.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return comboMajor;
	}
	public JComboBox getComboYear() {
		if (comboYear == null) {
			comboYear = new JComboBox();
			comboYear.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return comboYear;
	}
}
