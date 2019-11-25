package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.Sizes;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ChangeOfSubjectView extends JPanel {
	private JLabel labelTitle;
	private JLabel lblSubjcetCode;
	private JLabel lblStartYear;
	private JLabel lblEndYear;
	private JTextField txtSubjectCode;
	private JTextField txtSubjectName;
	private JTextField txtStartYear;
	private JTextField txtEndYear;
	private JComboBox comboBox;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable tableStd;
	private DefaultTableCellRenderer rend;
	private MyTableModel tableModel;
	private JButton btnEnter;
	private JLabel lblNewSubjectName;
	private JTextField txtNewSubjectName;

	/**
	 * Create the panel.
	 */
	public ChangeOfSubjectView() {
		setBackground(SystemColor.activeCaptionBorder);
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(111dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(80dlu;default)"),
				ColumnSpec.decode("10dlu"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("23dlu", false), Sizes.constant("25dlu", false)), 0),
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
				RowSpec.decode("10dlu"),}));
		add(getLabelTitle(), "2, 2, 7, 1, center, fill");
		add(getLblSubjcetCode(), "2, 4, right, default");
		add(getTxtSubjectCode(), "4, 4, fill, fill");
		add(getTxtSubjectName(), "6, 4, fill, fill");
		add(getLblStartYear(), "2, 6, right, default");
		add(getTxtStartYear(), "6, 6, left, fill");
		add(getBtnEnter(), "8, 6, 1, 5, center, fill");
		add(getLblEndYear(), "2, 8, right, default");
		add(getComboBox(), "4, 8, fill, fill");
		add(getTxtEndYear(), "6, 8, left, fill");
		add(getLblNewSubjectName(), "2, 10, right, default");
		add(getTxtNewSubjectName(), "4, 10, 3, 1, fill, fill");
		add(getPanel(), "2, 12, 7, 1, fill, fill");
	}

	public JLabel getLabelTitle() {
		if (labelTitle == null) {
			labelTitle = new JLabel("Changes Of Subject Name Over The Year");
			labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
			labelTitle.setForeground(new Color(70, 130, 180));
			labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 19));
		}
		return labelTitle;
	}
	public JLabel getLblSubjcetCode() {
		if (lblSubjcetCode == null) {
			lblSubjcetCode = new JLabel("Subject Code :");
			lblSubjcetCode.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblSubjcetCode;
	}
	public JLabel getLblStartYear() {
		if (lblStartYear == null) {
			lblStartYear = new JLabel("Start Year :");
			lblStartYear.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblStartYear;
	}
	public JLabel getLblEndYear() {
		if (lblEndYear == null) {
			lblEndYear = new JLabel("End Year :");
			lblEndYear.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblEndYear;
	}
	public JTextField getTxtSubjectCode() {
		if (txtSubjectCode == null) {
			txtSubjectCode = new JTextField();
			txtSubjectCode.setForeground(Color.BLUE);
			txtSubjectCode.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtSubjectCode.setColumns(10);
		}
		return txtSubjectCode;
	}
	public JTextField getTxtSubjectName() {
		if (txtSubjectName == null) {
			txtSubjectName = new JTextField();
			txtSubjectName.setForeground(Color.MAGENTA);
			txtSubjectName.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			txtSubjectName.setEditable(false);
			txtSubjectName.setColumns(10);
			txtSubjectName.setBackground(new Color(255, 255, 240));
			txtSubjectName.setFocusable(false);
		}
		return txtSubjectName;
	}
	public JTextField getTxtStartYear() {
		if (txtStartYear == null) {
			txtStartYear = new JTextField();
			txtStartYear.setForeground(Color.BLUE);
			txtStartYear.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtStartYear.setColumns(10);
		}
		return txtStartYear;
	}
	public JTextField getTxtEndYear() {
		if (txtEndYear == null) {
			txtEndYear = new JTextField();
			txtEndYear.setForeground(Color.BLUE);
			txtEndYear.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtEndYear.setColumns(10);
		}
		return txtEndYear;
	}
	public JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox(new Object[]{"Present","(type)"});
			comboBox.setForeground(Color.BLUE);
			comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		}
		return comboBox;
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(1, 1));
			panel.setBorder(new TitledBorder(new LineBorder(new Color(51, 153, 255), 1, true), " Changes Of Subject Name : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 153, 255)));
			panel.setBackground(SystemColor.activeCaptionBorder);
			panel.add(getScrollPane());
		}
		return panel;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			//scrollPane.setBounds(20, 29, 578, 513);
			scrollPane.setViewportView(getTableStd());
		}
		return scrollPane;
	}
	
	public JTable getTableStd() {
		if (tableStd == null) {
			tableStd = new JTable(getTableModel());
			
			tableStd.getColumnModel().getColumn(0).setMaxWidth(30);
			tableStd.getColumnModel().getColumn(0).setMinWidth(30);
			tableStd.getColumnModel().getColumn(1).setMaxWidth(100);
			tableStd.getColumnModel().getColumn(1).setMinWidth(100);
			tableStd.getColumnModel().getColumn(2).setMaxWidth(50);
			tableStd.getColumnModel().getColumn(2).setMinWidth(50);
			tableStd.getColumnModel().getColumn(3).setMaxWidth(80);
			tableStd.getColumnModel().getColumn(3).setMinWidth(80);
			tableStd.getColumnModel().getColumn(5).setMaxWidth(60);
			tableStd.getColumnModel().getColumn(5).setMinWidth(60);
			
			tableStd.setRowSelectionAllowed(false);
			tableStd.setColumnSelectionAllowed(false);
			tableStd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			rend = new DefaultTableCellRenderer();
			//rend.setBackground(new Color(176, 196, 222));
			rend.setBackground(new Color(255, 222, 173));
			rend.setForeground(Color.BLUE);
			for(int i=0;i<6;i++)
			tableStd.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			for(int i=0;i<6;i++)
				tableStd.getTableHeader().getColumnModel().getColumn(i).setCellRenderer(rend);
			
			rend = new DefaultTableCellRenderer();
			rend.setBackground(new Color(255, 248, 220));
			rend.setForeground(Color.RED);
			tableStd.getTableHeader().getColumnModel().getColumn(5).setCellRenderer(rend);
				
		}
		return tableStd;
	}
	// ******** Own Code *********
	private class MyRecord{
		public Object[] field = new Object[6];
	}
	
	public class MyTableModel extends AbstractTableModel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String[] heading = {
			"No.","Subject Code","Start","End","Subject Name","Action"
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
			if(columnIndex==4) return true;
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
	public JButton getBtnEnter() {
		if (btnEnter == null) {
			btnEnter = new JButton(" Enter ");
			btnEnter.setEnabled(false);
			btnEnter.setForeground(SystemColor.textHighlight);
			btnEnter.setFont(new Font("Century Gothic", Font.BOLD, 15));
		}
		return btnEnter;
	}
	public JLabel getLblNewSubjectName() {
		if (lblNewSubjectName == null) {
			lblNewSubjectName = new JLabel("New Subject Name :");
			lblNewSubjectName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblNewSubjectName;
	}
	public JTextField getTxtNewSubjectName() {
		if (txtNewSubjectName == null) {
			txtNewSubjectName = new JTextField();
			txtNewSubjectName.setForeground(Color.BLUE);
			txtNewSubjectName.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txtNewSubjectName.setColumns(10);
		}
		return txtNewSubjectName;
	}
}
