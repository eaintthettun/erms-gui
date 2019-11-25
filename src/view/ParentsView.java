package view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ParentsView extends JPanel {
	private JLabel lblName;
	private JTextField txtMotherNameEN;
	private JTextField txtFatherNameEN;
	private JLabel lblFather;
	private JLabel lblMother;
	private JLabel lblNamemm;
	private JLabel lblNrc;
	private JLabel lblEthnic;
	private JLabel lblReligion;
	private JLabel lblBirthPlace;
	private JLabel lblAddress;
	private JTextField txtFatherNameMM;
	private JTextField txtFatherNRC;
	private JTextField txtFatherEthnic;
	private JTextField txtFatherReligion;
	private JTextField txtMotherNameMM;
	private JTextField txtMotherNRC;
	private JTextField txtMotherEthnic;
	private JTextField txtMotherReligion;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTextArea txtFatherBirthPlace;
	private JTextArea txtFatherAddr;
	private JTextArea txtMotherAddr;
	private JTextArea txtMotherBirthPlace;

	/**
	 * Create the panel.
	 */
	public ParentsView() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Parents Section ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(255, 250, 250));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(59dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(88dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("86dlu:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(22dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(40dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(40dlu;default):grow"),}));
		add(getLblFather(), "4, 2");
		add(getLblMother(), "6, 2");
		add(getLblName(), "2, 4, left, center");
		add(getTxtFatherNameEN(), "4, 4, fill, center");
		add(getTxtMotherNameEN(), "6, 4, fill, center");
		add(getLblNamemm(), "2, 6, left, center");
		add(getTxtFatherNameMM(), "4, 6, fill, center");
		add(getTxtMotherNameMM(), "6, 6, fill, center");
		add(getLblNrc(), "2, 8, left, center");
		add(getTxtFatherNRC(), "4, 8, fill, center");
		add(getTxtMotherNRC(), "6, 8, fill, center");
		add(getLblEthnic(), "2, 10, left, center");
		add(getTxtFatherEthnic(), "4, 10, fill, center");
		add(getTxtMotherEthnic(), "6, 10, fill, center");
		add(getLblReligion(), "2, 12, left, center");
		add(getTxtFatherReligion(), "4, 12, fill, center");
		add(getTxtMotherReligion(), "6, 12, fill, center");
		add(getLblBirthPlace(), "2, 14, left, center");
		add(getScrollPane(), "4, 14, fill, fill");
		add(getScrollPane_2(), "6, 14, fill, fill");
		add(getLblAddress(), "2, 16, left, center");
		add(getScrollPane_1(), "4, 16, fill, fill");
		add(getScrollPane_3(), "6, 16, fill, fill");

	}

	public JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name (EN) :");
			lblName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblName;
	}
	public JTextField getTxtMotherNameEN() {
		if (txtMotherNameEN == null) {
			txtMotherNameEN = new JTextField();
			txtMotherNameEN.setForeground(Color.BLUE);
			txtMotherNameEN.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtMotherNameEN.setColumns(10);
		}
		return txtMotherNameEN;
	}
	public JTextField getTxtFatherNameEN() {
		if (txtFatherNameEN == null) {
			txtFatherNameEN = new JTextField();
			txtFatherNameEN.setForeground(Color.BLUE);
			txtFatherNameEN.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtFatherNameEN.setColumns(10);
		}
		return txtFatherNameEN;
	}
	public JLabel getLblFather() {
		if (lblFather == null) {
			lblFather = new JLabel("Father");
			lblFather.setHorizontalAlignment(SwingConstants.CENTER);
			lblFather.setFont(new Font("Century Gothic", Font.BOLD, 15));
			lblFather.setForeground(new Color(70, 130, 180));
		}
		return lblFather;
	}
	public JLabel getLblMother() {
		if (lblMother == null) {
			lblMother = new JLabel("Mother");
			lblMother.setHorizontalAlignment(SwingConstants.CENTER);
			lblMother.setFont(new Font("Century Gothic", Font.BOLD, 15));
			lblMother.setForeground(new Color(70, 130, 180));
		}
		return lblMother;
	}
	public JLabel getLblNamemm() {
		if (lblNamemm == null) {
			lblNamemm = new JLabel("Name (MM) :");
			lblNamemm.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblNamemm;
	}
	public JLabel getLblNrc() {
		if (lblNrc == null) {
			lblNrc = new JLabel("NRC :");
			lblNrc.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblNrc;
	}
	public JLabel getLblEthnic() {
		if (lblEthnic == null) {
			lblEthnic = new JLabel("Ethnic :");
			lblEthnic.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblEthnic;
	}
	public JLabel getLblReligion() {
		if (lblReligion == null) {
			lblReligion = new JLabel("Religion :");
			lblReligion.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblReligion;
	}
	public JLabel getLblBirthPlace() {
		if (lblBirthPlace == null) {
			lblBirthPlace = new JLabel("Birth Place :");
			lblBirthPlace.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblBirthPlace;
	}
	public JLabel getLblAddress() {
		if (lblAddress == null) {
			lblAddress = new JLabel("Job/Address :");
			lblAddress.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblAddress;
	}
	public JTextField getTxtFatherNameMM() {
		if (txtFatherNameMM == null) {
			txtFatherNameMM = new JTextField();
			txtFatherNameMM.setForeground(Color.BLUE);
			txtFatherNameMM.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtFatherNameMM.setColumns(10);
		}
		return txtFatherNameMM;
	}
	public JTextField getTxtFatherNRC() {
		if (txtFatherNRC == null) {
			txtFatherNRC = new JTextField();
			txtFatherNRC.setForeground(Color.BLUE);
			txtFatherNRC.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtFatherNRC.setColumns(10);
		}
		return txtFatherNRC;
	}
	public JTextField getTxtFatherEthnic() {
		if (txtFatherEthnic == null) {
			txtFatherEthnic = new JTextField();
			txtFatherEthnic.setForeground(Color.BLUE);
			txtFatherEthnic.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtFatherEthnic.setColumns(10);
		}
		return txtFatherEthnic;
	}
	public JTextField getTxtFatherReligion() {
		if (txtFatherReligion == null) {
			txtFatherReligion = new JTextField();
			txtFatherReligion.setForeground(Color.BLUE);
			txtFatherReligion.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtFatherReligion.setColumns(10);
		}
		return txtFatherReligion;
	}
	public JTextField getTxtMotherNameMM() {
		if (txtMotherNameMM == null) {
			txtMotherNameMM = new JTextField();
			txtMotherNameMM.setForeground(Color.BLUE);
			txtMotherNameMM.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtMotherNameMM.setColumns(10);
		}
		return txtMotherNameMM;
	}
	public JTextField getTxtMotherNRC() {
		if (txtMotherNRC == null) {
			txtMotherNRC = new JTextField();
			txtMotherNRC.setForeground(Color.BLUE);
			txtMotherNRC.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtMotherNRC.setColumns(10);
		}
		return txtMotherNRC;
	}
	public JTextField getTxtMotherEthnic() {
		if (txtMotherEthnic == null) {
			txtMotherEthnic = new JTextField();
			txtMotherEthnic.setForeground(Color.BLUE);
			txtMotherEthnic.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtMotherEthnic.setColumns(10);
		}
		return txtMotherEthnic;
	}
	public JTextField getTxtMotherReligion() {
		if (txtMotherReligion == null) {
			txtMotherReligion = new JTextField();
			txtMotherReligion.setForeground(Color.BLUE);
			txtMotherReligion.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
			txtMotherReligion.setColumns(10);
		}
		return txtMotherReligion;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTxtFatherBirthPlace());
		}
		return scrollPane;
	}
	public JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTxtFatherAddr());
		}
		return scrollPane_1;
	}
	public JScrollPane getScrollPane_2() {
		if (scrollPane_2 == null) {
			scrollPane_2 = new JScrollPane();
			scrollPane_2.setViewportView(getTxtMotherBirthPlace());
		}
		return scrollPane_2;
	}
	public JScrollPane getScrollPane_3() {
		if (scrollPane_3 == null) {
			scrollPane_3 = new JScrollPane();
			scrollPane_3.setViewportView(getTxtMotherAddr());
		}
		return scrollPane_3;
	}
	public JTextArea getTxtFatherBirthPlace() {
		if (txtFatherBirthPlace == null) {
			txtFatherBirthPlace = new JTextArea();
			txtFatherBirthPlace.setForeground(Color.BLUE);
			txtFatherBirthPlace.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
		}
		return txtFatherBirthPlace;
	}
	public JTextArea getTxtFatherAddr() {
		if (txtFatherAddr == null) {
			txtFatherAddr = new JTextArea();
			txtFatherAddr.setForeground(Color.BLUE);
			txtFatherAddr.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			
		}
		return txtFatherAddr;
	}
	public JTextArea getTxtMotherAddr() {
		if (txtMotherAddr == null) {
			txtMotherAddr = new JTextArea();
			txtMotherAddr.setForeground(Color.BLUE);
			txtMotherAddr.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		}
		return txtMotherAddr;
	}
	public JTextArea getTxtMotherBirthPlace() {
		if (txtMotherBirthPlace == null) {
			txtMotherBirthPlace = new JTextArea();
			txtMotherBirthPlace.setForeground(Color.BLUE);
			txtMotherBirthPlace.setFont(new Font("Zawgyi-One", Font.PLAIN, 14));
		}
		return txtMotherBirthPlace;
	}
}
