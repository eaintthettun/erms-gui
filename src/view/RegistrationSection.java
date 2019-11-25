package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import controller.ButtonMouseListener;
import com.jgoodies.forms.layout.Sizes;
import javax.swing.UIManager;

public class RegistrationSection extends JPanel {
	
	private JTable tableAddedStds;
	private MyTableModel tableModel;
	private DefaultTableCellRenderer rend;
	
	private JLabel lblEntranceID;
	private JLabel lblRollNo;
	private JTextField txtEntranceID;
	private JTextField txtStudentName;
	private JTextField txtRollNo;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private JLabel lblSubTitle;

	/**
	 * Create the panel.
	 */
	public RegistrationSection() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Registration Section : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(220, 220, 220));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("20dlu"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("20dlu"),
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),},
			new RowSpec[] {
				RowSpec.decode("12dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("23dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("23dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("23dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("23dlu", false), Sizes.constant("25dlu", false)), 0),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17dlu"),}));
		
		add(getLblEntranceID(),"3, 3, right, fill");
		add(getTxtEntranceID(),"5, 3, fill, fill");
		add(getTxtStudentName(),"7, 3, fill, fill");
		add(getLblRollNo(),"3, 5, right, fill");
		add(getTxtRollNo(),"5, 5, fill, fill");
		add(getBtnAdd(),"7, 5, right, fill");
		add(getLblSubTitle(),"3, 9, 5, 1, center, bottom");
		add(getScrollPane(),"3, 11, 5, 1, fill, fill");
	}
	
	public JLabel getLblEntranceID() {
		if (lblEntranceID == null) {
			lblEntranceID = new JLabel("Enter Student Entrance ID :");
			lblEntranceID.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblEntranceID;
	}
	public JTextField getTxtEntranceID() {
		if (txtEntranceID == null) {
			txtEntranceID = new JTextField();
			txtEntranceID.setForeground(Color.BLUE);
			txtEntranceID.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			txtEntranceID.setColumns(10);
		}
		return txtEntranceID;
	}
	public JTextField getTxtStudentName() {
		if (txtStudentName == null) {
			txtStudentName = new JTextField();
			txtStudentName.setBackground(new Color(255, 255, 240));
			txtStudentName.setForeground(Color.MAGENTA);
			txtStudentName.setFont(new Font("Century Gothic", Font.PLAIN, 13));
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
		}
		return btnAdd;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTableAddedStds());
		}
		return scrollPane;
	}
	public JLabel getLblSubTitle() {
		if (lblSubTitle == null) {
			lblSubTitle = new JLabel("Added Students");
			lblSubTitle.setForeground(new Color(70, 130, 180));
			lblSubTitle.setFont(new Font("Century Gothic", Font.BOLD, 18));
		}
		return lblSubTitle;
	}
	
	public JTable getTableAddedStds() {
		if (tableAddedStds == null) {
			tableAddedStds = new JTable(getTableModel());
			
			tableAddedStds.getColumnModel().getColumn(0).setMaxWidth(60);
			tableAddedStds.getColumnModel().getColumn(0).setMinWidth(60);
			tableAddedStds.getColumnModel().getColumn(1).setMaxWidth(170);
			tableAddedStds.getColumnModel().getColumn(1).setMinWidth(170);
			tableAddedStds.getColumnModel().getColumn(2).setMaxWidth(170);
			tableAddedStds.getColumnModel().getColumn(2).setMinWidth(170);
			tableAddedStds.getColumnModel().getColumn(4).setMaxWidth(100);
			tableAddedStds.getColumnModel().getColumn(4).setMinWidth(100);
			
			tableAddedStds.setRowSelectionAllowed(false);
			tableAddedStds.setColumnSelectionAllowed(false);
			tableAddedStds.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			rend = new DefaultTableCellRenderer();
			//rend.setBackground(new Color(176, 196, 222));
			rend.setBackground(new Color(255, 222, 173));
			rend.setForeground(Color.BLUE);
			for(int i=0;i<5;i++)
			tableAddedStds.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			for(int i=0;i<5;i++)
				tableAddedStds.getTableHeader().getColumnModel().getColumn(i).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(Color.RED);
			tableAddedStds.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(rend);
				
		}
		return tableAddedStds;
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
			"No.","Entrance ID","Roll No","Name","Action"
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
	
	public JLabel getLblRollNo() {
		if (lblRollNo == null) {
			lblRollNo = new JLabel("Enter Student New Roll No :");
			lblRollNo.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblRollNo;
	}
	public JTextField getTxtRollNo() {
		if (txtRollNo == null) {
			txtRollNo = new JTextField();
			txtRollNo.setForeground(UIManager.getColor("List.dropLineColor"));
			txtRollNo.setFont(new Font("Century Gothic", Font.BOLD, 16));
			txtRollNo.setColumns(10);
		}
		return txtRollNo;
	}

}
