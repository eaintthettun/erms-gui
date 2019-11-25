package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import utils.Checker;
import utils.OfficeUsage;
import utils.Usage;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

import controller.ButtonMouseListener;
import database.ChangeOfSubDao;
import database.StudentDao;
import model.Marks;

public class DetailSection extends JPanel {
	
	private Marks mks;
	private String acy;
	
	private JLabel lblSubTitle;
	
	private JButton btnWord,btnExcel;

	/**
	 * Create the panel.
	 */
	public DetailSection() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 139, 139), 1, true), " Details : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		setBackground(new Color(220, 220, 220));
		prepareDetails();
	}
	
	public void setRecords(Marks r){
		this.mks = r;
	}
	public Marks getRecords(){
		return this.mks;
	}
	
	public void setAcy(String acy){
		this.acy = acy;
	}
	public String getAcy(){
		return this.acy;
	}
	
	public void prepareDetails(){
		removeAll();
		revalidate();
		repaint();
		if(mks == null){
			setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("70dlu"),
					ColumnSpec.decode("default:grow"),
					ColumnSpec.decode("70dlu"),},
				new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_ROWSPEC,}));
			
			JLabel l = new JLabel("Nothing To Show");
			l.setForeground(new Color(70, 130, 180));
			l.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			add(l,"2, 2, center, fill");
			
		}else{
			
			ArrayList<String> subList = Usage.separateSubCodes(this.mks.getSubjects());
			ArrayList<String> markList = Usage.separateSubCodes(this.mks.getMarks());
			
			Usage.removeReduntantSubject(subList, markList);
			
			ArrayList<RowSpec> rowArr = new ArrayList<RowSpec>();
			
			for(int i=0;i<subList.size()+6;i++){
				rowArr.add(FormFactory.RELATED_GAP_ROWSPEC);
				rowArr.add(new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("23dlu", false), Sizes.constant("25dlu", false)), 0));
			}
			rowArr.add(RowSpec.decode("10dlu"));
			
			RowSpec[] rows = new RowSpec[rowArr.size()];
			for(int i=0;i<rows.length;i++){
				rows[i] = rowArr.get(i);
			}
			
			setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("45dlu"),
					ColumnSpec.decode("max(100dlu;default):grow"),//Subject Name
					ColumnSpec.decode("60dlu"),//Subject Marks
					ColumnSpec.decode("40dlu"),//Excellent
					ColumnSpec.decode("10dlu"),
					ColumnSpec.decode("default:grow"),//Buttons
					ColumnSpec.decode("25dlu"),
					},
					rows));
			
			add(getLblSubTitle(mks.getAcademic_year()+" Academic Year"),"2, 2, 5, 1, center, fill");
			add(getLblSubTitle((mks.getSemester().equals("1") ? "First":"Second")+" Semester Examination"),"2, 4, 5, 1, center, fill");
			add(getLblSubTitle("Marks Obtained By "
			+ StudentDao.getStudentByID(mks.getStd_id()).getStd_name()
			+ " ( " +mks.getRollno()+ " )"),"2, 6, 5, 1, center, fill");
			
			add(getLblSubHeader("SUBJECTS"),"2, 8, left, fill");
			add(getLblSubHeader("MARKS"),"3, 8, center, fill");
			
			int i=5;
			for(String sub:subList){
				add(getLblSubName(ChangeOfSubDao.getSubjectName(sub, acy)),"2, "+(i*2)+", left, fill");
				add(getLblSubMark(sub, markList.get(i-5), mks.getYear()),"3, "+(i*2)+", center, fill");
				if(Checker.isExcellent(sub, Integer.parseInt(markList.get(i-5)), mks.getYear()))
					add(getLblExcellent(),"4, "+(i*2)+", left, fill");
				i++;
			}
			
			add(getLblTotalAndResult("Total  ="),"2, "+(i*2)+", right, fill");
			add(getLblTotalMark(Usage.getTotalAndMax(subList, markList)),
					"3, "+(i*2)+", center, fill");
			i++;
			add(getLblTotalAndResult("Result  :  "),"2, "+(i*2)+", right, fill");
			add(getLblResult(Usage.getResultType(subList, markList, mks.getYear()))
					,"3, "+(i*2)+", 3, 1, left, fill");
			
			add(getBtnWord(),"6, 10, 1, 2, center, fill");
			add(getBtnExcel(),"6, 14, 1, 2, center, fill");
			
		}
	}
	
	public JLabel getLblSubTitle(String title) {
		lblSubTitle = new JLabel(title);
		lblSubTitle.setForeground(new Color(70, 130, 180));
		lblSubTitle.setFont(new Font("Century Gothic", Font.BOLD, 18));
		return lblSubTitle;
	}
	
	public JLabel getLblSubHeader(String header) {
		JLabel lblSubTitle = new JLabel(header);
		lblSubTitle.setForeground(Color.DARK_GRAY);
		lblSubTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		return lblSubTitle;
	}
	
	public JLabel getLblSubName(String name) {
		JLabel lblSubTitle = new JLabel(name);
		lblSubTitle.setForeground(Color.GRAY.darker());
		lblSubTitle.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		return lblSubTitle;
	}
	public JLabel getLblSubMark(String sub, String mk, String year) {
		JLabel lblSubTitle = new JLabel(mk);
		int mark = Integer.parseInt(mk);
		if(mark<50){
			lblSubTitle.setForeground(Color.RED);
		}else{
			if(Checker.isExcellent(sub, mark, year))
				lblSubTitle.setForeground(new Color(32, 178, 170)); //new Color(30, 144, 255) new Color(32, 178, 170)
			else
				lblSubTitle.setForeground(Color.DARK_GRAY);
		}
		lblSubTitle.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		return lblSubTitle;
	}
	public JLabel getLblExcellent() {
		JLabel lblSubTitle = new JLabel("Excellent");
		lblSubTitle.setForeground(Color.DARK_GRAY);
		lblSubTitle.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		return lblSubTitle;
	}
	public JLabel getLblTotalAndResult(String totAndMax) {
		JLabel lblSubTitle = new JLabel(totAndMax);
		lblSubTitle.setForeground(Color.DARK_GRAY);
		lblSubTitle.setFont(new Font("Century Gothic", Font.BOLD, 16));
		return lblSubTitle;
	}
	
	public JLabel getLblTotalMark(String totalAndMax) {
		JLabel lblSubTitle = new JLabel(totalAndMax);
		lblSubTitle.setForeground(new Color(30, 144, 255));
		lblSubTitle.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		return lblSubTitle;
	}
	
	public JLabel getLblResult(String rs) {
		JLabel lblSubTitle = new JLabel(rs);
		lblSubTitle.setForeground(new Color(147, 112, 219));
		lblSubTitle.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		return lblSubTitle;
	}
	
	public JButton getBtnWord(){
		if(btnWord == null){
			btnWord = new JButton("Report Marks");
			btnWord.setFont(new Font("Century Gothic", Font.BOLD, 18));
			btnWord.setForeground(Color.WHITE);
			btnWord.setFocusPainted(false);
			btnWord.setBackground(new Color(0, 128, 128).darker().darker());
			btnWord.addMouseListener(new ButtonMouseListener(btnWord));
			btnWord.addActionListener(new MyButtonActLis());
			Usage.setIcon(btnWord, "word");
		}
		return btnWord;
	}
	
	public JButton getBtnExcel(){
		if(btnExcel == null){
			btnExcel = new JButton("Export Marks");
			btnExcel.setFont(new Font("Century Gothic", Font.BOLD, 18));
			btnExcel.setForeground(Color.WHITE);
			btnExcel.setFocusPainted(false);
			btnExcel.setBackground(new Color(0, 128, 128).darker().darker());
			btnExcel.addMouseListener(new ButtonMouseListener(btnExcel));
			btnExcel.addActionListener(new MyButtonActLis());
			Usage.setIcon(btnExcel, "excel");
		}
		return btnExcel;
	}
	
	private class MyButtonActLis implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnWord)){

				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Word Document", "doc","docx");
				jfc.setAcceptAllFileFilterUsed(false);
				jfc.addChoosableFileFilter(filter);
				
				int ret = jfc.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					if(OfficeUsage.reportMarkToWord(mks, f))
						JOptionPane.showMessageDialog(null, "Successfully Reported to Microsoft Word!", 
								"Report Success", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Unable to Report to Microsoft Word!", 
								"Report Failure", JOptionPane.ERROR_MESSAGE);
				}
			
			}else{

				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel Worksheet", "xls","xlsx");
				jfc.setAcceptAllFileFilterUsed(false);
				jfc.addChoosableFileFilter(filter);
				
				int ret = jfc.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					
					ArrayList<Marks> marks = new ArrayList<Marks>();
					marks.add(mks);
					if(OfficeUsage.exportMarksToExcel(marks, f))
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
