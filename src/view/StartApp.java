package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.MainController;
import database.Dao;

public class StartApp {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
				} catch (Exception e) {
				    // If Nimbus is not available, you can set the GUI to another look and feel.
				}
				try {
					Dao.prepareDatabase();
					Dao.prepareAdminTable();
					Dao.prepareTables();
					Dao.preparePicture();
					Dao.prepareMajorData();
					Dao.prepareDeptData();
					
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(1350, 720);
					Dimension d = new Dimension(1300,700);
					frame.setMinimumSize(d);
					frame.setLayout(new BorderLayout());
					new MainController(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
