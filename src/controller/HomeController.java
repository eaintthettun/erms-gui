package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.Dao;
import utils.Usage;
import view.ChangeOfSubjectView;
import view.HomeView;

public class HomeController {
	private HomeView hv;
	private MainController mc;
	
	public HomeController(HomeView v, MainController c){
		this.hv = v;
		this.mc = c;
		byte[] data = Dao.getPicture("bgPic");
		if(data!=null){
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			try {
				BufferedImage img = ImageIO.read(bais);
				img = resize(img);
				img.getScaledInstance(730, (int)(730*0.6), Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(img);
				this.hv.getLabelYTUpic().setIcon(icon);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.hv.getBtnBackup().addMouseListener(new ButtonMouseListener(this.hv.getBtnBackup()));
		this.hv.getBtnRestore().addMouseListener(new ButtonMouseListener(this.hv.getBtnRestore()));
		this.hv.getBtnLogout().addMouseListener(new ButtonMouseListener(this.hv.getBtnLogout()));
		
		this.hv.getBtnBackup().addActionListener(new MyActionListener());
		this.hv.getBtnRestore().addActionListener(new MyActionListener());
		this.hv.getBtnLogout().addActionListener(new MyActionListener());
		
		this.hv.getLblHelp().addMouseListener(new MyLblMouseListener());
		this.hv.getLblSetting().addMouseListener(new MyLblMouseListener());
	}
	
	private BufferedImage resize(BufferedImage bufImg) {
		BufferedImage resized = new BufferedImage(730, (int)(730*0.6),bufImg.getType());
		Graphics2D g = resized.createGraphics();
		g.drawImage(bufImg, 0, 0, 730, (int)(730*0.6), null);
		g.dispose();
		return resized;
	}
	
	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(hv.getBtnBackup())){
				processBackup();
			}
			else if(e.getSource().equals(hv.getBtnRestore())){
				processRestore();
			}
			else if(e.getSource().equals(hv.getBtnLogout())){
				int op = JOptionPane.showConfirmDialog(hv, "Are you sure you want to logout?", "Logout",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op==JOptionPane.YES_OPTION)
					mc.funAfterLogout();
			}
		}
	}
	
	private class MyLblMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(hv.getLblHelp())){
				JOptionPane.showMessageDialog(hv, 
						"Exam Result Management System\n"
						+ "Version : 1.1\n"
						+ "Release on : July 20, 2019\n"
						+ "If you encounter errors/bugs with software or want to ask questions, mail me at thesaihan@gmail.com\n"
						+ "Or call +959400067604, +959969564764, +959796617618, +9592099633", "Help", JOptionPane.QUESTION_MESSAGE);
			}
			else{
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							JFrame f = new JFrame();
							
							Usage.setFrameIcon(f);
							
							f.setVisible(true);
							f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							f.setBounds(300,100,750,550);
							f.setResizable(false);
							
							ChangeOfSubjectView cosv = new ChangeOfSubjectView();
							cosv.setVisible(true);
							
							f.add(cosv);
							new ChangeOfSubjectController(cosv);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource().equals(hv.getLblHelp()))
				hv.getLblHelp().setForeground(SystemColor.textHighlight);
			else
				hv.getLblSetting().setForeground(SystemColor.textHighlight);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource().equals(hv.getLblHelp()))
				hv.getLblHelp().setForeground(Color.BLUE);
			else
				hv.getLblSetting().setForeground(Color.BLUE);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}
	
	private void processBackup(){
		
		String dbName = "erms";
        String dbUser = "root";
        String dbPass = "root";
        
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SQL File", "sql");
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.addChoosableFileFilter(filter);
		
		int ret = jfc.showSaveDialog(hv);
		
		if(ret == JFileChooser.APPROVE_OPTION){
			File f = jfc.getSelectedFile();
			
			String desFile = f.getAbsolutePath();
			
			if(!desFile.endsWith(".sql")){
				desFile = desFile+".sql";
			}

			/*
			String path = "D:"+File.separator+"J2EE19"+File.separator
					+ "mysql-5.7.13-winx64"+File.separator+"bin"+File.separator;
			*/	
			String executeCmd = "mysqldump -u" 
					+ dbUser + " -p" 
					+ dbPass + " --add-drop-database " 
					+ dbName + " -r " 
					+ "\""+desFile+ "\"";
			
			try {
				
				Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
				int runtimeComplete = runtimeProcess.waitFor();
				
				if(runtimeComplete == 0){
					JOptionPane.showMessageDialog(hv, "Backup Successful!\nYou can now save the file ("+desFile+") to a safe place or to your cloud storage."
							, "Success", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(hv, "Backup Failure", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(hv, e1.getMessage(), "Runtime Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void processRestore(){
		
		String dbName = "erms";
        String dbUser = "root";
        String dbPass = "root";
        
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SQL File", "sql","SQL");
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.addChoosableFileFilter(filter);
		
		int ret = jfc.showOpenDialog(hv);
		
		if(ret == JFileChooser.APPROVE_OPTION){
			File f = jfc.getSelectedFile();
			
			int response = JOptionPane.showConfirmDialog(hv, "Restoring database content from this file :\n"
					+f.getAbsolutePath()+"\n will replace the existing database on this server!\n"
					+ "Do You Want To Replace?"
					, "Restoring Database Content", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if(response == JOptionPane.YES_OPTION){
			/*
			String path = "D:"+File.separator+"J2EE19"+File.separator
						+ "mysql-5.7.13-winx64"+File.separator+"bin"+File.separator;
			*/
			String[] executeCmd = new String[]{"mysql", 
					dbName, 
					"-u" + dbUser, 
					"-p" + dbPass, 
					"-e", 
					" source " + "\""+f.getAbsolutePath()+"\""};
			
			try {
				Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
				int runtimeComplete = runtimeProcess.waitFor();
				
				if(runtimeComplete == 0){
					JOptionPane.showMessageDialog(hv, "The ERMS Database has been successfully restored", "Success", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(hv, "Restore Failure", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(hv, e1.getMessage(), "Runtime Error", JOptionPane.ERROR_MESSAGE);
			}
			
			}
		}
	}

}
