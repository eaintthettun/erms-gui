package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

public class ButtonPane extends JPanel {
	private JButton btnOne;
	private JButton btnTwo;
	private JButton btnThree;
	private JButton btnFour;
	private JButton btnFive;

	/**
	 * Create the panel.
	 */
	public ButtonPane() {
		setBorder(new MatteBorder(5, 50, 5, 50, (Color) UIManager.getColor("Panel.background")));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 40, 5));
		add(getBtnFive());
		add(getBtnFour());
		add(getBtnThree());
		add(getBtnTwo());
		add(getBtnOne());

	}
	public JButton getBtnOne() {
		if (btnOne == null) {
			btnOne = new JButton("One");
			designButton(btnOne);
		}
		return btnOne;
	}
	public JButton getBtnTwo() {
		if (btnTwo == null) {
			btnTwo = new JButton("Two");
			designButton(btnTwo);
		}
		return btnTwo;
	}
	public JButton getBtnThree() {
		if (btnThree == null) {
			btnThree = new JButton("Three");
			designButton(btnThree);
		}
		return btnThree;
	}
	public JButton getBtnFour() {
		if (btnFour == null) {
			btnFour = new JButton("Four");
			designButton(btnFour);
		}
		return btnFour;
	}
	public JButton getBtnFive() {
		if (btnFive == null) {
			btnFive = new JButton("Five");
			designButton(btnFive);
		}
		return btnFive;
	}
	
	private void designButton(final JButton b){
		b.setFont(new Font("Century Gothic", Font.BOLD, 18));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setBackground(new Color(0, 128, 128).darker().darker());
	}
}
