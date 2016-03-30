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

package org.eclipse.viatra.query.patternlanguage.emf.tests.types

import com.google.inject.Inject
import com.google.inject.Injector
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.viatra.query.patternlanguage.emf.tests.EMFPatternLanguageInjectorProvider
import org.eclipse.viatra.query.patternlanguage.emf.eMFPatternLanguage.PatternModel
import org.eclipse.viatra.query.patternlanguage.emf.validation.EMFIssueCodes
import org.eclipse.viatra.query.patternlanguage.emf.validation.EMFPatternLanguageJavaValidator
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.junit4.validation.ValidatorTester
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*
import org.junit.Ignoreimport org.eclipse.viatra.query.patternlanguage.emf.tests.util.AbstractValidatorTest
import org.eclipse.viatra.query.patternlanguage.emf.types.IEMFTypeProvider

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EMFPatternLanguageInjectorProvider))
class TypeInferenceTest extends AbstractValidatorTest {
	
	@Inject
	ParseHelper<PatternModel> parseHelper
	
	@Inject
	EMFPatternLanguageJavaValidator validator
	
	@Inject
	Injector injector
	
	@Inject
	private IEMFTypeProvider typeProvider
	
	ValidatorTester<EMFPatternLanguageJavaValidator> tester
	
	@Inject extension ValidationTestHelper
	
	@Before
	def void initialize() {
		tester = new ValidatorTester(validator, injector)
	}
	
	@Test
	def zeroLevelType() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"

