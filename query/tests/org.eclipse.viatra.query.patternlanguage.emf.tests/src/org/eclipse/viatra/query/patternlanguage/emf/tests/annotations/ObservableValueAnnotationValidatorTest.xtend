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

package org.eclipse.viatra.query.patternlanguage.emf.tests.annotations

import com.google.inject.Inject
import com.google.inject.Injector
import org.eclipse.viatra.query.patternlanguage.emf.tests.EMFPatternLanguageInjectorProvider
import org.eclipse.viatra.query.patternlanguage.emf.tests.util.AbstractValidatorTest
import org.eclipse.viatra.query.patternlanguage.emf.validation.EMFPatternLanguageJavaValidator
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidatorTester
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.viatra.addon.databinding.runtime.util.validation.ObservableValuePatternValidator
import org.eclipse.viatra.query.patternlanguage.emf.annotations.AnnotationExpressionValidator


@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EMFPatternLanguageInjectorProvider))
class ObservableValueAnnotationValidatorTest extends AbstractValidatorTest{
		
@Inject
	ParseHelper parseHelper
	@Inject
	EMFPatternLanguageJavaValidator validator
	@Inject
	Injector injector
	
	ValidatorTester<EMFPatternLanguageJavaValidator> tester
	
	@Before
	def void initialize() {
		tester = new ValidatorTester(validator, injector)
	}
	@Test
	def void expressionShortName() {
		val model = parseHelper.parse(
			'package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name", expression = "p")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'
		) 
		tester.validate(model).assertOK
	}
	
	@Test
	def void expressionFullName() {
		val model = parseHelper.parse(
			'package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name", expression = "p.name")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'
		) 
		tester.validate(model).assertOK
	}
	@Test
	def void labelExpressionFullName() {
		val model = parseHelper.parse(
			'package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name", labelExpression = "$p.name$")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'
		) 
		tester.validate(model).assertOK
	}
	@Test
	def void labelExpressionFullNameUnescaped() {
		val model = parseHelper.parse(
			'package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name", labelExpression = "p.name")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'
		) 
		tester.validate(model).assertWarning(ObservableValuePatternValidator::GENERAL_ISSUE_CODE)
	}
	
	@Test
	def void expressionEmpty() {
		val model = parseHelper.parse(
			'package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name", expression = "")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'
		) 
		tester.validate(model).assertError(AnnotationExpressionValidator::GENERAL_ISSUE_CODE)
	}
	
	@Test
	def void expressionMismatch1() {
		val model = parseHelper.parse('''
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'''
		) 
		tester.validate(model).assertError(ObservableValuePatternValidator::EXPRESSION_MISMATCH_ISSUE_CODE)
	}
	@Test
	def void expressionMismatch2() {
		val model = parseHelper.parse('''
			package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name", expression = "p", labelExpression="label $p.name$")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'''
		) 
		tester.validate(model).assertError(ObservableValuePatternValidator::EXPRESSION_MISMATCH_ISSUE_CODE)
	}
	
	@Test
	def void expressionInDollars() {
		val model = parseHelper.parse(
			'package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name", expression = "$p.name$")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'
		) 
		tester.validate(model).assertAll(getWarningCode(ObservableValuePatternValidator::GENERAL_ISSUE_CODE),getErrorCode(AnnotationExpressionValidator::UNKNOWN_VARIABLE_CODE))
	}
	
	@Test
	def void expressionInvalidParameter1() {
		val model = parseHelper.parse(
			'package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name", expression = "p1")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'
		) 
		tester.validate(model).assertError(AnnotationExpressionValidator::UNKNOWN_VARIABLE_CODE)
	}
	@Test
	def void expressionInvalidParameter2() {
		val model = parseHelper.parse(
			'package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name", expression = "p1.name")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'
		) 
		tester.validate(model).assertError(AnnotationExpressionValidator::UNKNOWN_VARIABLE_CODE)
	}
	@Test
	def void expressionInvalidFeature() {
		val model = parseHelper.parse(
			'package org.eclipse.viatra.query.patternlanguage.emf.tests
			import "http://www.eclipse.org/viatra/query/patternlanguage/PatternLanguage"

			@ObservableValue(name = "name", expression = "p.notExists")
			pattern pattern2(p : Pattern) = {
				Pattern(p);
			}'
		) 
		tester.validate(model).assertError(AnnotationExpressionValidator::UNKNOWN_ATTRIBUTE_CODE)
	}
}