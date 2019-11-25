package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;

public class HomeView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblHomeView;
	private JLabel labelYTUpic;
	private JLabel lblPhotoCredit;
	private JButton btnBackup;
	private JButton btnRestore;
	private JButton btnLogout;
	private JLabel lblSetting;
	private JLabel lblHelp;
	private JLabel label;
	/*
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLACK);
		Rectangle r=getLabelYTUpic().getBounds();
		g.drawRect(r.x-2, r.y-2, r.width+4, r.height+4);
		
	}
	*/
	/**
	 * Create the panel.
	 */
	public HomeView() {
		setBackground(new Color(220, 220, 220));
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		setLayout(null);
		add(getLblHomeView());
		add(getLabelYTUpic());
		add(getLblPhotoCredit());
		add(getBtnBackup());
		add(getBtnRestore());
		add(getBtnLogout());
		add(getLblSetting());
		add(getLblHelp());
		add(getLabel());

	}
	public JLabel getLblHomeView() {
		if (lblHomeView == null) {
			lblHomeView = new JLabel("Exam Result Management System");
			lblHomeView.setHorizontalAlignment(SwingConstants.CENTER);
			lblHomeView.setForeground(new Color(70, 130, 180));
			lblHomeView.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
			lblHomeView.setBounds(84, 36, 697, 31);
		}
		return lblHomeView;
	}
	public JLabel getLabelYTUpic() {
		if (labelYTUpic == null) {
			labelYTUpic = new JLabel("");
			labelYTUpic.setIconTextGap(0);
			labelYTUpic.setBounds(73, 126, 730, (int)(730*0.6));
		}
		return labelYTUpic;
	}
	public JLabel getLblPhotoCredit() {
		if (lblPhotoCredit == null) {
			lblPhotoCredit = new JLabel("");
			lblPhotoCredit.setForeground(Color.GRAY);
			lblPhotoCredit.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPhotoCredit.setFont(new Font("Century Gothic", Font.ITALIC, 10));
			lblPhotoCredit.setBounds(621, 569, 182, 20);
		}
		return lblPhotoCredit;
	}
	public JButton getBtnBackup() {
		if (btnBackup == null) {
			btnBackup = new JButton("Backup");
			btnBackup.setBounds(854, 126, 130, 40);
			designButton(btnBackup);
		}
		return btnBackup;
	}
	public JButton getBtnRestore() {
		if (btnRestore == null) {
			btnRestore = new JButton("Restore");
			btnRestore.setBounds(854, 207, 130, 40);
			designButton(btnRestore);
		}
		return btnRestore;
	}
	public JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout");
			btnLogout.setBounds(854, 288, 130, 40);
			designButton(btnLogout);
		}
		return btnLogout;
	}
	
	private void designButton(final JButton b){
		b.setFont(new Font("Century Gothic", Font.BOLD, 18));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setBackground(new Color(0, 128, 128).darker().darker());
	}
	public JLabel getLblSetting() {
		if (lblSetting == null) {
			lblSetting = new JLabel("Setting");
			lblSetting.setForeground(Color.BLUE);
			lblSetting.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
			lblSetting.setHorizontalAlignment(SwingConstants.LEFT);
			lblSetting.setBounds(929, 548, 55, 16);
		}
		return lblSetting;
	}
	public JLabel getLblHelp() {
		if (lblHelp == null) {
			lblHelp = new JLabel("Help");
			lblHelp.setForeground(Color.BLUE);
			lblHelp.setHorizontalAlignment(SwingConstants.RIGHT);
			lblHelp.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
			lblHelp.setBounds(854, 548, 55, 16);
		}
		return lblHelp;
	}
	public JLabel getLabel() {
		if (label == null) {
			label = new JLabel("|");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
			label.setBounds(912, 548, 15, 16);
		}
		return label;
	}
}
