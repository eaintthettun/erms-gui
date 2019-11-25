package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import database.MajorDao;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;

import com.jgoodies.forms.layout.Sizes;

import controller.ButtonMouseListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

public class CourseSection extends JPanel {
	
	private JTable tableAddedSubs;
	private MyTableModel tableModel;
	private DefaultTableCellRenderer rend;
	
	private JTextField txtAcadYearEnd;
	private JTextField txtAcadYearStart;
	private JLabel label;
	private JLabel lblAcademicYear;
	private JLabel lblYear;
	private JComboBox comboYear;
	private JLabel lblSemester;
	private JLabel lblSelectMajor;
	private JComboBox comboMajor;
	private JTextField txtMajorName;
	private JButton btnCreateCourse;
	private JLabel lblSubjectCode;
	private JTextField txtSubjectCode;
	private JTextField txtSubjectName;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private JLabel lblSubTitle;
	private JSpinner spinner;

	/**
	 * Create the panel.
	 */
	public CourseSection() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Add New Course : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(220, 220, 220));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("42dlu"),
				ColumnSpec.decode("min(120dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("80dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("10dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("80dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("min(80dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("40dlu"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("15dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(90dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("20dlu", false), Sizes.constant("25dlu", false)), 0),}));
		add(getLblAcademicYear(), "2, 2, right, fill");
		add(getTxtAcadYearStart(), "4, 2, fill, fill");
		add(getLabel(), "6, 2, right, default");
		add(getTxtAcadYearEnd(), "8, 2, fill, fill");
		add(getLblSelectMajor(), "2, 4, right, fill");
		add(getComboMajor(), "4, 4, fill, fill");
		add(getTxtMajorName(), "8, 4, 3, 1, fill, fill");
		add(getLblYear(), "2, 6, right, fill");
		add(getComboYear(), "4, 6, fill, fill");
		add(getLblSemester(), "8, 6, right, fill");
		add(getSpinner(), "10, 6, left, fill");
		add(getLblSubjectCode(), "2, 8, right, fill");
		add(getTxtSubjectCode(), "4, 8, fill, fill");
		add(getTxtSubjectName(), "8, 8, 3, 1, fill, fill");
		add(getBtnAdd(), "8, 10, left, fill");
		add(getLblSubTitle(), "4, 12, 5, 1, center, bottom");
		add(getScrollPane(), "2, 14, 9, 1, fill, fill");
		add(getBtnCreateCourse(), "8, 16, 3, 1, left, fill");
	}

	public JTextField getTxtAcadYearEnd() {
		if (txtAcadYearEnd == null) {
			txtAcadYearEnd = new JTextField();
			txtAcadYearEnd.setForeground(Color.BLUE);
			txtAcadYearEnd.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			txtAcadYearEnd.setColumns(10);
		}
		return txtAcadYearEnd;
	}
	public JTextField getTxtAcadYearStart() {
		if (txtAcadYearStart == null) {
			txtAcadYearStart = new JTextField();
			txtAcadYearStart.setForeground(Color.BLUE);
			txtAcadYearStart.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			txtAcadYearStart.setColumns(10);
		}
		return txtAcadYearStart;
	}
	public JLabel getLabel() {
		if (label == null) {
			label = new JLabel(" - ");
			label.setFont(new Font("Century Gothic", Font.BOLD, 18));
			label.setForeground(Color.BLUE);
		}
		return label;
	}
	public JLabel getLblAcademicYear() {
		if (lblAcademicYear == null) {
			lblAcademicYear = new JLabel("Academic Year :  ");
			lblAcademicYear.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblAcademicYear;
	}
	public JLabel getLblYear() {
		if (lblYear == null) {
			lblYear = new JLabel("Year :  ");
			lblYear.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblYear;
	}
	public JComboBox getComboYear() {
		if (comboYear == null) {
			comboYear = new JComboBox();
			comboYear.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return comboYear;
	}
	public JLabel getLblSemester() {
		if (lblSemester == null) {
			lblSemester = new JLabel("Semester :  ");
			lblSemester.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblSemester;
	}
	public JLabel getLblSelectMajor() {
		if (lblSelectMajor == null) {
			lblSelectMajor = new JLabel("Select Major :  ");
			lblSelectMajor.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblSelectMajor;
	}
	public JComboBox getComboMajor() {
		if (comboMajor == null) {
			comboMajor = new JComboBox(MajorDao.getMajorCode().toArray());
			comboMajor.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return comboMajor;
	}
	public JTextField getTxtMajorName() {
		if (txtMajorName == null) {
			txtMajorName = new JTextField();
			txtMajorName.setBackground(new Color(255, 255, 240));
			txtMajorName.setEditable(false);
			txtMajorName.setForeground(Color.MAGENTA);
			txtMajorName.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			txtMajorName.setColumns(10);
		}
		return txtMajorName;
	}
	public JButton getBtnCreateCourse() {
		if (btnCreateCourse == null) {
			btnCreateCourse = new JButton("Create Course");
			btnCreateCourse.setForeground(Color.WHITE);
			btnCreateCourse.setFont(new Font("Century Gothic", Font.BOLD, 18));
			btnCreateCourse.setFocusPainted(false);
			btnCreateCourse.setBackground(new Color(0, 62, 62));
			btnCreateCourse.addMouseListener(new ButtonMouseListener(btnCreateCourse));
		}
		return btnCreateCourse;
	}
	public JLabel getLblSubjectCode() {
		if (lblSubjectCode == null) {
			lblSubjectCode = new JLabel("Enter Subject Code :  ");
			lblSubjectCode.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblSubjectCode;
	}
	public JTextField getTxtSubjectCode() {
		if (txtSubjectCode == null) {
			txtSubjectCode = new JTextField();
			txtSubjectCode.setForeground(Color.BLUE);
			txtSubjectCode.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			txtSubjectCode.setColumns(10);
		}
		return txtSubjectCode;
	}
	public JTextField getTxtSubjectName() {
		if (txtSubjectName == null) {
			txtSubjectName = new JTextField();
			txtSubjectName.setBackground(new Color(255, 255, 240));
			txtSubjectName.setForeground(Color.MAGENTA);
			txtSubjectName.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			txtSubjectName.setEditable(false);
			txtSubjectName.setColumns(10);
		}
		return txtSubjectName;
	}
	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("+ Add");
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setFont(new Font("Century Gothic", Font.BOLD, 18));
			btnAdd.setFocusPainted(false);
			btnAdd.setBackground(new Color(0, 62, 62));
			btnAdd.addMouseListener(new ButtonMouseListener(btnAdd));
		}
		return btnAdd;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTableAddedSubs());
		}
		return scrollPane;
	}
	public JLabel getLblSubTitle() {
		if (lblSubTitle == null) {
			lblSubTitle = new JLabel("Added Subjects");
			lblSubTitle.setForeground(new Color(70, 130, 180));
			lblSubTitle.setFont(new Font("Century Gothic", Font.BOLD, 18));
		}
		return lblSubTitle;
	}
	
	public JTable getTableAddedSubs() {
		if (tableAddedSubs == null) {
			tableAddedSubs = new JTable(getTableModel());
			
			tableAddedSubs.getColumnModel().getColumn(0).setMaxWidth(50);
			tableAddedSubs.getColumnModel().getColumn(0).setMinWidth(50);
			tableAddedSubs.getColumnModel().getColumn(1).setMaxWidth(180);
			tableAddedSubs.getColumnModel().getColumn(1).setMinWidth(180);
			tableAddedSubs.getColumnModel().getColumn(3).setMaxWidth(80);
			tableAddedSubs.getColumnModel().getColumn(3).setMinWidth(80);
			tableAddedSubs.getColumnModel().getColumn(4).setMaxWidth(60);
			tableAddedSubs.getColumnModel().getColumn(4).setMinWidth(60);
			
			tableAddedSubs.setRowSelectionAllowed(false);
			tableAddedSubs.setColumnSelectionAllowed(false);
			tableAddedSubs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			rend = new DefaultTableCellRenderer();
			//rend.setBackground(new Color(176, 196, 222));
			rend.setBackground(new Color(255, 222, 173));
			rend.setForeground(Color.BLUE);
			for(int i=0;i<5;i++)
			tableAddedSubs.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			for(int i=0;i<5;i++)
				tableAddedSubs.getTableHeader().getColumnModel().getColumn(i).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(Color.RED);
			tableAddedSubs.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(Color.BLUE);
			tableAddedSubs.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(rend);
				
		}
		return tableAddedSubs;
	}
	// ******** Own Code *********
	private class MyRecord{
		public Object[] field = new Object[5];
	}
	
	public class MyTableModel extends AbstractTableModel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String[] heading = new String[]{
			"No.","Subject Code","Subject Name","Action","Move"
		};
		private ArrayList<MyRecord> data = new ArrayList<MyRecord>();
		
		public void removeAll(){
			int size=this.data.size();
			this.data.clear();
			fireTableRowsDeleted(0,size);
		}
		public ArrayList<MyRecord> getAll(){
			return this.data;
		}
		public String removeRecord(int rowIndex){
			String sid=(String)this.data.get(rowIndex).field[1];
			this.data.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
			return sid;
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
		if (tableModel == null) {
			tableModel = new MyTableModel();
		}
		return tableModel;
	}
	public JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setFont(new Font("Century Gothic", Font.BOLD, 18));
			spinner.setModel(new SpinnerNumberModel(1, 1, 2, 1));
		}
		return spinner;
	}
}
