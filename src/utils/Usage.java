package utils;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import view.SingleResultTab;
import controller.ShowResultsController;
import database.Dao;

public class Usage {
	
	public static boolean setIcon(JButton b, String iconName){
		byte[] data = Dao.getPicture(iconName);
		if(data!=null){
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			try {
				BufferedImage img = ImageIO.read(bais);
				img = resize(img);
				img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(img);
				b.setIcon(icon);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else
		return true;
	}
	
	private static BufferedImage resize(BufferedImage bufImg) {
		BufferedImage resized = new BufferedImage(40, 40,bufImg.getType());
		Graphics2D g = resized.createGraphics();
		g.drawImage(bufImg, 0, 0, 40, 40, null);
		g.dispose();
		return resized;
	}
	
	public static ArrayList<String> separateSubCodes(String subCodeArr){
		ArrayList<String> subCodes = new ArrayList<String>();
		if(subCodeArr==null) return new ArrayList<String>();
		StringTokenizer token = new StringTokenizer(subCodeArr, "|");
		while(token.hasMoreTokens()){
			subCodes.add(token.nextToken());
		}
		return subCodes;
	}
	
	public static String concatSubCodes(ArrayList<String> list){
		
		if(list==null || list.isEmpty()) return null;
		
		String str = "";
		for(String s:list){
			str = str+s+"|";
		}
		str = str.endsWith("|") ? str.substring(0,str.length()-1):str;
		return str;
	}
	
	public static void removeReduntantSubject(ArrayList<String> subList, ArrayList<String> markList){
		for(int i=0;i<markList.size();i++){
			if(markList.get(i).equals("xx")){
				markList.remove(i);
				subList.remove(i);
			}
		}
	}
	
	public static String getResultType(ArrayList<String> subList,ArrayList<String> markList){
		for(int i=0;i<markList.size();i++){
			if(markList.get(i).equals("xx")){
				markList.remove(i);
				subList.remove(i);
			}
		}
		
		boolean failed = false;
		int distCount = 0;
		int totalMark = 0;
		int avg;
		
		for(int i=0;i<markList.size();i++){
			int m = Integer.parseInt(markList.get(i));
			String sub = subList.get(i);
			
			if(m<50) failed = true;
			
			if(m<=100){
				if(sub.startsWith("M-")||sub.startsWith("E-")||sub.startsWith("HSS-")){
					if(m>=65) distCount++;
				}else{
					if(m>=70) distCount++;
				}
			}
			
			totalMark+=m;
		}
		avg = totalMark/markList.size();
		
		String result = null;
		if(failed)
			result = "Failed";
		else{
			if(avg>=80 && distCount==subList.size())
				result = "Passed with Distinction";
			else
				result = "Passed";
		}
		
		return result;
	}
	
	public static String getResultType(ArrayList<String> subList,ArrayList<String> markList, String year){
		
		removeReduntantSubject(subList, markList);
		
		boolean failed = false;
		int distCount = 0;
		int totalMark = 0;
		int avg;
		int yr = Integer.parseInt(year);
		
		for(int i=0;i<markList.size();i++){
			int m = Integer.parseInt(markList.get(i));
			String sub = subList.get(i);
			
			if(m<50) failed = true;
			
			if(m<=100){
				if(sub.startsWith("M-")||sub.startsWith("E-")||sub.startsWith("HSS-")){
					if(m>=65) distCount++;
				}else{
					if(m>=70) distCount++;
				}
			}
			
			totalMark+=m;
		}
		avg = totalMark/markList.size();
		
		String result = null;
		if(failed)
			result = "Failed";
		else{
			if(avg>=80 && distCount==subList.size() && yr<=6)
				result = "Passed with Distinction";
			else
				result = "Passed";
		}
		
		return result;
	}
	
	public static int getTotalMark(ArrayList<String> subList,ArrayList<String> markList){
		
		removeReduntantSubject(subList, markList);
		
		int totalMark = 0;
		
		for(int i=0;i<markList.size();i++){
			int m = Integer.parseInt(markList.get(i));
			totalMark+=m;
		}
		
		return totalMark;
		
	}
	
	public static int getTotalMark(String subListStr,String markListStr){
		ArrayList<String> subList = Usage.separateSubCodes(subListStr);
		ArrayList<String> markList = Usage.separateSubCodes(markListStr);
		return getTotalMark(subList, markList);
	}
	
	public static String getTotalAndMax(ArrayList<String> subList,ArrayList<String> markList){
		
		int totalMark = 0;
		int over100Count = 0;
		int maxMark;
		
		removeReduntantSubject(subList, markList);
		
		for(int i=0;i<markList.size();i++){
			int m = Integer.parseInt(markList.get(i));
			
			if(m>100) over100Count++;
			
			totalMark+=m;
		}
		
		maxMark = (markList.size()-over100Count)*100 + over100Count*1000;
		
		return totalMark+" / "+maxMark;
	}
	
	public static String getTotalAndMax(String subListStr,String markListStr){
		ArrayList<String> subList = Usage.separateSubCodes(subListStr);
		ArrayList<String> markList = Usage.separateSubCodes(markListStr);
		return getTotalAndMax(subList, markList);
	}
	
	public static void setFrameIcon(JFrame frame){
		byte[] data = Dao.getPicture("ErmsLogo");
		if(data!=null){
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			try {
				BufferedImage img = ImageIO.read(bais);
				img = resizeFrameIcon(img);
				img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(img);
				frame.setIconImage(icon.getImage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static BufferedImage resizeFrameIcon(BufferedImage bufImg) {
		BufferedImage resized = new BufferedImage(100, 100,bufImg.getType());
		Graphics2D g = resized.createGraphics();
		g.drawImage(bufImg, 0, 0, 100, 100, null);
		g.dispose();
		return resized;
	}
	
	public static void prepareResult(final String stdId){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					
					Usage.setFrameIcon(frame);
					
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setBounds(250, 15, 1080, 700);
					
					SingleResultTab tab = new SingleResultTab();
					JScrollPane jsp = new JScrollPane();
					jsp.setViewportView(tab);
					frame.add(jsp);
					new ShowResultsController(stdId, tab);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
