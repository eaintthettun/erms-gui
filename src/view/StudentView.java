package view;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;

import database.MajorDao;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import javax.swing.ButtonGroup;

public class StudentView extends JPanel {
	private JScrollPane scrollPane;
	private JLabel labelTitle;
	private JLabel lblNameen;
	private JTextField txtNameEN;
	private JTextField txtNameMM;
	private JTextField txtEntranceID;
	private JRadioButton radioMale;
	private JRadioButton radioFemale;
	private JTextField txtEthnicGroup;
	private JTextField txtReligion;
	private JTextField txtNRCNo;
	private JTextArea txtBirthPlace;
	private JTextArea txtPermAddr;
	private JTextArea txtCurrentAddr;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JLabel lblNamemm;
	private JLabel lblEntranceId;
	private JLabel lblGender;
	private JLabel lblEthnicGroup;
	private JLabel lblReligion;
	private JLabel lblNrcNo;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JLabel lblBirthPlace;
	private JLabel lblPermanentAddress;
	private JLabel lblCurrentAddress;
	private JDateChooser dateChooser;
	private JComboBox comboBoxMajor;
	private JLabel lblDateOfBirth;
	private JLabel lblMajor;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblBack;

	/**
	 * Create the panel.
	 */
	public StudentView() {
		setBorder(new MatteBorder(4, 50, 4, 50, (Color) new Color(255, 255, 224)));
		setBackground(new Color(255, 255, 224));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("86dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("75dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("75dlu:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(50dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(50dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(50dlu;default):grow"),}));
		add(getLblBack(), "2, 2");
		add(getLabelTitle(), "2, 4, 5, 1");
		add(getLblNameen(), "2, 6, fill, default");
		add(getTxtNameEN(), "4, 6, 3, 1, fill, default");
		add(getLblNamemm(), "2, 8, fill, default");
		add(getTxtNameMM(), "4, 8, 3, 1, fill, default");
		add(getLblEntranceId(), "2, 10, fill, default");
		add(getTxtEntranceID(), "4, 10, 3, 1, fill, default");
		add(getLblGender(), "2, 12");
		add(getRadioMale(), "4, 12");
		add(getRadioFemale(), "6, 12");
		add(getLblEthnicGroup(), "2, 14, fill, default");
		add(getTxtEthnicGroup(), "4, 14, 3, 1, fill, default");
		add(getLblReligion(), "2, 16, fill, default");
		add(getTxtReligion(), "4, 16, 3, 1, fill, default");
		add(getLblNrcNo(), "2, 18, fill, default");
		add(getTxtNRCNo(), "4, 18, 3, 1, fill, default");
		add(getLblDateOfBirth(), "2, 20");
		add(getDateChooser(), "4, 20, 3, 1, fill, default");
		add(getLblMajor(), "2, 22, fill, default");
		add(getComboBoxMajor(), "4, 22, 3, 1, fill, default");
		add(getLblPhone(), "2, 24, fill, default");
		add(getTxtPhone(), "4, 24, 3, 1, fill, default");
		add(getLblEmail(), "2, 26, fill, default");
		add(getTxtEmail(), "4, 26, 3, 1, fill, default");
		add(getLblBirthPlace(), "2, 28");
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(getTxtBirthPlace());
		add(scrollPane, "4, 28, 3, 1, fill, fill");
		add(getLblPermanentAddress(), "2, 30");
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(getTxtPermAddr());
		add(scrollPane, "4, 30, 3, 1, fill, fill");
		add(getLblCurrentAddress(), "2, 32");
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(getTxtCurrentAddr());
		add(scrollPane, "4, 32, 3, 1, fill, fill");
	}
	public JLabel getLabelTitle() {
		if (labelTitle == null) {
			labelTitle = new JLabel("Student Registration");
			labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
			labelTitle.setForeground(new Color(70, 130, 180));
			labelTitle.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		}
		return labelTitle;
	}
	public JLabel getLblNameen() {
		if (lblNameen == null) {
			lblNameen = new JLabel("Name (EN) :");
			lblNameen.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblNameen;
	}
	public JTextField getTxtNameEN() {
		if (txtNameEN == null) {
			txtNameEN = new JTextField();
			txtNameEN.setForeground(Color.BLUE);
			txtNameEN.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtNameEN.setColumns(10);
		}
		return txtNameEN;
	}
	public JTextField getTxtNameMM() {
		if (txtNameMM == null) {
			txtNameMM = new JTextField();
			txtNameMM.setForeground(Color.BLUE);
			txtNameMM.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtNameMM.setColumns(10);
		}
		return txtNameMM;
	}
	public JTextField getTxtEntranceID() {
		if (txtEntranceID == null) {
			txtEntranceID = new JTextField();
			txtEntranceID.setForeground(Color.BLUE);
			txtEntranceID.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtEntranceID.setColumns(10);
		}
		return txtEntranceID;
	}
	public JRadioButton getRadioMale() {
		if (radioMale == null) {
			radioMale = new JRadioButton("Male");
			buttonGroup.add(radioMale);
			radioMale.setSelected(true);
			radioMale.setForeground(Color.BLUE);
			radioMale.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		}
		return radioMale;
	}
	public JRadioButton getRadioFemale() {
		if (radioFemale == null) {
			radioFemale = new JRadioButton("Female");
			buttonGroup.add(radioFemale);
			radioFemale.setForeground(Color.BLUE);
			radioFemale.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		}
		return radioFemale;
	}
	public JTextField getTxtEthnicGroup() {
		if (txtEthnicGroup == null) {
			txtEthnicGroup = new JTextField();
			txtEthnicGroup.setForeground(Color.BLUE);
			txtEthnicGroup.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtEthnicGroup.setColumns(10);
		}
		return txtEthnicGroup;
	}
	public JTextField getTxtReligion() {
		if (txtReligion == null) {
			txtReligion = new JTextField();
			txtReligion.setForeground(Color.BLUE);
			txtReligion.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtReligion.setColumns(10);
		}
		return txtReligion;
	}
	public JTextField getTxtNRCNo() {
		if (txtNRCNo == null) {
			txtNRCNo = new JTextField();
			txtNRCNo.setForeground(Color.BLUE);
			txtNRCNo.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtNRCNo.setColumns(10);
		}
		return txtNRCNo;
	}
	public JTextArea getTxtBirthPlace() {
		if (txtBirthPlace == null) {
			txtBirthPlace = new JTextArea();
			txtBirthPlace.setForeground(Color.BLUE);
			txtBirthPlace.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
		}
		return txtBirthPlace;
	}
	public JTextArea getTxtPermAddr() {
		if (txtPermAddr == null) {
			txtPermAddr = new JTextArea();
			txtPermAddr.setForeground(Color.BLUE);
			txtPermAddr.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
		}
		return txtPermAddr;
	}
	public JTextArea getTxtCurrentAddr() {
		if (txtCurrentAddr == null) {
			txtCurrentAddr = new JTextArea();
			txtCurrentAddr.setForeground(Color.BLUE);
			txtCurrentAddr.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
		}
		return txtCurrentAddr;
	}
	
	public JTextField getTxtPhone() {
		if (txtPhone == null) {
			txtPhone = new JTextField();
			txtPhone.setForeground(Color.BLUE);
			txtPhone.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtPhone.setColumns(10);
		}
		return txtPhone;
	}
	public JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setForeground(Color.BLUE);
			txtEmail.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}
	public JLabel getLblNamemm() {
		if (lblNamemm == null) {
			lblNamemm = new JLabel("Name (MM) :");
			lblNamemm.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblNamemm;
	}
	public JLabel getLblEntranceId() {
		if (lblEntranceId == null) {
			lblEntranceId = new JLabel("Entrance ID :");
			lblEntranceId.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblEntranceId;
	}
	public JLabel getLblGender() {
		if (lblGender == null) {
			lblGender = new JLabel("Gender :");
			lblGender.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblGender;
	}
	public JLabel getLblEthnicGroup() {
		if (lblEthnicGroup == null) {
			lblEthnicGroup = new JLabel("Ethnic Group :");
			lblEthnicGroup.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblEthnicGroup;
	}
	public JLabel getLblReligion() {
		if (lblReligion == null) {
			lblReligion = new JLabel("Religion :");
			lblReligion.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblReligion;
	}
	public JLabel getLblNrcNo() {
		if (lblNrcNo == null) {
			lblNrcNo = new JLabel("NRC No :");
			lblNrcNo.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblNrcNo;
	}
	public JLabel getLblPhone() {
		if (lblPhone == null) {
			lblPhone = new JLabel("Phone :");
			lblPhone.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblPhone;
	}
	public JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email :");
			lblEmail.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblEmail;
	}
	public JLabel getLblBirthPlace() {
		if (lblBirthPlace == null) {
			lblBirthPlace = new JLabel("Birth Place :");
			lblBirthPlace.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblBirthPlace;
	}
	public JLabel getLblPermanentAddress() {
		if (lblPermanentAddress == null) {
			lblPermanentAddress = new JLabel("Permanent Address :");
			lblPermanentAddress.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblPermanentAddress;
	}
	public JLabel getLblCurrentAddress() {
		if (lblCurrentAddress == null) {
			lblCurrentAddress = new JLabel("Current Address :");
			lblCurrentAddress.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblCurrentAddress;
	}
	public JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setForeground(Color.BLUE);
			dateChooser.setDateFormatString("MMM dd, yyyy E");
		}
		return dateChooser;
	}
	public JComboBox getComboBoxMajor() {
		if (comboBoxMajor == null) {
			/*
			String[] maj = {
					"Civil Engineering",
					"Mechanical Engineering",
					"Electrical Power Engineering",
					"Electronics Engineering",
					"Computer Engineering and Information Technology",
					"Mechatronics Engineering",
					"Chemical Engineering",
					"Petroleum Engineering",
					"Metallurgy Engineering",
					"Mining Engineering",
					"Textile Engineering",
					"Architecture"
			};
			*/
			comboBoxMajor = new JComboBox(MajorDao.getPrimaryMajorName().toArray());
			comboBoxMajor.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		}
		return comboBoxMajor;
	}
	public JLabel getLblDateOfBirth() {
		if (lblDateOfBirth == null) {
			lblDateOfBirth = new JLabel("Date Of Birth :");
			lblDateOfBirth.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblDateOfBirth;
	}
	public JLabel getLblMajor() {
		if (lblMajor == null) {
			lblMajor = new JLabel("Major :");
			lblMajor.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblMajor;
	}
	public JLabel getLblBack() {
		if (lblBack == null) {
			lblBack = new JLabel("< Back");
			lblBack.setForeground(Color.BLUE);
			lblBack.setFont(new Font("Calibri Light", Font.BOLD, 18));
		}
		return lblBack;
	}
}
