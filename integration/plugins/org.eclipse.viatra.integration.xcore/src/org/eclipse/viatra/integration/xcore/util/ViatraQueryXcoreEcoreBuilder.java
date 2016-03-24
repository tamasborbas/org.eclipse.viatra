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
package org.eclipse.viatra.integration.xcore.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XAttribute;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XReference;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.util.XcoreEcoreBuilder;
import org.eclipse.viatra.integration.xcore.generator.ViatraXcoreGenerator;
import org.eclipse.viatra.integration.xcore.mappings.ViatraQueryXcoreMapper;
import org.eclipse.viatra.integration.xcore.model.XViatraQueryDerivedFeature;
import org.eclipse.viatra.query.patternlanguage.helper.CorePatternLanguageHelper;
import org.eclipse.viatra.query.patternlanguage.patternLanguage.Pattern;
import org.eclipse.viatra.query.runtime.base.comprehension.WellbehavingDerivedFeatureRegistry;

import com.google.inject.Inject;

/**
 * The Ecore builder is responsible for creating the Ecore model for the Viatra Query & Xcore specific metamodel. Apart from
 * mapping all Xcore related model elements to Ecore specific model elements, it also needs to register the setting
 * delegates related annotations on both the {@link EPackage} and the {@link EStructuralFeature}s. This class will be
 * used in the dynamic instance model use case. <br>
 * <br>
 * The specified runtime annotations will then be used in the runtime Eclipse for the loaded Dynamic Instance Model. <br>
 * (1) The {@link EPackage} will have an annotation with the source EcorePackage.eNS_URI, key "settingDelegates" and
 * value "org.eclipse.viatra.query.querybasedfeature". <br>
 * (2) The {@link EStructuralFeature}s will have an annotation with the source "org.eclipse.viatra.query.querybasedfeature",
 * key "patternFQN" and the value will be the fully qualified name of the EIQ pattern which provides the value of the
 * feature. The {@link SettingDelegate}s will be loaded with the use of the special editor, specifically tailored for
 * this scenario.
 * 
 * @author Tamas Szabo (itemis AG)
 * 
 */
public class ViatraQueryXcoreEcoreBuilder extends XcoreEcoreBuilder {

    @Inject
    private ViatraQueryXcoreMapper mapper;

    @Override
    public EPackage getEPackage(XPackage xPackage) {
        EPackage pack = super.getEPackage(xPackage);
        EcoreUtil.setAnnotation(pack, EcorePackage.eNS_URI, "settingDelegates",
                ViatraXcoreGenerator.queryBasedFeatureFactory);
        return pack;
    }

    @Override
    protected EClass getEClass(final XClass xClass) {
        final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        mapper.getMapping(xClass).setEClass(eClass);
        mapper.getToXcoreMapping(eClass).setXcoreElement(xClass);
        if (xClass.isInterface()) {
            eClass.setInterface(true);
            eClass.setAbstract(true);
        } else if (xClass.isAbstract()) {
            eClass.setAbstract(true);
        }
        EList<EGenericType> eGenericSuperTypes = eClass.getEGenericSuperTypes();
        for (XGenericType superType : xClass.getSuperTypes()) {
            eGenericSuperTypes.add(getEGenericType(superType));
        }
        EList<ETypeParameter> eTypeParameters = eClass.getETypeParameters();
        for (XTypeParameter xTypeParameter : xClass.getTypeParameters()) {
            ETypeParameter eTypeParameter = getETypeParameter(xTypeParameter);
            eTypeParameters.add(eTypeParameter);
        }
        EList<EOperation> eOperations = eClass.getEOperations();
        EList<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
        for (XMember xMember : xClass.getMembers()) {
            if (xMember instanceof XOperation) {
                EOperation eOperation = getEOperation((XOperation) xMember);
                eOperations.add(eOperation);
            } else if (xMember instanceof XReference) {
                EReference eReference = getEReference((XReference) xMember);
                eStructuralFeatures.add(eReference);
            } else if (xMember instanceof XAttribute) {
                EAttribute eAttribute = getEAttribute((XAttribute) xMember);
                eStructuralFeatures.add(eAttribute);
            } else if (xMember instanceof XViatraQueryDerivedFeature) {
                EStructuralFeature eStructuralFeature = getViatraQueryDerivedFeature((XViatraQueryDerivedFeature) xMember);
                eStructuralFeatures.add(eStructuralFeature);
            }
        }
        return eClass;
    }

    protected EStructuralFeature getViatraQueryDerivedFeature(XViatraQueryDerivedFeature xViatraQueryDerivedFeature) {
        final EStructuralFeature eStructuralFeature = (xViatraQueryDerivedFeature.isReference() ? EcoreFactory.eINSTANCE
                .createEReference() : EcoreFactory.eINSTANCE.createEAttribute());
        mapper.getMapping(xViatraQueryDerivedFeature).setEStructuralFeature(eStructuralFeature);
        mapper.getToXcoreMapping(eStructuralFeature).setXcoreElement(xViatraQueryDerivedFeature);
        handleViatraQueryDerivedFeature(eStructuralFeature, xViatraQueryDerivedFeature);
        return eStructuralFeature;
    }

    /**
     * As a side effect of this call, the SettingDelegateFactory will be registered to handle things for dynamic
     * resources.
     * 
     * @param eStructuralFeature
     * @param xViatraQueryDerivedFeature
     */
    protected void handleViatraQueryDerivedFeature(final EStructuralFeature eStructuralFeature,
            final XViatraQueryDerivedFeature xViatraQueryDerivedFeature) {
        eStructuralFeature.setName(nonNullName(xViatraQueryDerivedFeature.getName()));
        handleETypedElement(eStructuralFeature, xViatraQueryDerivedFeature);
        eStructuralFeature.setChangeable(false);
        eStructuralFeature.setTransient(true);
        eStructuralFeature.setVolatile(true);
        eStructuralFeature.setUnsettable(true);
        eStructuralFeature.setDerived(true);

        runnables.add(new Runnable() {
            @Override
            public void run() {
                Pattern pattern = xViatraQueryDerivedFeature
                        .getPattern();

                if (pattern != null && pattern.eContainer() != null) {
                    EcoreUtil.setAnnotation(eStructuralFeature, ViatraXcoreGenerator.queryBasedFeatureFactory,
                            "patternFQN", CorePatternLanguageHelper.getFullyQualifiedName(pattern));

                    // mark the feature as well-behaving
                    WellbehavingDerivedFeatureRegistry.registerWellbehavingDerivedFeature(eStructuralFeature);
                }
            }
        });
    }
}
