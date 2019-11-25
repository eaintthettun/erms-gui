package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class SearchView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSearchStudent;
	private JTextField textField;
	private JLabel lblSearch;
	private JButton btnGo;
	private JRadioButton rdbtnId;
	private JRadioButton rdbtnName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblChoose;
	private JLabel labelMsg;
	private JLabel labelResult;

	/**
	 * Create the panel.
	 */
	public SearchView() {
		setBackground(new Color(220, 220, 220));
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		setLayout(null);
		add(getLblSearchStudent());
		add(getTextField());
		add(getLblSearch());
		add(getBtnGo());
		add(getRdbtnId());
		add(getRdbtnName());
		add(getLblChoose());
		add(getLabelMsg());
		add(getLabelResult());

	}
	public JLabel getLblSearchStudent() {
		if (lblSearchStudent == null) {
			lblSearchStudent = new JLabel("Search Student");
			lblSearchStudent.setHorizontalAlignment(SwingConstants.CENTER);
			lblSearchStudent.setForeground(new Color(70, 130, 180));
			lblSearchStudent.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
			lblSearchStudent.setBounds(91, 101, 717, 31);
		}
		return lblSearchStudent;
	}
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setForeground(Color.BLUE);
			textField.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			textField.setColumns(10);
			textField.setBounds(376, 332, 220, 38);
		}
		return textField;
	}
	public JLabel getLblSearch() {
		if (lblSearch == null) {
			lblSearch = new JLabel("Search :");
			lblSearch.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			lblSearch.setBounds(263, 331, 91, 39);
		}
		return lblSearch;
	}
	public JButton getBtnGo() {
		if (btnGo == null) {
			btnGo = new JButton("GO");
			btnGo.setBackground(new Color(0, 139, 139).darker().darker());
			btnGo.setForeground(Color.WHITE);
			btnGo.setFont(new Font("Century Gothic", Font.BOLD, 14));
			btnGo.setIconTextGap(0);
			btnGo.setBounds(608, 331, 63, 39);
		}
		return btnGo;
	}
	public JRadioButton getRdbtnId() {
		if (rdbtnId == null) {
			rdbtnId = new JRadioButton("ID");
			rdbtnId.setBackground(SystemColor.activeCaptionBorder);
			buttonGroup.add(rdbtnId);
			rdbtnId.setSelected(true);
			rdbtnId.setForeground(Color.BLUE);
			rdbtnId.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			rdbtnId.setBounds(385, 233, 91, 30);
		}
		return rdbtnId;
	}
	public JRadioButton getRdbtnName() {
		if (rdbtnName == null) {
			rdbtnName = new JRadioButton("Name");
			rdbtnName.setBackground(SystemColor.activeCaptionBorder);
			buttonGroup.add(rdbtnName);
			rdbtnName.setForeground(Color.BLUE);
			rdbtnName.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			rdbtnName.setBounds(491, 233, 93, 30);
		}
		return rdbtnName;
	}
	public JLabel getLblChoose() {
		if (lblChoose == null) {
			lblChoose = new JLabel("Search By :");
			lblChoose.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			lblChoose.setBounds(263, 233, 91, 30);
		}
		return lblChoose;
	}
	public JLabel getLabelMsg() {
		if (labelMsg == null) {
			labelMsg = new JLabel("");
			labelMsg.setHorizontalAlignment(SwingConstants.CENTER);
			labelMsg.setForeground(SystemColor.textHighlight);
			labelMsg.setFont(new Font("Century Gothic", Font.ITALIC, 14));
			labelMsg.setBounds(263, 437, 429, 30);
		}
		return labelMsg;
	}
	public JLabel getLabelResult() {
		if (labelResult == null) {
			labelResult = new JLabel("");
			labelResult.setHorizontalAlignment(SwingConstants.CENTER);
			labelResult.setForeground(SystemColor.textHighlight);
			labelResult.setFont(new Font("Century Gothic", Font.ITALIC, 14));
			labelResult.setBounds(263, 519, 429, 30);
		}
		return labelResult;
	}
}
