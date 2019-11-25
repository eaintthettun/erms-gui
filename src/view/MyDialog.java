package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class MyDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelMessage;
	private JButton okButton;
	private JButton cancelButton;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			MyDialog dialog = new MyDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	/**
	 * Create the dialog.
	 */
	public MyDialog() {
		setSize(400,250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			contentPanel.add(getLabelMessage());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				getRootPane().setDefaultButton(getOkButton());
			}
			{
				buttonPane.add(getOkButton());
				buttonPane.add(getCancelButton());
			}
		}
	}
	
	public JLabel getLabelMessage() {
		if(labelMessage == null) {
			labelMessage = new JLabel("Are you sure you want to Delete?");
			labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
			labelMessage.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			labelMessage.setBounds(12, 67, 358, 32);
		}
		return labelMessage;
	}
	public JButton getOkButton() {
		if(okButton == null) {
			okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			okButton.setFont(new Font("Century Gothic", Font.BOLD, 14));
			okButton.setForeground(SystemColor.textHighlight);
		}
		return okButton;
	}
	public JButton getCancelButton() {
		if(cancelButton == null) {
			cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			cancelButton.setFont(new Font("Century Gothic", Font.BOLD, 14));
			cancelButton.setForeground(SystemColor.textHighlight);
		}
		return cancelButton;
	}

}
