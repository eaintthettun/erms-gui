package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import utils.OfficeUsage;
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

public class MarkSectionForResult extends JPanel {
	
	private ArrayList<String> liSub,rollNos;
	
	private JTable tableAddedResults;
	private MyTableModel tableModel;
	private DefaultTableCellRenderer rend;
	
	private JScrollPane scrollPane;
	private JLabel lblSubTitle;
	private JButton btnExpOne,btnExpAll,btnReport;
	private Course c;
	private String selectedRollNo;

	/**
	 * Create the panel.
	 */
	public MarkSectionForResult(ArrayList<String> listSub,ArrayList<String> rollNos,Course co) {
		this.liSub = listSub;
		this.rollNos = rollNos;
		this.c = co;
		
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Mark Section : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(220, 220, 220));
		prepareStudents();
	}
	
	public void prepareStudents(){
		this.selectedRollNo = null;
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
				RowSpec.decode("30dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(65dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("30dlu"),}));
			
			JLabel l;
			if(this.liSub==null || this.liSub.isEmpty())
				l = new JLabel("No Available Course To Show Results");
			else
				l = new JLabel("No Student Registered For The Selected Course");
			l.setForeground(Color.RED);
			l.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			add(l,"3, 3, center, fill");
			
		}else{
			
			ArrayList<ColumnSpec> colSpec = new ArrayList<ColumnSpec>();
			
			colSpec.add(ColumnSpec.decode("15dlu"));
			colSpec.add(ColumnSpec.decode("default:grow"));
			colSpec.add(ColumnSpec.decode("5dlu"));
			colSpec.add(ColumnSpec.decode("default:grow"));
			colSpec.add(ColumnSpec.decode("5dlu"));
			colSpec.add(ColumnSpec.decode("default:grow"));
			colSpec.add(ColumnSpec.decode("5dlu"));
			colSpec.add(ColumnSpec.decode("default:grow"));
			colSpec.add(ColumnSpec.decode("15dlu"));
			
			RowSpec[] rowSpec = new RowSpec[]{
				RowSpec.decode("10dlu"),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("23dlu", false)), 0),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("80dlu", false), Sizes.constant("150dlu", false)), 1),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("25dlu", false), Sizes.constant("28dlu", false)), 0),
				RowSpec.decode("10dlu"),
			};
			
			ColumnSpec[] colSpecArr = new ColumnSpec[colSpec.size()];
			
			for(int j=0;j<colSpecArr.length;j++){
				colSpecArr[j] = colSpec.get(j);
			}
			
			setLayout(new FormLayout(colSpecArr,rowSpec));
			
			add(getLblSubTitle(),"2, 2, 7, 1, center, top");
			add(getScrollPane(),"2, 3, 7, 1, fill, fill");
			add(getBtnReport(),"8, 5, left, fill");
			add(getBtnExpOne(),"6, 5, center, fill");
			add(getBtnExpAll(),"4, 5, right, fill");
			
		}
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
	
	public JButton getBtnExpOne(){
		if(btnExpOne == null){
			btnExpOne = new JButton("Export Marks");
			designButton(btnExpOne);
			Usage.setIcon(btnExpOne, "excel");
		}
		return btnExpOne;
	}
	public JButton getBtnExpAll(){
		if(btnExpAll == null){
			btnExpAll = new JButton("Export All");
			designButton(btnExpAll);
			Usage.setIcon(btnExpAll, "excel");
		}
		return btnExpAll;
	}
	public JButton getBtnReport(){
		if(btnReport == null){
			btnReport = new JButton("Report Marks");
			designButton(btnReport);
			Usage.setIcon(btnReport, "word");
		}
		return btnReport;
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
			tableAddedResults.getColumnModel().getColumn(this.liSub.size()+6-1).setMaxWidth(70); //Remark
			tableAddedResults.getColumnModel().getColumn(this.liSub.size()+6-1).setMinWidth(70);
			
			tableAddedResults.setRowSelectionAllowed(true);
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
			rend.setForeground(new Color(70, 130, 180));
			tableAddedResults.getTableHeader().getColumnModel().getColumn(this.liSub.size()+6-2).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(new Color(147, 112, 219));
			tableAddedResults.getTableHeader().getColumnModel().getColumn(this.liSub.size()+6-1).setCellRenderer(rend);
			/*
			tableAddedResults.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					tableClicked();
				}
				
			});
			*/
		return tableAddedResults;
	}
	/*
	protected void tableClicked() {
		this.selectedRollNo = tableAddedResults.getValueAt(tableAddedResults.getSelectedRow(), 2).toString();
	}*/
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
			headingList.add("Remark");
			
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

					r.field[this.heading.length-2] = total;
					String res = Usage.getResultType(Usage.separateSubCodes(mk.getSubjects()), Usage.separateSubCodes(mk.getMarks()));
					r.field[this.heading.length-1] = mk.getYear().compareTo("6")<=0 && mk.getYear().compareTo("1")>=0 ? (res.equals("Passed with Distinction") ? "Distinction":res):(res.equals("Failed") ? "Failed":"Passed");
				}
				
				dts.add(r);
			}
			return dts;
		}

		@Override
		public int getColumnCount() {
			
			return this.heading.length;
		}

		@Override
		public int getRowCount() {
			
			return this.data.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int colIndex) {
			Object[] myRecord=this.data.get(rowIndex).field;
			return myRecord[colIndex];
		}

		@Override
		public String getColumnName(int column) {
			
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
	
	private void designButton(JButton b){
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Century Gothic", Font.BOLD, 18));
		b.setFocusPainted(false);
		b.setBackground(new Color(0, 62, 62));
		b.addMouseListener(new ButtonMouseListener(b));
		b.addActionListener(new MyBtnListener());
	}
	
	private class MyBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(tableAddedResults.getSelectedRow()!=-1)
				selectedRollNo = tableAddedResults.getValueAt(tableAddedResults.getSelectedRow(), 2).toString();
			
			if(e.getSource().equals(btnReport) && selectedRollNo!=null){

				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Word Document", "doc","docx");
				jfc.setAcceptAllFileFilterUsed(false);
				jfc.addChoosableFileFilter(filter);
				
				int ret = jfc.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					
					Marks mk = MarksDao.getMarksByRollNo(c.getAcademic_year(), c.getMajor_code(), c.getYear(), c.getSemester(), selectedRollNo);
					if(OfficeUsage.reportMarkToWord(mk, f))
						JOptionPane.showMessageDialog(null, "Successfully Reported to Microsoft Word!", 
								"Report Success", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Unable to Report to Microsoft Word!", 
								"Report Failure", JOptionPane.ERROR_MESSAGE);
				}
			
			}else if(e.getSource().equals(btnExpOne) && selectedRollNo!=null){

				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel Worksheet", "xls","xlsx");
				jfc.setAcceptAllFileFilterUsed(false);
				jfc.addChoosableFileFilter(filter);
				
				int ret = jfc.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					
					ArrayList<Marks> mks = new ArrayList<Marks>();
					mks.add(MarksDao.getMarksByRollNo(c.getAcademic_year(), c.getMajor_code(), c.getYear(), c.getSemester(), selectedRollNo));
					if(OfficeUsage.exportMarksToExcel(mks, f))
						JOptionPane.showMessageDialog(null, "Successfully Exported to Excel!", 
								"Export Success", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Unable to Export to Excel!", 
								"Export Failure", JOptionPane.ERROR_MESSAGE);
				}
			
			}else if(e.getSource().equals(btnExpAll)){

				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel Worksheet", "xls","xlsx");
				jfc.setAcceptAllFileFilterUsed(false);
				jfc.addChoosableFileFilter(filter);
				
				int ret = jfc.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					
					ArrayList<Marks> mks = MarksDao.getMarksBy(c.getAcademic_year(), c.getMajor_code(), c.getYear(), c.getSemester());
					if(OfficeUsage.exportMarksToExcel(mks, f))
						JOptionPane.showMessageDialog(null, "Successfully Exported to Excel!", 
								"Export Success", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Unable to Export to Excel!", 
								"Export Failure", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		}
		
	}
}
