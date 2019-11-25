package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import utils.Checker;
import utils.Usage;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

import database.StudentDao;
import model.Marks;

public class AcademicRecordSection extends JPanel {
	
	private ArrayList<Marks> records;
	
	private JTable tableAddedResults;
	private MyTableModel tableModel;
	private DefaultTableCellRenderer rend;
	
	private JLabel lblSubTitle;

	/**
	 * Create the panel.
	 */
	public AcademicRecordSection() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Academic Records : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(220, 220, 220));
		prepareRecords();
	}
	
	public void setRecords(ArrayList<Marks> r){
		this.records = r;
	}
	public ArrayList<Marks> getRecords(){
		return this.records;
	}
	
	public void prepareRecords(){
		removeAll();
		revalidate();
		repaint();
		if(records == null || records.isEmpty()){
			setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("70dlu"),
					ColumnSpec.decode("default:grow"),
					ColumnSpec.decode("70dlu"),},
				new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("max(65dlu;default):none"),
					FormFactory.RELATED_GAP_ROWSPEC,}));
			
			JLabel l = new JLabel("No Available Academic Records");
			l.setForeground(Color.RED);
			l.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			add(l,"2, 2, center, fill");
			
		}else{
			setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("20dlu"),
					ColumnSpec.decode("default:grow"),
					ColumnSpec.decode("20dlu"),},
				new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("23dlu", false)), 0),
					new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("40dlu", false), Sizes.constant("150dlu", false)), 0),
					RowSpec.decode("10dlu"),}));
			
			add(getLblSubTitle(),"2, 2, center, top");
			JScrollPane jsp = new JScrollPane();
			jsp.setViewportView(getTableAddedResults());
			add(jsp,"2, 3, fill, fill");
			
			addRecords();
		}
	}
	
	private void addRecords(){
		getTableModel().removeAll();
		int i=0;
		for(Marks mk:this.records){
			Object o[] = new Object[9];
			o[0] = ++i;
			o[1] = mk.getAcademic_year();
			o[2] = mk.getSemester().equals("1")? "First":"Second";
			o[3] = mk.getRollno();
			o[4] = Usage.getTotalAndMax(mk.getSubjects(), mk.getMarks());
			ArrayList<String> subList = Usage.separateSubCodes(mk.getSubjects());
			ArrayList<String> markList = Usage.separateSubCodes(mk.getMarks());
			Usage.removeReduntantSubject(subList, markList);
			int noOfE=0;
			for(int j=0;j<subList.size();j++){
				if(Checker.isExcellent(subList.get(j), Integer.parseInt(markList.get(j))))
					noOfE++;
			}
			o[5] = Integer.parseInt(mk.getYear())>6 ? " - ":noOfE;
			o[6] = Usage.getResultType(subList, markList, mk.getYear());
			o[7] = "Detail";
			o[8] = "Delete";
			
			getTableModel().addRecord(o);
		}
	}
	
	public JLabel getLblSubTitle() {
		String name = StudentDao.getStudentByID(this.records.get(0).getStd_id()).getStd_name();
		lblSubTitle = new JLabel(name.endsWith("s") ? name+"' Academic Records":name+"'s Academic Records");
		lblSubTitle.setForeground(new Color(70, 130, 180));
		lblSubTitle.setFont(new Font("Century Gothic", Font.BOLD, 18));
		return lblSubTitle;
	}
	
	public JTable getTableAddedResults() {
		if(tableAddedResults == null){
			tableAddedResults = new JTable(getTableModel());
			
			tableAddedResults.getColumnModel().getColumn(0).setMaxWidth(50);//No
			tableAddedResults.getColumnModel().getColumn(0).setMinWidth(50);
			
			tableAddedResults.getColumnModel().getColumn(1).setMinWidth(100);//Acy
			tableAddedResults.getColumnModel().getColumn(2).setMaxWidth(80);//Sem
			tableAddedResults.getColumnModel().getColumn(2).setMinWidth(80);
			tableAddedResults.getColumnModel().getColumn(3).setMaxWidth(130);//Roll No
			tableAddedResults.getColumnModel().getColumn(3).setMinWidth(130);
			tableAddedResults.getColumnModel().getColumn(4).setMaxWidth(100); //Total Mark
			tableAddedResults.getColumnModel().getColumn(4).setMinWidth(100);
			tableAddedResults.getColumnModel().getColumn(6).setMinWidth(200);//Result
			tableAddedResults.getColumnModel().getColumn(7).setMaxWidth(60); //Action
			tableAddedResults.getColumnModel().getColumn(7).setMinWidth(60);
			tableAddedResults.getColumnModel().getColumn(8).setMaxWidth(60); //Action
			tableAddedResults.getColumnModel().getColumn(8).setMinWidth(60);
			
			tableAddedResults.setRowSelectionAllowed(false);
			tableAddedResults.setColumnSelectionAllowed(false);
			tableAddedResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 222, 173));
			rend.setForeground(Color.BLUE);
			for(int i=0;i<9;i++)
			tableAddedResults.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			for(int i=0;i<9;i++)
				tableAddedResults.getTableHeader().getColumnModel().getColumn(i).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(new Color(70, 130, 180));
			tableAddedResults.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(new Color(147, 112, 219));
			tableAddedResults.getTableHeader().getColumnModel().getColumn(6).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(Color.BLUE);
			tableAddedResults.getTableHeader().getColumnModel().getColumn(7).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(Color.RED);
			tableAddedResults.getTableHeader().getColumnModel().getColumn(8).setCellRenderer(rend);
			
		}
		return tableAddedResults;
	}
	
	private class MyRecord{
		public Object[] field = new Object[9];
	}
	
	public class MyTableModel extends AbstractTableModel{

		private String[] heading = new String[]{
			"No.",
			"Academic Year",
			"Semester",
			"Roll No.",
			"Total Mark",
			"No. of E",
			"Result",
			"Show",
			"Action"
		};
		private ArrayList<MyRecord> data = new ArrayList<MyRecord>();;
		
		public void removeAll(){
			int size=this.data.size();
			this.data.clear();
			fireTableRowsDeleted(0,size);
		}
		public ArrayList<MyRecord> getAll(){
			return this.data;
		}
		public void removeRecord(int rowIndex){
			this.data.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
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
		if(tableModel == null){
			tableModel = new MyTableModel();
		}
		return tableModel;
	}

}
