/*******************************************************************************
 * Copyright (c) 2013 A-STPA Stupro Team Uni Stuttgart (Lukas Balzer, Adam
 * Grahovac, Jarkko Heidenwag, Benedikt Markt, Jaqueline Patzek, Sebastian
 * Sieber, Fabian Toth, Patrick Wickenhäuser, Aliaksei Babkovich, Aleksander
 * Zotov).
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *******************************************************************************/

package xstampp.astpa.controlstructure;

import messages.Messages;

import org.eclipse.draw2d.Viewport;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;

import xstampp.astpa.Activator;
import xstampp.astpa.controlstructure.controller.factorys.CSModelCreationFactory;
import xstampp.astpa.controlstructure.controller.factorys.ConnectionCreationFactory;
import xstampp.astpa.model.controlstructure.components.ComponentType;
import xstampp.astpa.model.controlstructure.components.ConnectionType;
import xstampp.astpa.wizards.stepImages.ControlStructureWizard;

/**
 * The Graphical Editor is responsible for creating the manipulatable content of
 * the Editor view. The extension of GraphicalEditorWithFlyoutPalette also adds
 * 
 * 
 * @version 1.0
 * @author Lukas Balzer, Aliaksei Babkovich
 * 
 */
public class CSEditor extends CSAbstractEditor {

	/**
	 * The ID is used to reference the Editor
	 */
	public static final String ID = "astpa.steps.step1_8"; //$NON-NLS-1$
	private double zoomLevel;
	private Viewport viewLocation;

	/**
	 * this sets the zoom initially to 100%
	 * 
	 * @author Lukas Balzer
	 * 
	 */
	public CSEditor() {
		this.zoomLevel = 1.0;
		this.viewLocation = null;
	}

