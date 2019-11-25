package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import utils.Checker;
import utils.Usage;
import model.Course;
import model.Marks;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

import controller.ButtonMouseListener;
import database.AttendDao;
import database.MarksDao;
import database.StudentDao;

public class AddMarkSection extends JPanel {
	
	private ArrayList<String> liSub,rollNos;
	
	private JTable tableAddedResults;
	private MyTableModel tableModel;
	private DefaultTableCellRenderer rend;
	
	private JLabel lblSelectStudent;
	private JTextField txtStudentID,txtStudentName;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private JLabel lblSubTitle;
	private JComboBox comboRollNo;
	private Course c;
	private String selectedRollNo;

	/**
	 * Create the panel.
	 */
	public AddMarkSection(ArrayList<String> listSub,ArrayList<String> rollNos,Course co) {
		this.liSub = listSub;
		this.rollNos = rollNos;
		this.c = co;
		
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Mark Section : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(220, 220, 220));
		prepareStudents();
	}
	
	public void prepareStudents(){
		removeAll();
		revalidate();
		repaint();
		if(this.liSub==null || this.rollNos==null || this.liSub.isEmpty() || this.rollNos.isEmpty()){
			setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("40dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("40dlu"),},
			new RowSpec[] {
				RowSpec.decode("40dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(65dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("40dlu"),}));
			
			JLabel l;
			if(this.liSub==null || this.liSub.isEmpty())
				l = new JLabel("No Course To Add Results To");
			else
				l = new JLabel("No Student Registered For The Selected Course");
			l.setForeground(Color.RED);
			l.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			add(l,"3, 3, center, fill");
			
		}else{
			int noOfSubs = this.liSub.size();
			
			ArrayList<ColumnSpec> colSpec = new ArrayList<ColumnSpec>();
			
			int i=1;
			colSpec.add(ColumnSpec.decode("15dlu"));
			while(true){
				if((noOfSubs<=4 && i==4)||(noOfSubs>4 && i==noOfSubs)){
					colSpec.add(ColumnSpec.decode("default:grow"));
					break;
				}
				colSpec.add(ColumnSpec.decode("default:grow"));
				colSpec.add(ColumnSpec.decode("10dlu"));
				i++;
			}
			colSpec.add(ColumnSpec.decode("15dlu"));
			
			RowSpec[] rowSpec = new RowSpec[]{
				RowSpec.decode("10dlu"),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("23dlu", false)), 0),
				RowSpec.decode("5dlu"),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("23dlu", false)), 0),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("23dlu", false)), 0),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("23dlu", false)), 0),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("23dlu", false)), 0),
				//RowSpec.decode("max(80dlu;default):grow"),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("80dlu", false), Sizes.constant("150dlu", false)), 1),
				//new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("23dlu", false)), 0),
				RowSpec.decode("10dlu"),
			};
			
			ColumnSpec[] colSpecArr = new ColumnSpec[colSpec.size()];
			
			for(int j=0;j<colSpecArr.length;j++){
				colSpecArr[j] = colSpec.get(j);
			}
			
			setLayout(new FormLayout(colSpecArr,rowSpec));
			
			add(getLblSelectStudent(),"2, 2, right, fill");
			add(getComboRollNo(),"4, 2, fill, fill");
			add(getTxtStudentID(),"6, 2, fill, fill");
			if(noOfSubs<5)
				add(getTxtStudentName(),"8, 2, fill, fill");
			else
				add(getTxtStudentName(),"8, 2, 3, 1, fill, fill");
			
			int d=1;
			for(String s:this.liSub){
				JLabel l = new JLabel(s);
				l.setForeground(Color.BLUE);
				l.setFont(new Font("Century Gothic", Font.PLAIN, 15));
				add(l,(d*2)+", 4, center, bottom");
				
				JTextField jtf = new JTextField();
				jtf.setForeground(UIManager.getColor("List.dropLineColor"));
				jtf.setFont(new Font("Century Gothic", Font.BOLD, 16));
				jtf.setColumns(10);
				jtf.setHorizontalAlignment(SwingConstants.CENTER);
				jtf.setAlignmentX(JTextField.CENTER_ALIGNMENT);
				add(jtf,(d*2)+", 5, fill, fill");
				d++;
			}
			
			int f = noOfSubs<4 ? 4:noOfSubs;
			add(getBtnAdd(),(f*2)+", 6, right, fill");
			add(getLblSubTitle(),"2, 7, "+(2*f-1)+", 1, center, bottom");
			add(getScrollPane(),"2, 8, "+(2*f-1)+", 1, fill, fill");
			
			rollNoSelected();
		}
	}
	
	public JLabel getLblSelectStudent() {
		if (lblSelectStudent == null) {
			lblSelectStudent = new JLabel("Select Student :");
			lblSelectStudent.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblSelectStudent;
	}
	
	public JComboBox getComboRollNo(){
		
			comboRollNo = new JComboBox(this.rollNos.toArray());
			comboRollNo.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			
			comboRollNo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					rollNoSelected();
				}
			});
			
			if(this.selectedRollNo != null)
				comboRollNo.setSelectedItem(this.selectedRollNo);
		
		return comboRollNo;
	}
	
	protected void rollNoSelected() {
		
		this.selectedRollNo =  comboRollNo.getSelectedItem().toString();
		
		String sid = AttendDao.getAttendStdID(c.getAcademic_year(), c.getMajor_code(), c.getYear(), this.selectedRollNo);
		
		String name = StudentDao.getStudentByID(sid).getStd_name();
		
		getTxtStudentID().setText(sid);
		getTxtStudentName().setText(name);
	}

	public JTextField getTxtStudentID() {
		if (txtStudentID == null) {
			txtStudentID = new JTextField();
			txtStudentID.setBackground(new Color(255, 255, 240));
			txtStudentID.setForeground(Color.MAGENTA);
			txtStudentID.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			txtStudentID.setEditable(false);
			txtStudentID.setColumns(10);
		}
		return txtStudentID;
	}
	public JTextField getTxtStudentName() {
		if (txtStudentName == null) {
			txtStudentName = new JTextField();
			txtStudentName.setBackground(new Color(255, 255, 240));
			txtStudentName.setForeground(Color.MAGENTA);
			txtStudentName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			txtStudentName.setEditable(false);
			txtStudentName.setColumns(10);
		}
		return txtStudentName;
	}
	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("+ Add");
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setFont(new Font("Century Gothic", Font.BOLD, 18));
			btnAdd.setFocusPainted(false);
			btnAdd.setBackground(new Color(0, 62, 62));
			btnAdd.addMouseListener(new ButtonMouseListener(btnAdd));
			btnAdd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!Checker.isAllEmpty(getMarks()))
						addMarks();
				}
			});
		}
		return btnAdd;
	}
	public JScrollPane getScrollPane() {
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(getTableAddedResults());
		
		return scrollPane;
	}
	
	public JLabel getLblSubTitle() {
		if (lblSubTitle == null) {
			lblSubTitle = new JLabel("Student Marks");
			lblSubTitle.setForeground(new Color(70, 130, 180));
			lblSubTitle.setFont(new Font("Century Gothic", Font.BOLD, 18));
		}
		return lblSubTitle;
	}
	
	public JTable getTableAddedResults() {
		
			tableAddedResults = new JTable(getTableModel());
			
			tableAddedResults.getColumnModel().getColumn(0).setMaxWidth(30);//No
			tableAddedResults.getColumnModel().getColumn(0).setMinWidth(30);
			tableAddedResults.getColumnModel().getColumn(1).setMaxWidth(75);//E-ID
			tableAddedResults.getColumnModel().getColumn(1).setMinWidth(75);
			tableAddedResults.getColumnModel().getColumn(2).setMaxWidth(75);//Roll
			tableAddedResults.getColumnModel().getColumn(2).setMinWidth(75);
			//tableAddedResults.getColumnModel().getColumn(3).setMaxWidth(120);//Name
			tableAddedResults.getColumnModel().getColumn(3).setMinWidth(120);
			tableAddedResults.getColumnModel().getColumn(this.liSub.size()+6-2).setMaxWidth(45); //Total Mark
			tableAddedResults.getColumnModel().getColumn(this.liSub.size()+6-2).setMinWidth(45);
			tableAddedResults.getColumnModel().getColumn(this.liSub.size()+6-1).setMaxWidth(50); //Action
			tableAddedResults.getColumnModel().getColumn(this.liSub.size()+6-1).setMinWidth(50);
			
			tableAddedResults.setRowSelectionAllowed(false);
			tableAddedResults.setColumnSelectionAllowed(false);
			tableAddedResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 222, 173));
			rend.setForeground(Color.BLUE);
			for(int i=0;i<this.liSub.size()+6;i++)
			tableAddedResults.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			for(int i=0;i<this.liSub.size()+6;i++)
				tableAddedResults.getTableHeader().getColumnModel().getColumn(i).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(new Color(32, 178, 170));
			tableAddedResults.getTableHeader().getColumnModel().getColumn(this.liSub.size()+6-2).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(Color.RED);
			tableAddedResults.getTableHeader().getColumnModel().getColumn(this.liSub.size()+6-1).setCellRenderer(rend);
			tableAddedResults.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					tableClicked();
				}
				
			});
			
		return tableAddedResults;
	}
	protected void tableClicked() {
		int rowIndex = tableAddedResults.getSelectedRow();
		int colIndex = tableAddedResults.getSelectedColumn();
		
		if(colIndex == this.liSub.size()+6-1){
			String std_id = tableAddedResults.getValueAt(rowIndex, 1).toString();
			String std_name = tableAddedResults.getValueAt(rowIndex, 3).toString();
			
			int ret = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the marks of "
					+ std_name+ "?", "Delete Marks", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(ret == JOptionPane.YES_OPTION){
				if(!MarksDao.deleteMarksByIDonAcy(c.getAcademic_year(), std_id, c.getSemester()))
					JOptionPane.showMessageDialog(null, "Error deleting Marks in database", "Delete Error", JOptionPane.ERROR_MESSAGE);
				else
					prepareStudents();
			}
		}
	}
	// ******** Own Code *********
	private class MyRecord{
		public Object[] field = new Object[liSub.size()+6];
	}
	
	public class MyTableModel extends AbstractTableModel{

		private String heading[];
		private ArrayList<MyRecord> data;
		
		public MyTableModel(){
			ArrayList<String> headingList = new ArrayList<String>();
			headingList.add("No.");
			headingList.add("Entrance ID");
			headingList.add("Roll No");
			headingList.add("Name");
			for(String co:liSub){
				headingList.add(co);
			}
			headingList.add("Total");
			headingList.add("Action");
			
			this.heading = new String[headingList.size()];
			int j=0;
			for(String st:headingList){
				this.heading[j++] = st;
			}
			this.data= getResultDatabase();
		}
		
		public void removeAll(){
			int size=this.data.size();
			this.data.clear();
			fireTableRowsDeleted(0,size);
		}
		public ArrayList<MyRecord> getAll(){
			return this.data;
		}
		public void removeRecord(int rowIndex){
			Object[] rcd = getSelectedRecord(rowIndex);
			MarksDao.deleteMarksByIDonAcy(c.getAcademic_year(), rcd[1].toString(), c.getSemester());
			fireTableDataChanged();
		}
		public Object[] getSelectedRecord(int rowIndex){		
			return this.data.get(rowIndex).field;
		}
		public void addRecord(Object[] fields){
			MyRecord r=new MyRecord();
			r.field=fields;
			this.data.add(r);
			fireTableDataChanged();
		}
		
		public ArrayList<MyRecord> getResultDatabase(){
			ArrayList<MyRecord> dts = new ArrayList<MyRecord>();
			ArrayList<Course> course = AttendDao.getAttendByAcMajYr(c.getAcademic_year(), c.getMajor_code(), c.getYear());
			
			Integer t=0;
			for(Course co:course){
				MyRecord r=new MyRecord();
				r.field[0] = ++t;
				r.field[1] = co.getSemester();
				r.field[2] = co.getSubjects();
				r.field[3] = StudentDao.getStudentByID(co.getSemester()).getStd_name();
				
				Marks mk = MarksDao.getMarksByRollNo(c.getAcademic_year(), c.getMajor_code(), c.getYear(), c.getSemester(), co.getSubjects());
				int d = 4;
				int total=0;
				if(mk == null){
					while(d<this.heading.length-1)
						r.field[d++] = " - ";
				}else{
					ArrayList<String> marksArr = Usage.separateSubCodes(mk.getMarks());
					for(String m:marksArr){
						if(m.matches("\\d{1,4}")){
							r.field[d++] = m;
							total+=Integer.parseInt(m);
						}else{
							r.field[d++] = " - ";
						}
					}
				}
				
				r.field[this.heading.length-2] = total;
				r.field[this.heading.length-1] = "Clear";
				dts.add(r);
			}
			return dts;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return this.heading.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.data.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int colIndex) {
			Object[] myRecord=this.data.get(rowIndex).field;
			return myRecord[colIndex];
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return this.heading[column];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if(columnIndex==3) return true;
			else return false;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			this.data.get(rowIndex).field[columnIndex]=aValue;
		}
		
	}
	
	public MyTableModel getTableModel(){
		tableModel = new MyTableModel();
		return tableModel;
	}
	
	public ArrayList<String> getMarks(){
		Component[] cs = getComponents();
		ArrayList<String> list = new ArrayList<String>();
		for(Component c:cs){
			if(c.getClass().getName().equals("javax.swing.JTextField") && ((JTextField)c).isEditable()){
				list.add(((JTextField)c).getText());
			}
		}
		return list;
	}
	
	public ArrayList<String> getListOfSubCodes() {
		return liSub;
	}

	public void setListOfSubCodes(ArrayList<String> liSub) {
		this.liSub = liSub;
	}
	
	public ArrayList<String> getListOfRollNos() {
		return rollNos;
	}

	public void setListOfRollNos(ArrayList<String> rollNos) {
		this.rollNos = rollNos;
	}
	
	public void setCourse(Course c){
		this.c = c;
	}
	
	public Course getCourse(){
		return this.c;
	}
	
	private void addMarks(){
		ArrayList<String> mks = getMarks();
		ArrayList<String> warnings = new ArrayList<String>();
		
		for(int i=0;i<mks.size();i++){
			if(Checker.checkRequired(mks.get(i)) || !mks.get(i).matches("\\d{1,4}")){
				mks.remove(i);
				mks.add(i, "xx");
			}
		}
		
		if(warnings.isEmpty()){
			Marks m = new Marks();
			m.setAcademic_year(c.getAcademic_year());
			m.setMajor_code(c.getMajor_code());
			m.setYear(c.getYear());
			m.setRollno(this.selectedRollNo);
			m.setSemester(c.getSemester());
			m.setStd_id(getTxtStudentID().getText());
			m.setSubjects(Usage.concatSubCodes(liSub));
			m.setMarks(Usage.concatSubCodes(mks));
			//System.out.println(m);
			if(MarksDao.saveMarks(m))
				prepareStudents();
			else
				JOptionPane.showMessageDialog(null, "Error saving Marks in database", "Error", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, warnings.get(0), "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
