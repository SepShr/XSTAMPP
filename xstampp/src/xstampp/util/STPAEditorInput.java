package xstampp.util;

import java.util.UUID;

import messages.Messages;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PlatformUI;

import xstampp.ui.common.ViewContainer;

/**
 * The Standard Editor input for this Platform,
 * 
 * @author Lukas Balzer
 * @since version 2.0.0
 * @see IEditorInput
 */
public class STPAEditorInput implements IEditorInput {

	private UUID projectId;
	private String stepEditorId;
	private UUID id;
	private String stepName;
	private String pathHistory;
	/**
	 * The
	 * 
	 * @author Lukas Balzer
	 * 
	 * @param projectId
	 *            the id of the project which is related to this input
	 */
	public STPAEditorInput(UUID projectId, String editorId) {
		this.projectId = projectId;
		this.stepEditorId = editorId;
		this.id= UUID.randomUUID();
		this.stepName="";

	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public IPersistableElement getPersistable() {
		// no persistence supported at the moment
		return null;
	}

	@Override
	public String getToolTipText() {
		return stepName + " - " + ViewContainer.getContainerInstance().getTitle(this.projectId);
	}
	
	/**
	 * 
	 * @author Lukas Balzer
	 * 
	 * @return the id of the project which is related to this input
	 */
	public UUID getProjectID() {
		return this.projectId;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		return (int) this.id.getMostSignificantBits();
	}

	@Override
	public boolean equals(Object arg0) {
		boolean equality= false;
		
		if(arg0 instanceof STPAEditorInput){
			equality= this.projectId.equals(((STPAEditorInput) arg0).projectId);
			equality= equality && this.stepEditorId.equals(((STPAEditorInput) arg0).stepEditorId);
			if(equality){
			}
			
			return this.id.equals(((STPAEditorInput) arg0).id);
		}
		return super.equals(arg0);
	}

	/**
	 * @return the stepName
	 */
	public String getStepName() {
		return this.stepName;
	}

	/**
	 * @param stepName the stepName to set
	 */
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	
	/**
	 * @param pathHistory the pathHistory to set
	 */
	public void setPathHistory(String pathHistory) {
		this.pathHistory = pathHistory;
	}
	
	public void activate() {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().
									setText(Messages.PlatformName + " -" +this.pathHistory);
	}
}
