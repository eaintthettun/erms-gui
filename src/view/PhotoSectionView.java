package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

public class PhotoSectionView extends JPanel {
	private JLabel lblPhotoBox;
	private JLabel lblPhoto;

	/**
	 * Create the panel.
	 */
	public PhotoSectionView() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Photo Section ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(255, 250, 250));
		setLayout(null);
		setVisible(true);
		setSize(300, 250);
		add(getLblPhotoBox());
		add(getLblPhoto());

	}
	public JLabel getLblPhotoBox() {
		if (lblPhotoBox == null) {
			lblPhotoBox = new JLabel("+ Add Photo");
			lblPhotoBox.setIconTextGap(0);
			lblPhotoBox.setHorizontalAlignment(SwingConstants.CENTER);
			lblPhotoBox.setForeground(Color.RED);
			lblPhotoBox.setFont(new Font("Century Gothic", Font.PLAIN, 11));
			lblPhotoBox.setBackground(Color.WHITE);
			lblPhotoBox.setBounds(160, 43, 100, 100);
		}
		return lblPhotoBox;
	}
	public JLabel getLblPhoto() {
		if (lblPhoto == null) {
			lblPhoto = new JLabel("Photo");
			lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
			lblPhoto.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
			lblPhoto.setBounds(160, 173, 100, 30);
		}
		return lblPhoto;
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLACK);
		Rectangle r=getLblPhotoBox().getBounds();
		g.drawRect(r.x-2, r.y-2, r.height+4, r.width+4);
	}
}