	/**
	 * creates fly out palette with tools
	 * 
	 * @author Lukas Balzer, Aliaksei Babkovich
	 */
	@Override
	public PaletteRoot getPaletteRoot() {
		PaletteRoot root = new PaletteRoot();

		PaletteDrawer manipGroup = new PaletteDrawer(
				Messages.ManipulationObjects);
		root.add(manipGroup);

		PanningSelectionToolEntry selectionToolEntry = new PanningSelectionToolEntry();
		selectionToolEntry.setDescription(Messages.SpacePlusMouseTo);
		manipGroup.add(selectionToolEntry);
		manipGroup.add(new MarqueeToolEntry());

		root.setDefaultEntry(selectionToolEntry);

		PaletteSeparator separator = new PaletteSeparator();
		root.add(separator);
		PaletteDrawer componentElements = new PaletteDrawer(
				Messages.ComponentElements);
		root.add(componentElements);

		ImageDescriptor imgDesc = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/controller_32.png"); //$NON-NLS-1$
		ImageDescriptor imgDescLarge = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/controller_40.png"); //$NON-NLS-1$
		componentElements.add(new CombinedTemplateCreationEntry(
				Messages.Controller, Messages.CreateController,
				ComponentType.CONTROLLER, new CSModelCreationFactory(
						ComponentType.CONTROLLER, this.modelInterface),
				imgDesc, imgDescLarge));

		imgDesc = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/actuator_32.png"); //$NON-NLS-1$
		imgDescLarge = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/actuator_40.png"); //$NON-NLS-1$
		componentElements.add(new CombinedTemplateCreationEntry(
				Messages.Actuator, Messages.CreateActuator,
				ComponentType.ACTUATOR, new CSModelCreationFactory(
						ComponentType.ACTUATOR, this.modelInterface), imgDesc,
				imgDescLarge));

		imgDesc = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/process_32.png"); //$NON-NLS-1$
		imgDescLarge = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/process_40.png"); //$NON-NLS-1$
		componentElements.add(new CombinedTemplateCreationEntry(
				Messages.ControlledProcess, Messages.CreateControlledProcess,
				ComponentType.CONTROLLED_PROCESS, new CSModelCreationFactory(
						ComponentType.CONTROLLED_PROCESS, this.modelInterface),
				imgDesc, imgDescLarge));

		imgDesc = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/sensor_32.png"); //$NON-NLS-1$
		imgDescLarge = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/sensor_40.png"); //$NON-NLS-1$
		componentElements.add(new CombinedTemplateCreationEntry(
				Messages.Sensor, Messages.CreateSensor, ComponentType.SENSOR,
				new CSModelCreationFactory(ComponentType.SENSOR,
						this.modelInterface), imgDesc, imgDescLarge));

		imgDesc = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/ControlAction_32.png"); //$NON-NLS-1$
		imgDescLarge = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/ControlAction_40.png"); //$NON-NLS-1$
		componentElements.add(new CombinedTemplateCreationEntry(
				Messages.ControlAction, "Create Control Action Component",
				ComponentType.CONTROLACTION, new CSModelCreationFactory(
						ComponentType.CONTROLACTION, this.modelInterface),
				imgDesc, imgDescLarge));

		root.add(separator);
		PaletteDrawer connectionElements = new PaletteDrawer(
				Messages.ConnectingElements);
		root.add(connectionElements);

		imgDesc = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/arrow_simple_32.png"); //$NON-NLS-1$
		imgDescLarge = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/arrow_simple_40.png"); //$NON-NLS-1$
		connectionElements.add(new ConnectionCreationToolEntry(Messages.Arrow,
				Messages.CreateConnections, new ConnectionCreationFactory(
						ConnectionType.ARROW_SIMPLE), imgDesc, imgDescLarge));

		imgDesc = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/arrow_dashed_32.png"); //$NON-NLS-1$
		imgDescLarge = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/arrow_dashed_40.png"); //$NON-NLS-1$
		connectionElements.add(new ConnectionCreationToolEntry(
				Messages.DashedArrows, Messages.CreateConnections,
				new ConnectionCreationFactory(ConnectionType.ARROW_DASHED),
				imgDesc, imgDescLarge));

		root.add(separator);
		PaletteDrawer otherElements = new PaletteDrawer(Messages.Others);
		root.add(otherElements);
		imgDesc = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/text_box_32.png"); //$NON-NLS-1$
		imgDescLarge = Activator
				.getImageDescriptor("/icons/buttons/controlstructure/text_box_40.png"); //$NON-NLS-1$
		otherElements.add(new CombinedTemplateCreationEntry(Messages.TextBox,
				Messages.CreateTextBox, ComponentType.TEXTFIELD,
				new CSModelCreationFactory(ComponentType.TEXTFIELD,
						this.modelInterface), imgDesc, imgDescLarge));

		return root;
	}

	@Override
	public String getTitle() {
		return Messages.ControlStructure;
	}

	@Override
	public String getId() {
		return CSEditor.ID;
	}

	@Override
	public double getZoomLevel() {
		System.out.println(this.zoomLevel);
		return this.zoomLevel;
	}

	@Override
	public void setZoomLevel(double zoom) {
		this.zoomLevel = zoom;

	}

	@Override
	public Viewport getViewport() {
		return this.viewLocation;
	}

	@Override
	public void setViewport(Viewport view) {
		this.viewLocation = view;
	}

	/**
	 * @param values
	 *            <ol>
	 *            <li>[0] must be the filePath
	 *            <li>[1] if there is a second value it is assumed as the offset
	 *            if not a default value is used
	 *            <li>[3] if there is a third value it is assumed as the boolean
	 *            deciding the decoration if there is no such value decoration
	 *            is turned off
	 *            </ol>
	 */
	@Override
	public boolean triggerExport(Object[] values) {
		return this.initExport(Messages.ExportCS, Messages.ExportingCS, values);
	}

	@Override
	public Class<?> getExportWizard() {
		return ControlStructureWizard.class;
	}

}