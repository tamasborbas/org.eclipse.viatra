/**
 * Copyright (c) 2010-2016, Peter Lunk, IncQuery Labs Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Peter Lunk - initial API and implementation
 */
package org.eclipse.viatra.transformation.ui.wizards;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.google.inject.Inject;

/**
 * New transformation wizard page that enables the user to define optional details of the created transformation.
 * Details:
 *  - Transformation type --> Batch or Event-driven
 *  - Log4J logger support --> add a default logger to the generated class
 *  - Debugger support --> The generated transformation initializes the VIATRA debugger
 *  
 * @author Peter Lunk
 *
 */
public class NewTransformationTypeSelectionWizardPage extends WizardPage {
    private TransformationType transformationType = TransformationType.BatchTransformation;
    private boolean logging = false;
    private boolean debugger = false;

    @Inject
    protected NewTransformationTypeSelectionWizardPage() {
        super(NewTransformationWizard.TYPEPAGENAME);
        this.setTitle(NewTransformationWizard.TITLE);
        this.setDescription("Define VIATRA transformation details.");
        
    }
    
    public TransformationType getTransformationType() {
        return transformationType;
    }

    public boolean isLogging() {
        return logging;
    }

    public boolean isDebugger() {
        return debugger;
    }

    @Override
    public void createControl(Composite parent) {
        final int nColumns = 5;

        initializeDialogUnits(parent);

        Composite composite = new Composite(parent, SWT.NONE);
        composite.setFont(parent.getFont());

        GridData gd_1 = new GridData(GridData.FILL_HORIZONTAL);
        gd_1.horizontalSpan = 3;

        GridData gd_2 = new GridData(GridData.FILL_HORIZONTAL);
        gd_2.horizontalSpan = nColumns;

        setControl(composite);
        composite.setLayout(new FormLayout());

        Label transformationTypeLabel = new Label(composite, SWT.NONE);
        FormData fd_transformationTypeLabel = new FormData();
        fd_transformationTypeLabel.top = new FormAttachment(0, 29);
        fd_transformationTypeLabel.left = new FormAttachment(0, 28);
        transformationTypeLabel.setLayoutData(fd_transformationTypeLabel);
        transformationTypeLabel.setText("Select VIATRA Transformation Type:");

        final ComboViewer transformationTypeViewer = new ComboViewer(composite, SWT.NONE);
        Combo transformationTypeCombo = transformationTypeViewer.getCombo();
        FormData fd_transformationTypeCombo = new FormData();
        fd_transformationTypeCombo.right = new FormAttachment(0, 569);
        fd_transformationTypeCombo.top = new FormAttachment(0, 25);
        fd_transformationTypeCombo.left = new FormAttachment(0, 247);
        transformationTypeCombo.setLayoutData(fd_transformationTypeCombo);
        transformationTypeViewer.setContentProvider(ArrayContentProvider.getInstance());
        transformationTypeViewer.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof TransformationType) {
                    return element.toString();
                }
                return "";
            }
        });
        transformationTypeViewer.setInput(TransformationType.values());
        transformationTypeViewer.setSelection(new StructuredSelection(transformationType));
        transformationTypeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                transformationType = (TransformationType) ((StructuredSelection) transformationTypeViewer
                        .getSelection()).getFirstElement();

            }
        });

        final Button loggingCheckbox = new Button(composite, SWT.CHECK);
        FormData fd_loggingCheckbox = new FormData();
        fd_loggingCheckbox.top = new FormAttachment(0, 253);
        fd_loggingCheckbox.left = new FormAttachment(0, 28);
        loggingCheckbox.setLayoutData(fd_loggingCheckbox);
        loggingCheckbox.setText("Apache Log4J  Logging support");
        loggingCheckbox.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                logging = loggingCheckbox.getSelection();

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                logging = loggingCheckbox.getSelection();
            }
        });

        final Button debuggerCheckbox = new Button(composite, SWT.CHECK);
        FormData fd_debuggerCheckbox = new FormData();
        fd_debuggerCheckbox.top = new FormAttachment(0, 294);
        fd_debuggerCheckbox.left = new FormAttachment(0, 28);
        debuggerCheckbox.setLayoutData(fd_debuggerCheckbox);
        debuggerCheckbox.setText("VIATRA debugger support");
        debuggerCheckbox.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                 debugger = debuggerCheckbox.getSelection();

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                debugger = debuggerCheckbox.getSelection();

            }
        });
    }


    public enum TransformationType {
        BatchTransformation, EventDrivenTransformation
    }

}
