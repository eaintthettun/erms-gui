package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.Sizes;

import database.ChangeOfSubDao;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;

public class AllSubjectView extends JPanel {
	private JLabel lblTitle;
	private JLabel lblNote;
	private JLabel lblNoSub;
	
	private ArrayList<String[]> subjects;

	/**
	 * Create the panel.
	 */
	public AllSubjectView() {
		setBackground(new Color(220, 220, 220));
		//setBackground(SystemColor.activeCaptionBorder);
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		prepareView();
	}
	
	public void prepareView(){
		
		removeAll();
		revalidate();
		repaint();
		
		if(this.subjects == null || this.subjects.isEmpty()){
			setLayout(new FormLayout(new ColumnSpec[] { 
					ColumnSpec.decode("20dlu"),
					ColumnSpec.decode("default:grow"),	//For sub_code
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),	//For sub_name
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),	//For Remark
					ColumnSpec.decode("20dlu"), }, 
					new RowSpec[] {
					RowSpec.decode("10dlu"),
					new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT,
							Sizes.constant("23dlu", false),
							Sizes.constant("25dlu", false)), 0),
					
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),
					RowSpec.decode("15dlu"),
					}));
			add(getLblTitle(), "2, 2, 5, 1, center, fill");
			add(getLblNoSub(), "2, 4, 5, 1, center, fill");
		}else{
			
			ArrayList<RowSpec> rowList = new ArrayList<RowSpec>();
			rowList.add(RowSpec.decode("10dlu"));
			rowList.add(new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT,
					Sizes.constant("23dlu", false),
					Sizes.constant("25dlu", false)), 0));
			rowList.add(RowSpec.decode("5dlu"));
			rowList.add(new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT,
					Sizes.constant("23dlu", false),
					Sizes.constant("25dlu", false)), 0));
			for(int i=0;i<this.subjects.size();i++){
				rowList.add(FormFactory.RELATED_GAP_ROWSPEC);
				rowList.add(new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT,
					Sizes.constant("23dlu", false),
					Sizes.constant("25dlu", false)), 0));
			}
			rowList.add(FormFactory.RELATED_GAP_ROWSPEC);
			rowList.add(new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT,
					Sizes.constant("23dlu", false),
					Sizes.constant("25dlu", false)), 0));
			rowList.add(RowSpec.decode("15dlu"));
			
			RowSpec[] row = new RowSpec[rowList.size()];
			for(int i=0;i<row.length;i++){
				row[i] = rowList.get(i);
			}
			
			setLayout(new FormLayout(new ColumnSpec[] { 
					ColumnSpec.decode("20dlu"),
					ColumnSpec.decode("65dlu"),	//For sub_code
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),	//For sub_name
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("20dlu"),	//For Remark
					ColumnSpec.decode("20dlu"), }, 
					row
					));
			add(getLblTitle(), "2, 2, 5, 1, center, fill");
			
			add(getTextField(" Subject Code"),"2, 4, fill, fill");
			add(getTextField("\tSubject Name"),"4, 4, fill, fill");
			add(getTextField("Rmk"),"6, 4, fill, fill");
			
			int i=0;
			for(String[] str:this.subjects){
				add(getTextField("  "+str[0]),"2, "+((i+3)*2)+", fill, fill");
				add(getTextField("   "+str[1]),"4, "+((i+3)*2)+", fill, fill");
				if(ChangeOfSubDao.exists(str[0]))
					add(getTextField("   *"),"6, "+((i+3)*2)+", fill, fill");
				else
					add(getTextField(" "),"6, "+((i+3)*2)+", fill, fill");
				i++;
			}
			add(getLblNote(), "2, "+((i+3)*2)+", 5, 1, left, fill");
		}
	}

	public JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("List of All Subjects");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setForeground(new Color(70, 130, 180));
			lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 19));
		}
		return lblTitle;
	}
	public JLabel getLblNote() {
		if (lblNote == null) {
			lblNote = new JLabel("Note : ( * ) represents that subject has multiple names and only default name is shown here.");
			lblNote.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			lblNote.setForeground(Color.GRAY);
		}
		return lblNote;
	}
	public JLabel getLblNoSub() {
		if (lblNoSub == null) {
			lblNoSub = new JLabel("No Subject To Display");
			lblNoSub.setForeground(Color.RED);
			lblNoSub.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		}
		return lblNoSub;
	}
	
	public JTextField getTextField(String name){
		JTextField tf = new JTextField();
		tf.setText(name);
		tf.setEditable(false);
		tf.setFocusable(false);
		tf.setBackground(new Color(220, 220, 220));
		tf.setForeground(SystemColor.textHighlight);	//new Color(32, 178, 170)
		tf.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		tf.setUI(new HintTextFieldUI(""));
		tf.setBorder(new LineBorder(new Color(32, 178, 170), 1, false));
		return tf;
	}

	public ArrayList<String[]> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<String[]> subjects) {
		this.subjects = subjects;
	}
}
