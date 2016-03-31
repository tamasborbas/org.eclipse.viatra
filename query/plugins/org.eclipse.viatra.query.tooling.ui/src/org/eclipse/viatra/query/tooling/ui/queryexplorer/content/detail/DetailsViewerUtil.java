/*******************************************************************************
 * Copyright (c) 2010-2012, Zoltan Ujhelyi, Tamas Szabo, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zoltan Ujhelyi, Tamas Szabo - initial API and implementation
 *******************************************************************************/

package org.eclipse.viatra.query.tooling.ui.queryexplorer.content.detail;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.tooling.ui.queryexplorer.QueryExplorer;
import org.eclipse.viatra.query.tooling.ui.queryexplorer.content.matcher.PatternMatchContent;
import org.eclipse.viatra.query.tooling.ui.queryexplorer.content.matcher.PatternMatcherContent;
import org.eclipse.viatra.query.tooling.ui.queryexplorer.util.QueryExplorerPatternRegistry;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * A collection of useful utility methods for the details and filters viewer in the {@link QueryExplorer}.
 * 
 * @author Tamas Szabo (itemis AG)
 * 
 */
@Singleton
public class DetailsViewerUtil {

    @Inject
    private Injector injector;

    private final Set<String> primitiveTypes;

    protected DetailsViewerUtil() {
        primitiveTypes = new HashSet<String>();
        primitiveTypes.add(Boolean.class.getName());
        primitiveTypes.add(Character.class.getName());
        primitiveTypes.add(Byte.class.getName());
        primitiveTypes.add(Short.class.getName());
        primitiveTypes.add(Integer.class.getName());
        primitiveTypes.add(Long.class.getName());
        primitiveTypes.add(Float.class.getName());
        primitiveTypes.add(Double.class.getName());
        primitiveTypes.add(Void.class.getName());
        primitiveTypes.add(String.class.getName());
    }

    public boolean isPrimitiveType(String fqn) {
        return primitiveTypes.contains(fqn);
    }

    public void prepareFor(PatternMatchContent match, TableViewer viewer) {
        clearTableViewerColumns(viewer);
        String[] titles = { "Parameter", "Value" };
        createColumns(viewer, titles);
        viewer.setUseHashlookup(true);
        viewer.setColumnProperties(titles);
        viewer.setContentProvider(new ObservableListContentProvider());
        viewer.setLabelProvider(new DetailElementLabelProvider());
        viewer.setCellModifier(new DetailElementCellModifier());
        viewer.setComparator(new ViewerComparator(new DetailComparator(match.getPatternMatch().parameterNames())));

        DetailObserver observer = new DetailObserver(match);
        viewer.setInput(observer);
    }

    public void prepareFor(PatternMatcherContent observableMatcher, TableViewer viewer) {
        clearTableViewerColumns(viewer);
        String[] titles = { "Parameter", "Filter", "Class" };
        createColumns(viewer, titles);
        viewer.setUseHashlookup(true);
        viewer.setColumnProperties(titles);
        viewer.setContentProvider(new MatcherConfigurationContentProvider());
        viewer.setLabelProvider(new MatcherConfigurationLabelProvider());
        viewer.setCellModifier(new MatcherConfigurationCellModifier(viewer));
        viewer.setComparator(new ViewerComparator(new DetailComparator(observableMatcher.getMatcher()
                .getParameterNames())));

        Table table = viewer.getTable();
        CellEditor[] editors = new CellEditor[titles.length];

        editors[0] = new TextCellEditor(table);
        ModelElementCellEditor cellEditor = new ModelElementCellEditor(table, observableMatcher);
        injector.injectMembers(cellEditor);
        editors[1] = cellEditor;
        editors[2] = new TextCellEditor(table);

        viewer.setCellEditors(editors);

        IQuerySpecification<?> specification = QueryExplorerPatternRegistry.getInstance().getPatternByFqn(
                observableMatcher.getPatternName());
        Object[] filter = observableMatcher.getFilter();
        MatcherConfiguration[] input;
        if (specification != null) {
            input = new MatcherConfiguration[specification.getParameters().size()];
            if (filter != null) {
                for (int i = 0; i < specification.getParameters().size(); i++) {
                    PParameter var = specification.getParameters().get(i);
                    input[i] = new MatcherConfiguration(var.getName(), var.getTypeName(), filter[i]);
                }
            }
        } else {
            input = new MatcherConfiguration[0];
        }
        viewer.setInput(input);
    }

    public void clearTableViewerColumns(TableViewer viewer) {
        if (viewer.getContentProvider() != null) {
            viewer.setInput(null);
        }
        while (viewer.getTable().getColumnCount() > 0) {
            // calling dispose will decrement the column count of the table
            viewer.getTable().getColumns()[0].dispose();
        }

        if (viewer.getCellEditors() != null) {
            for (CellEditor cellEditor : viewer.getCellEditors()) {
                cellEditor.dispose();
            }
        }

        viewer.refresh();
    }

    private void createColumns(TableViewer viewer, String[] titles) {
        for (int i = 0; i < titles.length; i++) {
            createTableViewerColumn(viewer, titles[i], i);
        }
    }

    private TableViewerColumn createTableViewerColumn(TableViewer viewer, String title, int index) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE, index);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setResizable(true);
        column.setMoveable(true);
        column.setWidth(150);
        return viewerColumn;
    }

    public Object createValue(String classFqn, Object value) {
        if (!(value instanceof String)) {
            return value;
        } else if (value.toString().matches("")) {
            return null;
        } else if (String.class.getName().matches(classFqn)) {
            return value;
        } else {
            try {
                Class<?> clazz = Class.forName(classFqn);
                Method method = clazz.getMethod("valueOf", String.class);
                return method.invoke(null, value);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public boolean isValidValue(String classFqn, String value) {
        classFqn = classFqn.toLowerCase();

        if (Boolean.class.getName().toLowerCase().matches(classFqn)) {
            return value.toLowerCase().matches("true") || value.toLowerCase().matches("false");
        } else if (Character.class.getName().toLowerCase().matches(classFqn)) {
            return true;
        } else if (Byte.class.getName().toLowerCase().matches(classFqn)
                || Short.class.getName().toLowerCase().matches(classFqn)
                || Integer.class.getName().toLowerCase().matches(classFqn)
                || Long.class.getName().toLowerCase().matches(classFqn)) {
            return value.matches("[0-9]*");
        } else if (Float.class.getName().toLowerCase().matches(classFqn)
                || Double.class.getName().toLowerCase().matches(classFqn)) {
            return value.matches("[0-9]*\\.?[0-9]*");
        } else {
            return true;
        }
    }
}
