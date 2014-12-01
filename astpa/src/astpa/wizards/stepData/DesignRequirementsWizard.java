package astpa.wizards.stepData;

import messages.Messages;
import astpa.preferences.IPreferenceConstants;
import astpa.ui.sds.DesignRequirementView;
import astpa.wizards.AbstractExportWizard;
import astpa.wizards.pages.CSVExportPage;

/**
 *
 * @author Lukas Balzer
 *
 */
public class DesignRequirementsWizard extends AbstractExportWizard{
	
	/**
	 *
	 * @author Lukas Balzer
	 *
	 */
	public DesignRequirementsWizard() {
		super(DesignRequirementView.ID);
		String[] filters= new String[] {"*.csv"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		setExportPage(new CSVExportPage(filters,Messages.DesignRequirements,
										this.getStore().getString(IPreferenceConstants.PROJECT_NAME)));
	}

	@Override
	public boolean performFinish() {
		return performCSVExport();
	}
}
