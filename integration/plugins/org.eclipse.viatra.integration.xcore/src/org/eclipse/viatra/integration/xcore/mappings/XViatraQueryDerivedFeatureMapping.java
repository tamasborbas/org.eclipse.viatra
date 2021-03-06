/*******************************************************************************
 * Copyright (c) 2010-2012, Tamas Szabo, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tamas Szabo (itemis AG) - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.integration.xcore.mappings;

import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xcore.mappings.AbstractMapping;

public class XViatraQueryDerivedFeatureMapping extends AbstractMapping {

    private EStructuralFeature eStructuralFeature;

    private GenFeature genFeature;

    public GenFeature getGenFeature() {
        return genFeature;
    }

    public void setGenFeature(GenFeature genFeature) {
        this.genFeature = genFeature;
    }

    public EStructuralFeature getEStructuralFeature() {
        return eStructuralFeature;
    }

    public void setEStructuralFeature(EStructuralFeature eStructuralFeature) {
        this.eStructuralFeature = eStructuralFeature;
    }

}
