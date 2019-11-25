package view;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import model.Course;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MultipleResultTab extends JPanel {
	
	private ExamSectionForResult examSection;
	private MarkSectionForResult addMarkSection;

	/**
	 * Create the panel.
	 */
	public MultipleResultTab() {
		setBackground(new Color(220, 220, 220));
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20dlu"),
				ColumnSpec.decode("min(530dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("18dlu"),},
			new RowSpec[] {
				RowSpec.decode("10dlu"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("10dlu"),}));
		add(getExamSection(), "2, 2, fill, fill");
		add(getAddMarkSection(), "2, 4, fill, fill");
	}
	
	public ExamSectionForResult getExamSection(){
		if(examSection == null){
			examSection = new ExamSectionForResult();
		}
		return examSection;
	}
	
	public MarkSectionForResult getAddMarkSection(){
		if(addMarkSection == null){
			addMarkSection = new MarkSectionForResult(null,null,new Course());
		}
		return addMarkSection;
	}

}
