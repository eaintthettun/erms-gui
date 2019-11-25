package controller;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import database.StudentDao;
import model.Student;
import utils.Usage;
import view.SearchView;

public class SearchController {
	
	private SearchView view;
	private Student std;
	private MainController mc;
	
	public SearchController(SearchView v,MainController mc){
		this.view = v;
		this.mc = mc;
		
		this.view.getBtnGo().addActionListener(new SearchActionListener());
		this.view.getBtnGo().addMouseListener(new ButtonMouseListener(this.view.getBtnGo()));
		this.view.getTextField().addActionListener(new SearchActionListener());
		this.view.getLabelMsg().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(view.getLabelMsg().getText()!="" && !view.getLabelMsg().getText().equals("No Items Match Your Search"))
				{
					SearchController.this.mc.prepareStudentUpdate(view.getTextField().getText());
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				if(!view.getLabelMsg().getText().equals("No Items Match Your Search"))
				view.getLabelMsg().setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				if(!view.getLabelMsg().getText().equals("No Items Match Your Search"))
				view.getLabelMsg().setForeground(SystemColor.textHighlight);
			}
			
		});
		
		this.view.getLabelResult().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(!(view.getLabelResult().getText()=="" || 
						view.getLabelResult().getText().isEmpty()||
						view.getLabelMsg().getText().equals("No Items Match Your Search")))
				{
					Usage.prepareResult(std==null ? null:std.getStd_id());
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				if(!view.getLabelMsg().getText().equals("No Items Match Your Search"))
				view.getLabelResult().setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				if(!view.getLabelMsg().getText().equals("No Items Match Your Search"))
				view.getLabelResult().setForeground(SystemColor.textHighlight);
			}
			
		
		});
		
		this.view.getTextField().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				view.getLabelMsg().setText("");
				view.getLabelResult().setText("");
			}
		});
	}
	
	private class SearchActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getLabelMsg().setText("");
			view.getLabelMsg().setForeground(SystemColor.textHighlight);
			view.getLabelResult().setText("");
			view.getLabelResult().setForeground(SystemColor.textHighlight);
			if(view.getRdbtnName().isSelected()){
				
				ArrayList<Student> stds = StudentDao.getStudentByName(view.getTextField().getText());
				if(stds == null)
					std = null;
				else
					std = stds.get(0);
			}
			else{
				std = StudentDao.getStudentByID(view.getTextField().getText());
			}
			if(std != null){
				view.getLabelMsg().setText("Click Here for "+std.getStd_name()+"'s full profile");
				if(view.getRdbtnId().isSelected())
					view.getLabelResult().setText("Click Here for "+std.getStd_name()+"'s Academic Records");
				else
					view.getLabelResult().setText("");
			}else{
				view.getLabelResult().setText("");
				view.getLabelMsg().setText("No Items Match Your Search");
				view.getLabelMsg().setForeground(Color.RED);
			}
		}
	}

}
