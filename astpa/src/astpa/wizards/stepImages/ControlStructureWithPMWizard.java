package astpa.wizards.stepImages;

import messages.Messages;
import astpa.controlstructure.CSEditorWithPM;
import astpa.preferences.IPreferenceConstants;
import astpa.wizards.pages.ControlStructureExportPage;

/**
 *
 * @author Lukas Balzer
 *
 */
public class ControlStructureWithPMWizard extends ControlStructureWizard{
	
	/**
	 *
	 * @author Lukas Balzer
	 *
	 */
	public ControlStructureWithPMWizard() {
		super(CSEditorWithPM.ID);
		String[] filters= new String[] {"*.png", "*.jpg", "*.bmp"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		setExportPage(new ControlStructureExportPage(filters,Messages.ControlStructureDiagramWithProcessModel,
										this.getStore().getString(IPreferenceConstants.PROJECT_NAME)));
	}


}