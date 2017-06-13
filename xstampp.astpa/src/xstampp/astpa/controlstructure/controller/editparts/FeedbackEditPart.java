
package xstampp.astpa.controlstructure.controller.editparts;

import messages.Messages;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import xstampp.astpa.Activator;
import xstampp.astpa.controlstructure.controller.policys.CSConnectionPolicy;
import xstampp.astpa.controlstructure.figure.ComponentFigure;
import xstampp.astpa.model.interfaces.IControlStructureEditorDataModel;

/**
 * @author Lukas Balzer
 * 
 */
public class FeedbackEditPart extends CSAbstractEditPart {

	/**
	 * this constuctor sets the unique ID of this EditPart which is the same in
	 * its model and figure
	 * 
	 * @author Lukas Balzer
	 * 
	 * @param model
	 *            The DataModel which contains all model classes
	 * @param stepId
	 *            this steps id
	 */
	public FeedbackEditPart(IControlStructureEditorDataModel model,
			String stepId) {
		super(model, stepId, 1);
	}

	@Override
	protected IFigure createFigure() {
		ImageDescriptor imgDesc = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/Feedback_80.png"); //$NON-NLS-1$
		Image img = imgDesc.createImage(null);
		ComponentFigure tmpFigure = new ComponentFigure(this.getId(), img);
		tmpFigure.setPreferenceStore(getStore());
		tmpFigure.setParent(((IControlStructureEditPart) this.getParent()).getContentPane());
		tmpFigure.setToolTip(new Label("Feedback"));
		return tmpFigure;
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		this.installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new CSConnectionPolicy(this.getDataModel(), this.getStepId()));
	}

}
