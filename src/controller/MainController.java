package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import database.AdminDao;
import database.CurrentAdminDao;
import utils.Checker;
import utils.MyConstants;
import utils.Usage;
import view.AcademicYearView;
import view.AddNewCourseView;
import view.AddNewResultsView;
import view.ButtonPane;
import view.HomeView;
import view.MainFrame;
import view.MenuPane;
import view.ResultView;
import view.RightPane;
import view.SearchView;
import view.ShowAllStudentsView;
import view.StudentView;
import view.WelcomeView;

public class MainController {
	
	private MainFrame frame;
	private WelcomeView myWelcomeView;
	private HomeView myHomeView;
	private MenuPane myMenuPane;
	private JLabel title;
	private ButtonPane myButtonPane;
	private RightPane myRightPane;
	private StudentView myStudentView;
	private SearchView mySearchView;
	private ShowAllStudentsView sasv;
	private AddNewCourseView ancv;
	private AcademicYearView ayv;
	private AddNewResultsView anrv;
	private ResultView rsv;
	
	public static int MENU_NO;

	public MainController(MainFrame frame) {
		this.frame = frame;
		Usage.setFrameIcon(this.frame);
		this.frame.add(getYTUtitle(),BorderLayout.NORTH);
		
		if(Checker.checkAdminData())
			prepareWelcomeView();
		else
			prepareSignUpView();
	}
	
	private void clearOldView()
	{
		this.frame.getContentPane().removeAll();
		this.frame.revalidate();
		this.frame.repaint();
		
		this.frame.add(getYTUtitle(),BorderLayout.NORTH);
		this.myMenuPane = new MenuPane();
		this.myMenuPane.setVisible(true);
		this.frame.add(this.myMenuPane,BorderLayout.WEST);
		this.frame.validate();
		this.frame.repaint();
		new MenuController(this,this.myMenuPane);
	}
	
	public JLabel getYTUtitle(){
		if(title == null){
			title = new JLabel("Yangon Technological University");
			title.setForeground(Color.BLUE.darker().darker());
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setFont(new Font("Century Gothic", Font.BOLD, 28));
			title.setVisible(true);
		}
		return title;
	}
	
	protected void funAfterLogin(String name){
		CurrentAdminDao.addCurrentAdmin(name);
	}
	protected void funAfterLogout(){
		prepareWelcomeView();
		CurrentAdminDao.removeCurrentAdmin();
	}

