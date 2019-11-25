package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

public class RightPane extends JPanel {
	
	private PhotoSectionView photoSectionView;
	private MatSectionView matSectionView;
	private ParentsView parentsView;

	/**
	 * Create the panel.
	 */
	public RightPane() {
		setBorder(new MatteBorder(4, 8, 4, 8, (Color) new Color(255, 250, 250)));
		setBackground(new Color(255, 250, 250));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(133dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		add(getPhotoSectionView(), "2, 2, fill, fill");
		add(getMatSectionView(), "2, 4, fill, default");
		add(getParentsView(), "2, 6, fill, default");
		
	}

	public PhotoSectionView getPhotoSectionView() {
		if(photoSectionView == null){
			photoSectionView = new PhotoSectionView();
		}
		return photoSectionView;
	}

	public MatSectionView getMatSectionView() {
		if(matSectionView == null){
			matSectionView = new MatSectionView();
		}
		return matSectionView;
	}

	public ParentsView getParentsView() {
		if(parentsView == null){
			parentsView = new ParentsView();
		}
		return parentsView;
	}
	
}
