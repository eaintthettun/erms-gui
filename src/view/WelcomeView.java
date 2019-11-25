package view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;

public class WelcomeView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblUsername;
	private JTextField textFieldUsername;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JPasswordField passwordField;
	private JLabel labelAsk;
	private JLabel lblCreate;
	private JLabel labelTwo;

	/**
	 * Create the panel.
	 */
	public WelcomeView() {
		setBackground(SystemColor.activeCaptionBorder);
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		setLayout(null);
		add(getLblUsername());
		add(getTextFieldUsername());
		add(getLblPassword());
		add(getBtnLogin());
		add(getPasswordField());
		add(getLabelAsk());
		add(getLblCreate());
		add(getLabelTwo());

	}
	public JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("Username : ");
			lblUsername.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsername.setBounds(446, 226, 102, 37);
		}
		return lblUsername;
	}
	public JTextField getTextFieldUsername() {
		if (textFieldUsername == null) {
			textFieldUsername = new JTextField();
			textFieldUsername.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			textFieldUsername.setForeground(Color.BLUE);
			textFieldUsername.setBounds(588, 226, 236, 37);
		}
		return textFieldUsername;
	}
	public JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password : \r\n");
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			lblPassword.setBounds(446, 317, 102, 37);
		}
		return lblPassword;
	}
	public JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("Login");
			btnLogin.setFont(new Font("Century Gothic", Font.BOLD, 18));
			btnLogin.setForeground(Color.WHITE);
			btnLogin.setBackground(new Color(0, 128, 128).darker().darker());
			btnLogin.setBounds(704, 455, 120, 35);
		}
		return btnLogin;
	}
	public JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setForeground(Color.BLUE);
			passwordField.setBounds(588, 317, 236, 37);
		}
		return passwordField;
	}
	public JLabel getLabelAsk() {
		if (labelAsk == null) {
			labelAsk = new JLabel("");
			labelAsk.setBackground(Color.DARK_GRAY);
			labelAsk.setHorizontalAlignment(SwingConstants.LEFT);
			labelAsk.setForeground(new Color(148, 0, 211));
			labelAsk.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			labelAsk.setBounds(456, 171, 470, 31);
		}
		return labelAsk;
	}
	public JLabel getLblCreate() {
		if (lblCreate == null) {
			lblCreate = new JLabel("Create Admin Account");
			lblCreate.setVisible(false);
			lblCreate.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 12));
			lblCreate.setForeground(Color.BLUE);
			lblCreate.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCreate.setBounds(588, 396, 236, 24);
		}
		return lblCreate;
	}
	public JLabel getLabelTwo() {
		if (labelTwo == null) {
			labelTwo = new JLabel("Exam Result Management System");
			labelTwo.setHorizontalAlignment(SwingConstants.CENTER);
			labelTwo.setForeground(new Color(70, 130, 180));
			labelTwo.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
			labelTwo.setBounds(247, 68, 792, 31);
		}
		return labelTwo;
	}
}