	protected void prepareWelcomeView() {
		
		this.frame.getContentPane().removeAll();
		this.frame.revalidate();
		this.frame.repaint();
		
		this.frame.add(getYTUtitle(),BorderLayout.NORTH);
		
		this.myWelcomeView = new WelcomeView();
		this.myWelcomeView.setVisible(true);
		this.frame.add(this.myWelcomeView,BorderLayout.CENTER);
		this.frame.validate();
		this.frame.repaint();
		this.myWelcomeView.getLblCreate().setVisible(false);
		this.myWelcomeView.getLblCreate().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				prepareSignUpView();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				myWelcomeView.getLblCreate().setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				myWelcomeView.getLblCreate().setForeground(Color.BLUE);
			}
			
		});
		this.myWelcomeView.getBtnLogin().addActionListener(
				new LoginActionListener(this.myWelcomeView.getBtnLogin().getText()));
		this.myWelcomeView.getTextFieldUsername().addActionListener(
				new LoginActionListener(this.myWelcomeView.getBtnLogin().getText()));
		this.myWelcomeView.getPasswordField().addActionListener(
				new LoginActionListener(this.myWelcomeView.getBtnLogin().getText()));
		this.myWelcomeView.getBtnLogin().addMouseListener(new ButtonMouseListener(this.myWelcomeView.getBtnLogin()));
	}
	
	protected void prepareSignUpView()
	{
		
		this.myWelcomeView = new WelcomeView();
		this.myWelcomeView.setVisible(true);
		this.myWelcomeView.getLabelAsk().setText("Create An Account");
		this.myWelcomeView.getBtnLogin().setText("Sign Up");
		
		this.myWelcomeView.getLblCreate().setText("Already have an account? Login");
		this.myWelcomeView.getLblCreate().setVisible(true);
		this.myWelcomeView.getLblCreate().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(Checker.checkAdminData())
					prepareWelcomeView();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				myWelcomeView.getLblCreate().setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				myWelcomeView.getLblCreate().setForeground(Color.BLUE);
			}
			
		});
		
		this.frame.add(this.myWelcomeView,BorderLayout.CENTER);
		
		this.myWelcomeView.getBtnLogin().addActionListener(
				new LoginActionListener(this.myWelcomeView.getBtnLogin().getText()));
		this.myWelcomeView.getTextFieldUsername().addActionListener(
				new LoginActionListener(this.myWelcomeView.getBtnLogin().getText()));
		this.myWelcomeView.getPasswordField().addActionListener(
				new LoginActionListener(this.myWelcomeView.getBtnLogin().getText()));
	}
	
	private void processSignUp() {
		
		String name= this.myWelcomeView.getTextFieldUsername().getText();
		String password= new String(this.myWelcomeView.getPasswordField().getPassword());
		ArrayList<String> errors = new ArrayList<String>();

		checkInput(name, password, errors);
		
		if(errors.isEmpty()||errors==null)
		{
			if(!AdminDao.checkLoginDB(name, password) && AdminDao.createAdmin(name, password))
			{
				funAfterLogin(name);
				prepareHomeView();
			}
			else
				JOptionPane.showMessageDialog(myWelcomeView, "Cannot create an account!\n"
						+ "User might have already existed","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			String message = "";
			for(String e:errors)
				message = message+e+"\n";
			JOptionPane.showMessageDialog(myWelcomeView, message,"Warnings",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void processLogin() {
		
		String name= this.myWelcomeView.getTextFieldUsername().getText();
		String password= new String(this.myWelcomeView.getPasswordField().getPassword());
		ArrayList<String> errors = new ArrayList<String>();
		
		checkInput(name,password,errors);
		
		if(errors.isEmpty()||errors==null)
		{
			if(AdminDao.checkLoginDB(name, password))
			{
				funAfterLogin(name);
				prepareHomeView();		
			}
			else
				JOptionPane.showMessageDialog(myWelcomeView, "Unable to login! Please try again.","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			String message = "";
			for(String e:errors)
				message = message+e+"\n";
			JOptionPane.showMessageDialog(myWelcomeView, message,"Warnings",JOptionPane.WARNING_MESSAGE);
		}
	}

	private void checkInput(String name, String password,
			ArrayList<String> errors) {
		
		if(Checker.checkRequired(name))
			errors.add(MyConstants.msgRequired("Name"));
		if(Checker.checkRequired(password))
			errors.add(MyConstants.msgRequired("Password"));
		
		else if(Checker.checkTextLength(name))
			errors.add(MyConstants.msgLength("Name", 50));
		
		else if(Checker.checkPasswordLength(password, 4, 12))
			errors.add(MyConstants.msgLength("Password", 4, 12));
		
	}
	
	private class LoginActionListener implements ActionListener{

		private String btnName;
		public LoginActionListener(String name){
			this.btnName=name;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(this.btnName.equals("Login")){
				processLogin();
			}
			if(this.btnName.equals("Sign Up"))
				processSignUp();
		}
	}
	
	private void prepareStudentForm(){
		clearOldView();
		
		this.myButtonPane = new ButtonPane();
		this.myRightPane = new RightPane();
		this.myStudentView = new StudentView();
		
		this.myButtonPane.setVisible(true);
		this.myRightPane.setVisible(true);
		this.myStudentView.setVisible(true);
		
		JScrollPane jsp1 = new JScrollPane();
		jsp1.setVisible(true);
		jsp1.setViewportView(this.myStudentView);
		this.frame.add(jsp1,BorderLayout.CENTER);
		
		JScrollPane jsp2 = new JScrollPane();
		jsp2.setVisible(true);
		jsp2.setViewportView(this.myRightPane);
		this.frame.add(jsp2,BorderLayout.EAST);
		
		this.frame.add(this.myButtonPane,BorderLayout.SOUTH);
		
		this.frame.validate();
		this.frame.repaint();
	}
	
	
	/**
	 * 
	 * For Menu Action Starts here
	 * 
	 */
	
	protected void prepareHomeView() {
		MENU_NO = 1;
		
		clearOldView();
		
		this.myHomeView = new HomeView();
		this.myHomeView.setVisible(true);
		this.frame.add(this.myHomeView,BorderLayout.CENTER);
		this.frame.validate();
		this.frame.repaint();
		new HomeController(this.myHomeView,this);

	}
	
	protected void prepareRegistration(){
		MENU_NO = 2;
		prepareStudentForm();
		new RegistrationController(this.myButtonPane,this.myRightPane,this.myStudentView);
	}
	
	protected void prepareShowAllStudents(){
		MENU_NO = 3;
		clearOldView();
		
		this.sasv = new ShowAllStudentsView();
		this.sasv.setVisible(true);
		this.frame.add(this.sasv,BorderLayout.CENTER);
		this.frame.validate();
		this.frame.repaint();
		new ShowAllStdController(this.sasv,this);
	}
	
	protected void prepareStudentInfo(){
		MENU_NO = 4;
		clearOldView();
		this.mySearchView = new SearchView();
		this.mySearchView.setVisible(true);
		this.frame.add(this.mySearchView,BorderLayout.CENTER);
		this.frame.validate();
		this.frame.repaint();
		new SearchController(this.mySearchView, this);
	}
	protected void prepareStudentInfo1(String id){
		prepareStudentInfo();
		this.mySearchView.getTextField().setText(id);
		if(Checker.isID(id))
			this.mySearchView.getRdbtnId().setSelected(true);
		else
			this.mySearchView.getRdbtnName().setSelected(true);
	}
	protected void prepareStudentUpdate(String id){
		prepareStudentForm();
		new RegistrationUpdateController(this,this.myButtonPane,this.myRightPane,this.myStudentView,id);
	}
	
	protected void prepareAddNewCourse(){
		MENU_NO = 5;
		clearOldView();
		
		this.ancv = new AddNewCourseView();
		this.ancv.setVisible(true);
		this.frame.add(this.ancv,BorderLayout.CENTER);
		this.frame.validate();
		this.frame.repaint();
		new AddNewCourseController(this.ancv);
	}
	
	protected void prepareAcademicYear(){
		MENU_NO = 6;
		clearOldView();
		
		this.ayv = new AcademicYearView();
		this.ayv.setVisible(true);
		this.frame.add(this.ayv,BorderLayout.CENTER);
		this.frame.validate();
		this.frame.repaint();
		new AcademicYearController(this.ayv);
	}
	
	protected void prepareAddNewResults(){
		MENU_NO = 7;
		clearOldView();
		
		this.anrv = new AddNewResultsView();
		JScrollPane j = new JScrollPane();
		j.setViewportView(this.anrv);
		this.frame.add(j,BorderLayout.CENTER);
		this.frame.validate();
		this.frame.repaint();
		new AddNewResultsController(this.anrv);
	}
	
	protected void prepareResultView(){
		MENU_NO = 8;
		clearOldView();
		
		this.rsv = new ResultView();
		this.frame.add(this.rsv,BorderLayout.CENTER);
		this.frame.validate();
		this.frame.repaint();
		new ShowResultsController(this.rsv.getSingleResultTab(),this.rsv.getMultipleResultTab());
	}
}