			pattern first(class1) = {
				EClass(class1);
			}
		')
		model.assertNoErrors
		tester.validate(model).assertAll(getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE))
		
		val param = model.patterns.get(0).parameters.get(0)
		val type = typeProvider.getVariableType(param)
		assertEquals(typeof(EClass).canonicalName, type.qualifiedName) 
	}
	
	@Test
	def firstLevelFindType() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"

			pattern first(class1) = {
				EClass(class1);
			}

			pattern second(class2) = {
				find first(class2);
			}
		')
		model.assertNoErrors
		tester.validate(model).assertAll(getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE), getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE))
		
		val param1 = model.patterns.get(0).parameters.get(0)
		val param2 = model.patterns.get(1).parameters.get(0)
		val type1 = typeProvider.getVariableType(param1)
		val type2 = typeProvider.getVariableType(param2)
		assertEquals(typeof(EClass).canonicalName, type1.qualifiedName)
		assertEquals(typeof(EClass).canonicalName, type2.qualifiedName)
	}
	
	@Test
	def secondLevelFindType() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"

			pattern first(class1) = {
				EClass(class1);
			}

			pattern second(class2) = {
				find first(class2);
			}

			pattern third(class3) = {
				find second(class3);
			}
		')
		model.assertNoErrors
		tester.validate(model).assertAll(
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE),
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE),
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE)
		)
		
		val param1 = model.patterns.get(0).parameters.get(0)
		val param2 = model.patterns.get(1).parameters.get(0)
		val param3 = model.patterns.get(2).parameters.get(0)
		val type1 = typeProvider.getVariableType(param1)
		val type2 = typeProvider.getVariableType(param2)
		val type3 = typeProvider.getVariableType(param3)
		assertEquals(typeof(EClass).canonicalName, type1.qualifiedName)
		assertEquals(typeof(EClass).canonicalName, type2.qualifiedName)
		assertEquals(typeof(EClass).canonicalName, type3.qualifiedName)
	}
	
	@Test
	def zeroLevelPathType() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"

			pattern firstPath(class1, attribute1) = {
				EClass.eStructuralFeatures(class1, attribute1);
			}
		')
		model.assertNoErrors
		tester.validate(model).assertAll(
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE),
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE)
		)
		
		val param1 = model.patterns.get(0).parameters.get(0)
		val param2 = model.patterns.get(0).parameters.get(1)
		val type1 = typeProvider.getVariableType(param1)
		val type2 = typeProvider.getVariableType(param2)
		assertEquals(typeof(EClass).canonicalName, type1.qualifiedName)
		assertEquals(typeof(EStructuralFeature).canonicalName, type2.qualifiedName)
	}
	
	@Test
	def firstLevelPathType() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"

			pattern firstPath(class1, attribute1) = {
				EClass.eStructuralFeatures(class1, attribute1);
			}

			pattern secondPath(class2, attribute2) = {
				find firstPath(class2, attribute2);
			}
		')
		model.assertNoErrors
		tester.validate(model).assertAll(
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE),
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE),
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE),
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE)
		)
		
		val param11 = model.patterns.get(0).parameters.get(0)
		val param21 = model.patterns.get(0).parameters.get(1)
		val param12 = model.patterns.get(1).parameters.get(0)
		val param22 = model.patterns.get(1).parameters.get(1)
		val type11 = typeProvider.getVariableType(param11)
		val type21 = typeProvider.getVariableType(param21)
		val type12 = typeProvider.getVariableType(param12)
		val type22 = typeProvider.getVariableType(param22)
		assertEquals(typeof(EClass).canonicalName, type11.qualifiedName)
		assertEquals(typeof(EClass).canonicalName, type12.qualifiedName)
		assertEquals(typeof(EStructuralFeature).canonicalName, type21.qualifiedName)
		assertEquals(typeof(EStructuralFeature).canonicalName, type22.qualifiedName)
	}
	
	@Test
	def injectivityConstraintTest() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"

			pattern injectivity1(class1, class2) = {
				EClass(class1);
				class1 == class2;
			}
		')
		model.assertNoErrors
		tester.validate(model).assertAll(
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE),
		    getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE)
		)
		
		val param1 = model.patterns.get(0).parameters.get(0)
		val param2 = model.patterns.get(0).parameters.get(1)
		val type1 = typeProvider.getVariableType(param1)
		val type2 = typeProvider.getVariableType(param2)
		assertEquals(typeof(EClass).canonicalName, type1.qualifiedName)
		assertEquals(typeof(EClass).canonicalName, type2.qualifiedName)
	}
	
	@Test
	def parameterTest() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"

			pattern parameterTest(parameter) = {
				EDataType(parameter); 
			} or { 
				EClass(parameter);
			} 
		')
		model.assertNoErrors
		tester.validate(model).assertAll(getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE))
		
		val parameter1 = model.patterns.get(0).parameters.get(0)
		val variable1 = model.patterns.get(0).bodies.get(0).variables.get(0)
		val variable2 = model.patterns.get(0).bodies.get(1).variables.get(0)
		val type1 = typeProvider.getVariableType(parameter1)
		val type2 = typeProvider.getVariableType(variable1)
		val type3 = typeProvider.getVariableType(variable2)
		assertEquals(typeof(EClassifier).canonicalName, type1.qualifiedName)
		assertEquals(typeof(EDataType).canonicalName, type2.qualifiedName)
		assertEquals(typeof(EClass).canonicalName, type3.qualifiedName)
	}
	
	@Test
	def parameterTest2() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"

			pattern parameterTest2(parameter : EClassifier) = {
				EDataType(parameter); 
			} or { 
				EClass(parameter);
			} 
		')
		model.assertNoErrors
		tester.validate(model).assertOK
		
		val parameter1 = model.patterns.get(0).parameters.get(0)
		val variable1 = model.patterns.get(0).bodies.get(0).variables.get(0)
		val variable2 = model.patterns.get(0).bodies.get(1).variables.get(0)
		val type1 = typeProvider.getVariableType(parameter1)
		val type2 = typeProvider.getVariableType(variable1)
		val type3 = typeProvider.getVariableType(variable2)
		assertEquals(typeof(EClassifier).canonicalName, type1.qualifiedName)
		assertEquals(typeof(EDataType).canonicalName, type2.qualifiedName)
		assertEquals(typeof(EClass).canonicalName, type3.qualifiedName)
	}
	
	@Test
	def intLiteralType() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			pattern literalValue(literalType) = {
				literalType == 10;
			}
		')
		model.assertNoErrors
		tester.validate(model).assertOK
		
		val param = model.patterns.get(0).parameters.get(0)
		val type = typeProvider.getVariableType(param)
		assertEquals(typeof(Integer).canonicalName, type.qualifiedName) 
	}
	
	@Test
	def stringLiteralType() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			pattern literalValue(literalType) = {
				literalType == "helloworld";
			}
		')
		model.assertNoErrors
		tester.validate(model).assertOK
		
		val param = model.patterns.get(0).parameters.get(0)
		val type = typeProvider.getVariableType(param)
		assertEquals(typeof(String).canonicalName, type.qualifiedName) 
	}
	
	@Test
	def boolLiteralType() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			pattern literalValue(literalType) = {
				literalType == true;
			}
		')
		model.assertNoErrors
		tester.validate(model).assertOK
		
		val param = model.patterns.get(0).parameters.get(0)
		val type = typeProvider.getVariableType(param)
		assertEquals(typeof(Boolean).canonicalName, type.qualifiedName) 
	}
	
	@Test
	def doubleLiteralType() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			pattern literalValue(literalType) = {
				literalType == 3.14;
			}
		')
		model.assertNoErrors
		tester.validate(model).assertOK
		
		val param = model.patterns.get(0).parameters.get(0)
		val type = typeProvider.getVariableType(param)
		assertEquals(typeof(Double).canonicalName, type.qualifiedName) 
	}
	
	@Test
	def countAggregatedComputationValueType() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			pattern literalValue(literalType) = {
				uselessVariable == 10;
				literalType == count find patternToFind(uselessVariable);
			}

			pattern patternToFind(uselessParameter) = {
				uselessParameter == 10;
				check(true);
			}
		')
		model.assertNoErrors
		tester.validate(model).assertOK
		
		val param = model.patterns.get(0).parameters.get(0)
		val type = typeProvider.getVariableType(param)
		assertEquals("literalType", param.name) 
		assertEquals(typeof(Integer).canonicalName, type.qualifiedName) 
	}
	
	@Test
	def errorTypeTest1() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"
			
			pattern errorTypeTest(parameter) = {
				EClass(parameter);
				EDataType(parameter);
			} 
		')
		tester.validate(model).assertAll(getErrorCode(EMFIssueCodes::VARIABLE_TYPE_INVALID_ERROR), getInfoCode(EMFIssueCodes::MISSING_PARAMETER_TYPE))
	}
	
	@Test
	def errorTypeTest2() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"
			
			pattern warningTypeTest1(parameter : EClass) = {
				EDataType(parameter);
			} 
		')
		tester.validate(model).assertError(EMFIssueCodes::VARIABLE_TYPE_INVALID_ERROR)
		
		val param = model.patterns.get(0).parameters.get(0)
		val type = typeProvider.getVariableType(param)
		assertEquals("parameter", param.name) 
		assertEquals(typeof(EClass).canonicalName, type.qualifiedName) 
	}
	
	@Test
	def errorTypeTest3() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"
			
			pattern warningTypeTest2(parameter : EDataType) = {
				EClass(parameter);
			} 
		')
		tester.validate(model).assertError(EMFIssueCodes::VARIABLE_TYPE_INVALID_ERROR)
		
		val param = model.patterns.get(0).parameters.get(0)
		val type = typeProvider.getVariableType(param)
		assertEquals("parameter", param.name) 
		assertEquals(typeof(EDataType).canonicalName, type.qualifiedName) 
	}
	
	@Test
	@Ignore
	def warningTypeTest1() {
		val model = parseHelper.parse('
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/emf/2002/Ecore"
			
			pattern warningTypeTest3(parameter : EClassifier) = {
				EClass(parameter);
			} 
		')
		tester.validate(model).assertWarning(EMFIssueCodes::PARAMETER_TYPE_INVALID)
		
		val param = model.patterns.get(0).parameters.get(0)
		val type = typeProvider.getVariableType(param)
		assertEquals("parameter", param.name) 
		assertEquals(typeof(EClass).canonicalName, type.qualifiedName) 
	}
	
}