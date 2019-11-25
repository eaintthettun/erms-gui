package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import database.ChangeOfSubDao;
import database.SubjectDao;

public class SubjectListPane extends JPanel {
	
	private ArrayList<String> li;
	private String acy;

	/**
	 * Create the panel.
	 */
	public SubjectListPane(ArrayList<String> list) {
		this.li = list;
		
		setBackground(new Color(220, 220, 220));
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		prepareSubjects();
	}
	
	public void prepareSubjects(){
		removeAll();
		revalidate();
		repaint();
		if(this.li==null || this.li.isEmpty()){
			setLayout(new GridLayout(1,1,5,5));
			JLabel l = new JLabel("This Course Hasn't Created Yet");
			l.setHorizontalAlignment(SwingConstants.CENTER);
			l.setForeground(Color.RED);
			l.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			add(l);
		}else{
			setLayout(new GridLayout(0,2,5,5));
			JLabel l1 = new JLabel("        Subject Code");
			l1.setForeground(Color.BLUE);
			l1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			add(l1);
			JLabel l2 = new JLabel("            Subject Name");
			l2.setForeground(Color.BLUE);
			l2.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			add(l2);
			
			for(String s:this.li){
				JLabel l3 = new JLabel("        "+s);
				designLabel(l3);
				add(l3);
				
				JLabel l4 = new JLabel(ChangeOfSubDao.getSubjectName(s, acy));
				designLabel(l4);
				add(l4);
			}
		}
	}
	
	private void designLabel(JLabel l){
		l.setForeground(new Color(70, 130, 180).darker());
		l.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	}

	public ArrayList<String> getListOfSubCodes() {
		return li;
	}

	public void setListOfSubCodes(ArrayList<String> li) {
		this.li = li;
	}
	
	public String getAcy(){
		return this.acy;
	}
	
	public void setAcy(String acy){
		this.acy = acy;
	}

}
