package astpa.export.pages;

import messages.Messages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import astpa.export.AbstractExportPage;

/**
 * Creates a Page which collects basic informations about formatting the table Export
 * 
 * @author Lukas Balzer
 *
 */
public class TableExportPage extends AbstractExportPage {
	private Composite control;
	private Button singlePage;
	private Button multiPage;
	private Text pathText;
	private final String[] filters;
	/**
	 *
	 * @author Lukas Balzer
	 * @param filters
	 * 			the file extensions, which shall be excepted by in the dialog
	 *
	 * @param pageName
	 * 			the Name of this page, that is displayed in the header of the wizard
	 * @param projectName 
	 * 			The Name of the project
	 */
	public TableExportPage(String[] filters,String pageName,String projectName) {
		super(pageName, projectName);
		this.setTitle(pageName);
		this.filters=filters;
		this.setDescription(Messages.SetValuesForTheExportFile);
	}


	@Override
	public void createControl(Composite parent) {
		this.control = new Composite(parent,SWT.NONE);
		this.control.setLayout(new FormLayout());
		
		
		Group layoutGroup= new Group(this.control, SWT.SHADOW_IN);
		layoutGroup.setLayout(new RowLayout(SWT.VERTICAL));
		layoutGroup.setText("Layout");
		this.singlePage= new Button(layoutGroup, SWT.RADIO);
		this.singlePage.setText("export on a single image");
		this.multiPage= new Button(layoutGroup, SWT.RADIO);
		this.multiPage.setText("Use multiple Din A4 Images if needed");
		
		FormData data= new FormData();
		
		data.top= new FormAttachment(COMPONENT_OFFSET);
		layoutGroup.setLayoutData(data);
		
		PathComposite pathChooser= new PathComposite(this.filters,this.control, SWT.NONE);
		data= new FormData();
		data.top= new FormAttachment(layoutGroup,COMPONENT_OFFSET);
		pathChooser.setLayoutData(data);
		this.pathText=pathChooser.getText();
		
		// Required to avoid an error in the system
				this.setControl(this.control);
				this.setPageComplete(true);

	}


	@Override
	public boolean asOne(){
		return this.singlePage.getSelection();
	}


	@Override
	public String getExportPath() {
		return this.pathText.getText();
	}
	
	@Override
	public void setExportPath(String path) {
		this.pathText.setText(path);	
	}
	

}
