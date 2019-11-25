package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class ResultView extends JTabbedPane{
	
	private SingleResultTab srst;
	private MultipleResultTab mrst;
	
	public ResultView(){
		setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		setForeground(new Color(70, 130, 180));
		
		JScrollPane j1 = new JScrollPane();
		j1.setViewportView(getSingleResultTab());
		addTab("Student Academic Records", j1);
		
		JScrollPane j2 = new JScrollPane();
		j2.setViewportView(getMultipleResultTab());
		addTab("Yearly Results", j2);
	}
	
	public SingleResultTab getSingleResultTab(){
		if(srst == null){
			srst = new SingleResultTab();
		}
		return srst;
	}
	public MultipleResultTab getMultipleResultTab(){
		if(mrst == null){
			mrst = new MultipleResultTab();
		}
		return mrst;
	}

}
