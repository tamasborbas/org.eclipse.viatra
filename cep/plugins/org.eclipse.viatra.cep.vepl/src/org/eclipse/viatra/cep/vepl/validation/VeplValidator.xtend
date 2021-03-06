/*******************************************************************************
 * Copyright (c) 2004-2014, Istvan David, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Istvan David - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.cep.vepl.validation

import com.google.common.base.Preconditions
import com.google.common.collect.Lists
import org.eclipse.viatra.cep.vepl.vepl.AbstractAtomicEventPattern
import org.eclipse.viatra.cep.vepl.vepl.Atom
import org.eclipse.viatra.cep.vepl.vepl.AtomicEventPattern
import org.eclipse.viatra.cep.vepl.vepl.ChainedExpression
import org.eclipse.viatra.cep.vepl.vepl.ComplexEventExpression
import org.eclipse.viatra.cep.vepl.vepl.ComplexEventPattern
import org.eclipse.viatra.cep.vepl.vepl.EventModel
import org.eclipse.viatra.cep.vepl.vepl.EventPattern
import org.eclipse.viatra.cep.vepl.vepl.Infinite
import org.eclipse.viatra.cep.vepl.vepl.ModelElement
import org.eclipse.viatra.cep.vepl.vepl.Multiplicity
import org.eclipse.viatra.cep.vepl.vepl.ParameterizedPatternCall
import org.eclipse.viatra.cep.vepl.vepl.QueryImport
import org.eclipse.viatra.cep.vepl.vepl.QueryResultChangeEventPattern
import org.eclipse.viatra.cep.vepl.vepl.Trait
import org.eclipse.viatra.cep.vepl.vepl.TraitList
import org.eclipse.viatra.cep.vepl.vepl.TypedParameterList
import org.eclipse.viatra.cep.vepl.vepl.VeplPackage
import org.eclipse.xtext.validation.Check

import static extension org.eclipse.viatra.cep.vepl.validation.ValidationHelper.*

class VeplValidator extends AbstractVeplValidator {

	public static val INVALID_NAME = 'invalidName'
	public static val INVALID_ARGUMENTS = 'invalidArguments'
	public static val MISSING_QUERY_IMPORT = "missingQueryImport"
	public static val ATOM_TIMEWINDOW_NO_MULTIPLICITY = "atomTimewindowNoMultiplicity"
	public static val SINGE_PLAIN_ATOM_IN_COMPLEX_EVENT_EXPRESSION = "singlePlainAtomInComplexEventExpression"
	public static val NON_POSITIVE_MULTIPLICITY = "nonPositiveMultiplicity"
	public static val INFINITE_MULTIPLICITY_WITH_TIMEWINDOW = "infiniteMultiplicityWithTimewindow"
	public static val NO_INFINITE_SUPPORT = "noInfiniteSupport"
	public static val NEGATIVE_OPERATOR_ON_NONATOMIC_REFERENCE = "negativeOperatorOnNonAtomicReference"
	public static val UNSAFE_INFINITE_MULTIPLICITY = "unsafeInfiniteMultiplicity"
	public static val PARAMETER_ON_NON_ATOMIC_PATTERN_CALL = "parameterOnNonAtomicPatternCall"
	public static val NEGATIVE_WITH_MULTIPLICITY = "negativeWithMultiplicity"
	public static val NEGATIVE_WITH_TIMEWINDOW = "negativeWithTimewindow"
	public static val DUPLICATE_TRAIT_PARAMETER_NAMES = "duplicateTraitParameterNames"
	public static val TRAIT_EXPERIMENTAL = "traitExperimental"
	public static val SHADOWED_TRAIT_PARAMETERS = "shadowedTraitParameters"

	@Check
	def uniqueName(ModelElement modelElement) {
		if(modelElement.name.nullOrEmpty) return;
		if(!(modelElement.eContainer instanceof EventModel)) return;

		var model = (modelElement.eContainer as EventModel)

		for (me : model.modelElements) {
			checkUniqueness(modelElement, me)
		}
	}

	def private checkUniqueness(ModelElement modelElement1, ModelElement modelElement2) {
		if (modelElement1.equals(modelElement2)) {
			return
		}
		if (modelElement1.name.equalsIgnoreCase(modelElement2.name))
			error("All model elements must have a unique name!", VeplPackage.Literals.MODEL_ELEMENT__NAME, INVALID_NAME)
	}

	@Check
	def validPatternCallArguments(ParameterizedPatternCall patternCall) {
		if(!patternCall.hasParameterList || patternCall.eventPattern == null) return

		var parameterList = patternCall.parameterList
		var eventPatternParameter = patternCall.eventPattern

		var patternParameterNumber = getParameterNumber(eventPatternParameter)

		if (parameterList.parameters.empty && patternParameterNumber != 0) {
			error("Pattern call parameters must be specified!",
				VeplPackage.Literals.PARAMETERIZED_PATTERN_CALL__PARAMETER_LIST, INVALID_ARGUMENTS)
		}
		if (parameterList.parameters.size != patternParameterNumber) {
			error("The exact number of parameters in the referred pattern must be specified!",
				VeplPackage.Literals.PARAMETERIZED_PATTERN_CALL__PARAMETER_LIST, INVALID_ARGUMENTS)
		}
	}

	def private int getParameterNumber(EventPattern eventPattern) {
		switch (eventPattern) {
			AtomicEventPattern:
				getTypedParameterListSize(eventPattern.parameters) + getTraitParameterListSize(eventPattern.traits)
			QueryResultChangeEventPattern:
				getTypedParameterListSize(eventPattern.parameters)
			ComplexEventPattern:
				getTypedParameterListSize(eventPattern.parameters)
			default:
				0
		}
	}

	def private int getTypedParameterListSize(TypedParameterList parameterList) {
		if(parameterList == null) return 0
		if(parameterList.parameters.nullOrEmpty) return 0
		return parameterList.parameters.size
	}

	def private int getTraitParameterListSize(TraitList traitList) {
		if (traitList == null) {
			return 0
		} else {
			traitList.traits.fold(0)[count, trait|count + trait.parameters.parameters.size]
		}
	}

	@Check
	def explicitlyImportedQueryPackage(QueryResultChangeEventPattern iqPatternEventPattern) {
		var eventModel = (iqPatternEventPattern.eContainer as EventModel)
		if (!(eventModel.imports.filter[i|i instanceof QueryImport].size == 1)) {
			error(
				"Missing 'import-patterns' statement for query reference.",
				VeplPackage.Literals.QUERY_RESULT_CHANGE_EVENT_PATTERN__QUERY_REFERENCE,
				MISSING_QUERY_IMPORT
			)
		}
	}

	@Check
	def expressionAtomWithTimewindowMustFeatureMultiplicity(Atom atom) {
		if (atom.hasTimewindow && !atom.hasMultiplicity) {
			error(
				"Timewindows on expression atoms are allowed only if multiplicity is also specified.",
				VeplPackage.Literals.COMPLEX_EVENT_EXPRESSION__TIMEWINDOW,
				ATOM_TIMEWINDOW_NO_MULTIPLICITY
			)
		} else if ((atom.multiplicity instanceof Multiplicity) && (atom.multiplicity as Multiplicity).value < 2) {
			error(
				"One atomic event does not result in a valid complex event.",
				VeplPackage.Literals.COMPLEX_EVENT_EXPRESSION__MULTIPLICITY,
				ATOM_TIMEWINDOW_NO_MULTIPLICITY
			)
		}
	}

	@Check
	def unsupportedMultiplicityTimewindowCombinations(Atom atom) {
		if (atom.hasMultiplicity && atom.hasTimewindow) {
			if (atom.multiplicity instanceof Infinite)
				error(
					"Infinite multiplicity cannot be combined with timewindow.",
					VeplPackage.Literals.COMPLEX_EVENT_EXPRESSION__MULTIPLICITY,
					INFINITE_MULTIPLICITY_WITH_TIMEWINDOW
				)
		}
	}

	@Check
	def complexEventPatternWithPlainAtomExpression(ComplexEventPattern eventPattern) {
		val expression = eventPattern.complexEventExpression

		if (expression.right.empty && (expression.left instanceof Atom)) {
			val atom = expression.left as Atom

			if (!atom.hasMultiplicity) {
				warning(
					"Using a single plain atomic event pattern in the complex event pattern is a bad design.",
					VeplPackage.Literals.COMPLEX_EVENT_PATTERN__COMPLEX_EVENT_EXPRESSION,
					SINGE_PLAIN_ATOM_IN_COMPLEX_EVENT_EXPRESSION
				)
			}
		}
	}

//	@Check
//	def negativeOperatorOnComplexEventPatternReference(ComplexEventExpression complexEventExpression) {
//		if (complexEventExpression.negOperator == null) {
//			return
//		}
//
//		val left = complexEventExpression.left as Atom
//
//		val patternCall = left.patternCall
//		if (!(patternCall.eventPattern instanceof AbstractAtomicEventPattern)) {
//			error(
//				"The NOT operator can be applied only on atomic event pattern references.",
//				VeplPackage.Literals.COMPLEX_EVENT_EXPRESSION__NEG_OPERATOR,
//				NEGATIVE_OPERATOR_ON_NONATOMIC_REFERENCE
//			)
//		}
//	}
	@Check
	def void unsafeStarOperator(ComplexEventExpression complexEventExpression) {
		if (!complexEventExpression.hasInfiniteMultiplicity) {
			return
		} else if (complexEventExpression.starOperatorIsLast) {
			error("Unsafe infinite multiplicity operator (\"{*}\").",
				VeplPackage.Literals.COMPLEX_EVENT_EXPRESSION__MULTIPLICITY, UNSAFE_INFINITE_MULTIPLICITY)
		}
	}

	def boolean starOperatorIsLast(ComplexEventExpression expression) {
		if (expression.eContainer instanceof ComplexEventPattern) { // no followup expressions
			return true
		} else if (expression.eContainer instanceof ComplexEventExpression) { // the expression is left
			val containerExpression = (expression.eContainer as ComplexEventExpression)
			// if there's a fol() or and() expression, star operator is not last
			return !containerExpression.right.exists [ che |
				che.operator.qualifiesAsFollowingOperator
			]
		} else if (expression.eContainer instanceof ChainedExpression) { // the expression is one of the rights
			val containerExpression = (expression.eContainer as ChainedExpression) as ChainedExpression
			Preconditions::checkArgument(containerExpression.eContainer instanceof ComplexEventExpression)

			val chainedExpressions = (containerExpression.eContainer as ComplexEventExpression).right

			val followingExpressions = chainedExpressions.subListFrom(expression)
			val hasFollowsExpression = followingExpressions.exists [ che |
				che.operator.qualifiesAsFollowingOperator
			]
			if (hasFollowsExpression) {
				return false
			} else {
				return (containerExpression.eContainer as ComplexEventExpression).starOperatorIsLast
			}
		} else {
			throw new IllegalArgumentException
		}
	}

	@Check
	def negativeOperatorAndOtherOperatorCombinations(ComplexEventExpression complexEventExpression) {
		if (complexEventExpression.negOperator == null) {
			return
		}

		if (!(complexEventExpression.left instanceof Atom)) {
			return
		}

		val primary = complexEventExpression.left as Atom

		val multiplicity = primary.multiplicity
		if (!multiplicity.nullOrOneMultiplicity) {
			error(
				"Cannot use multiplicity operator with a NOT expression.",
				VeplPackage.Literals.COMPLEX_EVENT_EXPRESSION__LEFT,
				NEGATIVE_WITH_MULTIPLICITY
			)
		}
		if (primary.hasTimewindow) {
			error(
				"Cannot use timewindow operator with a NOT expression.",
				VeplPackage.Literals.COMPLEX_EVENT_EXPRESSION__LEFT,
				NEGATIVE_WITH_TIMEWINDOW
			)
		}
	}

	@Check
	def paramOnlyOnAtomicEventPatternReference(ParameterizedPatternCall parameterizedPatternCall) {
		if (parameterizedPatternCall.parameterList == null) {
			return
		}
		if (parameterizedPatternCall.parameterList.parameters.empty) {
			return
		}
		if (!(parameterizedPatternCall.eventPattern instanceof AbstractAtomicEventPattern)) {
			error(
				"Parameters are only applicable to atomic event pattern references.",
				VeplPackage.Literals.PARAMETERIZED_PATTERN_CALL__PARAMETER_LIST,
				PARAMETER_ON_NON_ATOMIC_PATTERN_CALL
			)
		}
	}

	@Check
	def duplicateTraitParameterNamesInDiamondInheritance(AtomicEventPattern atomicEventPattern) {
		val traitList = atomicEventPattern.traits
		if (traitList == null) {
			return
		}

		val paramNames = Lists::newArrayList
		val errorousParamNames = Lists::newArrayList

		for (trait : traitList.traits) {
			for (param : trait.parameters.parameters) {
				val parameter = param.typedParameter
				if (paramNames.contains(parameter.name)) {
					errorousParamNames += parameter.name
				} else {
					paramNames += parameter.name
				}
			}
		}

		if (!errorousParamNames.empty) {
			error(
				"Duplicate parameter definition in traits. (" + errorousParamNames.foldWithComma,
				VeplPackage.Literals.ATOMIC_EVENT_PATTERN__TRAITS,
				DUPLICATE_TRAIT_PARAMETER_NAMES
			)
		}
	}

	@Check
	def traitParameterShadowing(AtomicEventPattern atomicEventPattern) {
		val traitList = atomicEventPattern.traits
		if (traitList == null) {
			return
		}

		val shadowedParameters = Lists::newArrayList

		for (parameter : atomicEventPattern.parameters.parameters) {
			for (trait : traitList.traits) {
				for (traitParam : trait.parameters.parameters) {
					if (traitParam.typedParameter.name.equals(parameter.name)) {
						shadowedParameters += traitParam.typedParameter.name
					}
				}
			}
		}

		if (!shadowedParameters.empty) {
			warning(
				"Parameters " + shadowedParameters.foldWithComma + " shadow parameters in the associated traits.",
				VeplPackage.Literals.ATOMIC_EVENT_PATTERN__TRAITS,
				SHADOWED_TRAIT_PARAMETERS
			)
		}
	}

	@Check
	def traitsAreExperimentalFeature(Trait trait) {
		info(
			"Traits are experimental features, use them carefully.",
			VeplPackage.Literals.TRAIT__PARAMETERS,
			TRAIT_EXPERIMENTAL
		)
	}
}
