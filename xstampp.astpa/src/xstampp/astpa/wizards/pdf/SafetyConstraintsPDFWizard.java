package xstampp.astpa.wizards.pdf;

import org.eclipse.ui.PlatformUI;

import messages.Messages;
import xstampp.astpa.ui.causalfactors.CausalFactorsView;
import xstampp.astpa.ui.sds.CSCView;
import xstampp.astpa.ui.sds.SafetyConstraintView;
import xstampp.astpa.wizards.AbstractExportWizard;
import xstampp.astpa.wizards.pages.TableExportPage;

public class SafetyConstraintsPDFWizard extends AbstractExportWizard {

	public SafetyConstraintsPDFWizard() {
		super(SafetyConstraintView.ID);
		String[] filters = new String[] { "*.pdf" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		this.setExportPage(new TableExportPage(filters,
				Messages.SafetyConstraints + Messages.AsPDF));
	}

	@Override
	public boolean performFinish() {
		return this.performXSLExport(				
				"/fopSafetyConstraints.xsl", Messages.ExportingPdf, false); ////$NON-NLS-1$
	}
}