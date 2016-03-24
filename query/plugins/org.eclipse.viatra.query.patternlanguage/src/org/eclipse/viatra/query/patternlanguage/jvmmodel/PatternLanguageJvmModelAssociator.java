/*******************************************************************************
 * Copyright (c) 2010-2016, Zoltan Ujhelyi, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zoltan Ujhelyi - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.query.patternlanguage.jvmmodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.Annotation;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.AnnotationParameter;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.Constraint;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.ParameterRef;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.Pattern;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.PatternBody;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.PatternLanguageFactory;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.PatternLanguagePackage;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.Variable;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.VariableReference;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.VariableValue;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class PatternLanguageJvmModelAssociator extends JvmModelAssociator {

    @Override
    public void installDerivedState(DerivedStateAwareResource resource, boolean preIndexingPhase) {
        calculateDerivedVariableObjects(resource);
        super.installDerivedState(resource, preIndexingPhase);
    }

    protected void calculateDerivedVariableObjects(DerivedStateAwareResource resource) {
        TreeIterator<EObject> it = resource.getAllContents();
        while(it.hasNext()) {
            EObject obj = it.next();
            if (obj instanceof Pattern) {
                Pattern pattern = (Pattern) obj;
                for (PatternBody body : pattern.getBodies()) {
                    EList<Variable> variables = body.getVariables();
                    variables.addAll(getAllVariablesInBody(body, variables));
                }
                for (Annotation annotation : pattern.getAnnotations()) {
                    for (AnnotationParameter parameter : annotation.getParameters()) {
                        if ((parameter.getValue()) instanceof VariableValue) {
                            final VariableReference reference = ((VariableValue) parameter.getValue()).getValue();
                            Variable declaration = Iterables.find(pattern.getParameters(), new Predicate<Variable>() {
    
                                @Override
                                public boolean apply(Variable variable) {
                                    return Objects.equals(variable.getName(), reference.getVar());
                                }
                            }, null);
                            if (declaration != null) {
                                reference.setVariable(declaration);
                            }
                        }
                    }
                }
                it.prune();
                
            }
        }
    }

    private EList<Variable> getAllVariablesInBody(PatternBody body, EList<Variable> previous) {
        EList<Variable> variables = previous;

        Map<String, Variable> parameterMap = new HashMap<String, Variable>();

        EList<Variable> parameters = ((Pattern) body.eContainer()).getParameters();
        for (Variable var : variables) {
            parameterMap.put(var.getName(), var);
        }
        for (Variable var : parameters) {
            if (!parameterMap.containsKey(var.getName())) {
                // Creating a new paramater ref variable
                ParameterRef refVar = initializeParameterRef(var);
                parameterMap.put(var.getName(), refVar);
                variables.add(refVar);
            }
        }
        int unnamedCounter = 0;
        for (Constraint constraint : body.getConstraints()) {
            Iterator<EObject> it = constraint.eAllContents();
            while (it.hasNext()) {
                EObject obj = it.next();
                if (obj instanceof VariableReference) {
                    VariableReference varRef = (VariableReference) obj;
                    String varName = varRef.getVar();
                    if ("_".equals(varName)) {
                        varName = String.format("_<%d>", unnamedCounter);
                        unnamedCounter++;
                    }
                    Variable var;
                    if (parameterMap.containsKey(varName)) {
                        var = parameterMap.get(varName);
                    } else {
                        var = initializeLocalVariable(varName);
                        variables.add(var);
                        parameterMap.put(varName, var);
                    }
                    if (!varRef.eIsSet(PatternLanguagePackage.Literals.VARIABLE_REFERENCE__VARIABLE) || !varRef.getVariable().equals(var)) {
                        varRef.setVariable(var);
                    }
                }
            }
        }

        return variables;
    }

    private Variable initializeLocalVariable(String varName) {
        Variable decl;
        decl = PatternLanguageFactory.eINSTANCE.createVariable();
        decl.setName(varName);
        return decl;
    }

    private ParameterRef initializeParameterRef(Variable var) {
        ParameterRef refVar = PatternLanguageFactory.eINSTANCE.createParameterRef();
        refVar.setName(var.getName());
        // refVar.setType(var.getType());
        refVar.setReferredParam(var);
        return refVar;
    }
}