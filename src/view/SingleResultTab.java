package view;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SingleResultTab extends JPanel {
	
	private StudentSection studentSection;
	private AcademicRecordSection acrSection;
	private DetailSection detailSection;

	/**
	 * Create the panel.
	 */
	public SingleResultTab() {
		setBackground(new Color(220, 220, 220));
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20dlu"),
				ColumnSpec.decode("min(530dlu;default):grow"),
				ColumnSpec.decode("20dlu"),},
			new RowSpec[] {
				RowSpec.decode("10dlu"),
				RowSpec.decode("min(65dlu;default):none"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("min(150dlu;default):none"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("10dlu"),}));
		
		add(getStudentSection(),"2, 2, fill, fill");
		add(getAcrSection(),"2, 4, fill, default");
		add(getDetailSection(),"2, 6, fill, fill");
	}
	
	public StudentSection getStudentSection(){
		if(studentSection == null){
			studentSection = new StudentSection();
		}
		return studentSection;
	}
	public AcademicRecordSection getAcrSection(){
		if(acrSection == null){
			acrSection = new AcademicRecordSection();
		}
		return acrSection;
	}
	public DetailSection getDetailSection(){
		if(detailSection == null){
			detailSection = new DetailSection();
		}
		return detailSection;
	}

}
