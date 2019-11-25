package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ShowAllStudentsView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSASTitle;
	private JScrollPane scrollPane;
	private JTable tableStd;
	private MyTableModel tableModel;
	private DefaultTableCellRenderer rend;
	private JComboBox comboBox;
	private JLabel lblSortBy;
	private JButton btnExportAllTo;
	private JButton btnImportFromExcel;

	/**
	 * Create the panel.
	 */
	public ShowAllStudentsView() {
		setBackground(new Color(220, 220, 220));
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		setLayout(null);
		add(getLblSASTitle());
		add(getScrollPane());
		add(getComboBox());
		add(getLblSortBy());
		add(getBtnExportAllTo());
		add(getBtnImportFromExcel());

	}
	
	public JLabel getLblSASTitle() {
		if (lblSASTitle == null) {
			lblSASTitle = new JLabel("List Of All Registered Students");
			lblSASTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblSASTitle.setForeground(new Color(70, 130, 180));
			lblSASTitle.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
			lblSASTitle.setBounds(73, 25, 921, 31);
		}
		return lblSASTitle;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(76, 142, 918, 400);
			scrollPane.setViewportView(getTableStd());
		}
		return scrollPane;
	}
	public JTable getTableStd() {
		if (tableStd == null) {
			tableStd = new JTable(getTableModel());
			
			tableStd.getColumnModel().getColumn(0).setMaxWidth(40);
			tableStd.getColumnModel().getColumn(0).setMinWidth(40);
			tableStd.getColumnModel().getColumn(1).setMaxWidth(100);
			tableStd.getColumnModel().getColumn(1).setMinWidth(100);
			tableStd.getColumnModel().getColumn(3).setMinWidth(120);
			tableStd.getColumnModel().getColumn(4).setMaxWidth(50);
			tableStd.getColumnModel().getColumn(7).setMaxWidth(70);
			tableStd.getColumnModel().getColumn(7).setMinWidth(70);
			tableStd.getColumnModel().getColumn(8).setMaxWidth(70);
			tableStd.getColumnModel().getColumn(8).setMinWidth(70);
			tableStd.getColumnModel().getColumn(9).setMaxWidth(70);
			tableStd.getColumnModel().getColumn(9).setMinWidth(70);
			
			tableStd.setRowSelectionAllowed(false);
			tableStd.setColumnSelectionAllowed(false);
			tableStd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 222, 173));
			rend.setForeground(Color.BLUE);
			for(int i=0;i<10;i++)
			tableStd.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			for(int i=0;i<10;i++)
				tableStd.getTableHeader().getColumnModel().getColumn(i).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(Color.BLUE);
			tableStd.getTableHeader().getColumnModel().getColumn(7).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(new Color(32, 178, 170));
			tableStd.getTableHeader().getColumnModel().getColumn(8).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(Color.RED);
			tableStd.getTableHeader().getColumnModel().getColumn(9).setCellRenderer(rend);
				
		}
		return tableStd;
	}
	// ******** Own Code *********
	private class MyRecord{
		public Object[] field = new Object[10];
	}
	
	public class MyTableModel extends AbstractTableModel{
		
		private String[] heading = new String[]{
			"No.","ID","Name","NRC No","Major","Father Name","Phone No","Show","Academic","Action"
		};
		private ArrayList<MyRecord> data = new ArrayList<MyRecord>();
		
		public void removeAll(){
			int size=this.data.size();
			this.data.clear();
			fireTableRowsDeleted(0,size);
			
		}
		public String removeRecord(int rowIndex){
			String sid=(String)this.data.get(rowIndex).field[1];
			this.data.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
			return sid;
		}
		public String getStudentId(int rowIndex){
			String sid=(String)this.data.get(rowIndex).field[1];		
			return sid;
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
			if(columnIndex==8 || columnIndex==9) return true;
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
	public JComboBox getComboBox() {
		if (comboBox == null) {
			Object[] obj = new Object[]{
					"Student ID",
					"Student Name",
					"Major"
			};
			comboBox = new JComboBox(obj);
			comboBox.setForeground(Color.BLUE);
			comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			comboBox.setBounds(768, 82, 226, 30);
		}
		return comboBox;
	}
	public JLabel getLblSortBy() {
		if (lblSortBy == null) {
			lblSortBy = new JLabel("Sort By :");
			lblSortBy.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			lblSortBy.setBounds(674, 82, 68, 30);
		}
		return lblSortBy;
	}
	public JButton getBtnExportAllTo() {
		if (btnExportAllTo == null) {
			btnExportAllTo = new JButton("Export All To Excel");
			btnExportAllTo.setBounds(732, 554, 262, 42);
			btnExportAllTo.setForeground(Color.WHITE);
			btnExportAllTo.setFont(new Font("Century Gothic", Font.BOLD, 18));
			btnExportAllTo.setFocusPainted(false);
			btnExportAllTo.setBackground(new Color(0, 62, 62));
		}
		return btnExportAllTo;
	}
	public JButton getBtnImportFromExcel() {
		if (btnImportFromExcel == null) {
			btnImportFromExcel = new JButton("Import From Excel");
			btnImportFromExcel.setForeground(Color.WHITE);
			btnImportFromExcel.setFont(new Font("Century Gothic", Font.BOLD, 18));
			btnImportFromExcel.setFocusPainted(false);
			btnImportFromExcel.setBackground(new Color(0, 62, 62));
			btnImportFromExcel.setBounds(453, 554, 262, 42);
		}
		return btnImportFromExcel;
	}
}
