package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ButtonMouseListener implements MouseListener {
	
	private JButton b;
	private Color originalColor;
	
	public ButtonMouseListener(JButton b) {
		this.b = b;
		this.originalColor = b.getBackground();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		b.setBackground(Color.DARK_GRAY);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		b.setBackground(this.originalColor);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
