package view;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AddNewCourseView extends JPanel {
	
	private SubjectSection subjectSection;
	private CourseSection courseSection;

	/**
	 * Create the panel.
	 */
	public AddNewCourseView() {
		setBackground(new Color(220, 220, 220));
		setBorder(new MatteBorder(6, 6, 6, 6, (Color) SystemColor.scrollbar));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("27dlu"),
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("25dlu"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		add(getSubjectSection(), "2, 2, fill, fill");
		add(getCourseSection(), "2, 4, fill, fill");
	}
	
	public SubjectSection getSubjectSection(){
		if(subjectSection == null){
			subjectSection = new SubjectSection();
		}
		return subjectSection;
	}
	
	public CourseSection getCourseSection(){
		if(courseSection == null){
			courseSection = new CourseSection();
		}
		return courseSection;
	}

}
