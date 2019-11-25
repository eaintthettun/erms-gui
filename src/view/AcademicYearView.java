package view;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AcademicYearView extends JPanel {
	
	private AcademicSection academicSection;
	private RegistrationSection registrationSection;

	/**
	 * Create the panel.
	 */
	public AcademicYearView() {
		setBackground(new Color(220, 220, 220));
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("27dlu"),
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("25dlu"),},
			new RowSpec[] {
				RowSpec.decode("10dlu"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("10dlu:grow"),}));
		add(getAcademicSection(), "2, 2, fill, fill");
		add(getRegistrationSection(), "2, 4, fill, fill");
	}
	
	public AcademicSection getAcademicSection(){
		if(academicSection == null){
			academicSection = new AcademicSection();
		}
		return academicSection;
	}
	
	public RegistrationSection getRegistrationSection(){
		if(registrationSection == null){
			registrationSection = new RegistrationSection();
		}
		return registrationSection;
	}

}
