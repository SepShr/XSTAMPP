package xstampp.astpa.util.jobs;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeListener;

import xstampp.astpa.controlstructure.CSEditor;
import xstampp.astpa.controlstructure.CSEditorWithPM;
import xstampp.astpa.model.DataModelController;
import xstampp.ui.common.ProjectManager;
import xstampp.util.JAXBExportJob;

/**
 * Eclipse job that handles the export
 * 
 * @author Fabian Toth,Lukas Balzer
 * @since 2.0
 * 
 */
public class ExportJob extends JAXBExportJob implements IJobChangeListener {

	private final boolean decorate;
	private String imgPath;
	private boolean isCsDirty;
	private File csPath;
	private File csPmPath;
	private UUID projectId;
	private Object exportAddition;
	/**
	 * the xslfoTransormer is beeing related to the xsl which describes the
	 * pdf export
	 */

	/**
	 * Constructor of the export job
	 * 
	 * @author Fabian Toth, Lukas Balzer
	 * @param projectId
	 *            the project which
	 * @param name
	 *            the name of the job
	 * @param filePath
	 *            the path to the pdf file
	 * @param xslName
	 *            the name of the file in which the xsl file is stored which
	 *            should be used
	 * @param asOne
	 *            true if all content shall be exported on a single page
	 * @param decorate
	 *            if the control structure components should be pictured with
	 *            colored borders and image labels
	 */
	public ExportJob(UUID projectId, String name, String filePath, String xslName,
			boolean asOne, boolean decorate) {
		super(name, filePath, xslName);
		this.projectId = projectId;
		this.decorate = decorate;
		this.isCsDirty = false;
	}

	public void setCSImagePath(String path){
		File pathFile= new File(path);
		if(pathFile.exists()){
			this.imgPath = path;
		}
	}
	public void setCSDirty(){
		this.isCsDirty= true;
	}
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);

		while(((DataModelController)getModel()).getExportInfo() == null){
			if(monitor.isCanceled()){
				return Status.CANCEL_STATUS;
			}
		}
		this.imgPath = new File(this.getFilePath()).getParent(); 
		if(this.isCsDirty ){
			this.csPath = new File(this.imgPath + File.separator
					+ getThread().getId() + "cs.png");
			this.csPmPath = new File(this.imgPath + File.separator
					+ getThread().getId() + "cspm.png");
			CSExportJob csExport = new CSExportJob(this.csPath.getPath(), CSEditor.ID, projectId,
					10, this.decorate);
			CSExportJob csPmExport = new CSExportJob(this.csPmPath.getPath(), CSEditorWithPM.ID,
					projectId, 10, this.decorate);
	
			csExport.getPrintableRoot();
			csPmExport.getPrintableRoot();

			((DataModelController) getModel()).setCSImagePath(this.csPath.getPath());
			((DataModelController) getModel()).setCSPMImagePath(this.csPmPath.getPath());
		}

		return super.run(monitor);
	}

	@Override
	protected Object getModel() {
		return ProjectManager.getContainerInstance().getDataModel(projectId);
	}

	@Override
	protected JAXBContext getModelContent() throws JAXBException {
		return JAXBContext.newInstance(DataModelController.class);
	}

	@Override
	protected Observable getModelObserver() {
		return (DataModelController)getModel();
	}
	
	public UUID getProjectId() {
		return this.projectId;
	}

	@Override
	protected Transformer getxslTransformer(String resource) {
		URL xslUrl = this.getClass().getResource(resource);

		if (xslUrl == null) {
			return null;
		}
		try {
			StreamSource transformXSLSource = new StreamSource(xslUrl.openStream());
			TransformerFactory transfact = TransformerFactory.newInstance();
			transfact.setURIResolver(new URIResolver() {
				@Override
				public Source resolve(String href, String base) {
					return new StreamSource(this.getClass().getClassLoader()
							.getResourceAsStream("/" + href)); //$NON-NLS-1$
				}
			});
			return transfact.newTransformer(transformXSLSource);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param exportAddition the exportAddition to set
	 */
	public void setExportAddition(Object exportAddition) {
		this.exportAddition = exportAddition;
	}
	
}
