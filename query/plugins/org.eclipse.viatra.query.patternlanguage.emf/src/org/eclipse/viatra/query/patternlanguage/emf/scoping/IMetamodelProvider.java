/*******************************************************************************
 * Copyright (c) 2010-2012, Zoltan Ujhelyi, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zoltan Ujhelyi - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.query.patternlanguage.emf.scoping;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

public interface IMetamodelProvider {

    /**
     * Returns a set of all available EPackages wrapped into {@link IEObjectDescription} for the use of scoping.
     * It uses the {@link IEObjectDescription}s from the delegate scope provider too, this way the 
     * {@link EPackage}s from the XText index will be available too. 
     * 
     * @param delegateScope the delegate scope
     * @param context the context object for the scoping
     */
    IScope getAllMetamodelObjects(IScope delegateScope, EObject context);

    /**
     * Loads an EMF package from the nsURI or resource URI of the model, and uses the resource set given as the second
     * parameter.
     * 
     * @param uri
     * @param resourceSet
     * @return the loaded EMF EPackage
     */
    EPackage loadEPackage(String uri, ResourceSet resourceSet);

    boolean isGeneratedCodeAvailable(EPackage ePackage, ResourceSet set);
    
    String getQualifiedClassName(EClassifier classifier, EObject context);
}