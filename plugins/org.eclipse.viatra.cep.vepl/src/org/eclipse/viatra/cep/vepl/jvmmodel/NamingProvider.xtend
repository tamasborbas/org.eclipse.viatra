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
package org.eclipse.viatra.cep.vepl.jvmmodel

import com.google.inject.Inject
import org.eclipse.viatra.cep.vepl.vepl.AtomicEventPattern
import org.eclipse.viatra.cep.vepl.vepl.ComplexEventPattern
import org.eclipse.viatra.cep.vepl.vepl.EventModel
import org.eclipse.viatra.cep.vepl.vepl.EventPattern
import org.eclipse.viatra.cep.vepl.vepl.ModelElement
import org.eclipse.viatra.cep.vepl.vepl.QueryResultChangeEventPattern
import org.eclipse.viatra.cep.vepl.vepl.Rule
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName

class NamingProvider {
	@Inject extension IQualifiedNameProvider

	private static final String EVENTCLASS_PACKAGE_NAME_ELEMENT = "events"
	private static final String QUERYRESULT_EVENTCLASS_PACKAGE_NAME_ELEMENT = "events.queryresult"
	private static final String ATOMIC_PATTERN_PACKAGE_NAME_ELEMENT = "patterns.atomic"
	private static final String QUERYRESULT_PATTERN_PACKAGE_NAME_ELEMENT = "patterns.atomic.queryresult"
	private static final String COMPLEX_PATTERN_PACKAGE_NAME_ELEMENT = "patterns.complex"
	private static final String ANONYMOUS_PATTERN_PACKAGE_NAME_ELEMENT = "patterns.complex.anonymous"
	private static final String RULES_PACKAGE_NAME_ELEMENT = "rules"
	private static final String JOBS_PACKAGE_NAME_ELEMENT = "jobs"
	private static final String MAPPING_PACKAGE_NAME_ELEMENT = "mapping.QueryEngine2ViatraCep"

	private static final String ANONYMOUS_PATTERN_NAME = "_AnonymousPattern_"

	private static final String EVENT_SUFFIX = "_Event"
	private static final String QUERYRESULT_EVENT_SUFFIX = "_QueryResultEvent"
	private static final String PATTERN_SUFFIX = "_Pattern"
	private static final String JOB_SUFFIX = "_Job"

	def getClassFqn(ModelElement element) {
		var className = element.fullyQualifiedName.lastSegment
		var packageName = element.fullyQualifiedName.skipLast(1)

		if (element instanceof AtomicEventPattern) {
			return packageName.append(EVENTCLASS_PACKAGE_NAME_ELEMENT).append(className.toFirstUpper + EVENT_SUFFIX)
		} else if (element instanceof QueryResultChangeEventPattern) {
			return packageName.append(QUERYRESULT_EVENTCLASS_PACKAGE_NAME_ELEMENT).append(
				className.toFirstUpper + QUERYRESULT_EVENT_SUFFIX)
		}
	}

	def getPatternFqn(ModelElement element) {
		var className = element.fullyQualifiedName.lastSegment
		var packageName = element.fullyQualifiedName.skipLast(1)

		if (element instanceof AtomicEventPattern) {
			return packageName.append(ATOMIC_PATTERN_PACKAGE_NAME_ELEMENT).append(
				className.toFirstUpper + PATTERN_SUFFIX)
		} else if (element instanceof ComplexEventPattern) {
			return packageName.append(COMPLEX_PATTERN_PACKAGE_NAME_ELEMENT).append(
				className.toFirstUpper + PATTERN_SUFFIX)
		} else if (element instanceof QueryResultChangeEventPattern) {
			return packageName.append(QUERYRESULT_PATTERN_PACKAGE_NAME_ELEMENT).append(
				className.toFirstUpper + PATTERN_SUFFIX)
		}
	}

	def getAnonymousName(EventPattern element, int suffix) {
		var packageName = element.fullyQualifiedName.skipLast(1)

		return packageName.append(ANONYMOUS_PATTERN_PACKAGE_NAME_ELEMENT).append(ANONYMOUS_PATTERN_NAME + suffix)
	}

	def getFqn(Rule rule) {
		var className = rule.fullyQualifiedName.lastSegment
		var packageName = rule.fullyQualifiedName.skipLast(1)
		return packageName.append(RULES_PACKAGE_NAME_ELEMENT).append(className.toFirstUpper)
	}

	def getJobClassName(Rule rule) {
		var className = rule.fullyQualifiedName.lastSegment
		var packageName = rule.fullyQualifiedName.skipLast(1)
		return packageName.append(JOBS_PACKAGE_NAME_ELEMENT).append(className.toFirstUpper + JOB_SUFFIX)
	}

	def getQueryEngine2CepEngineClassFqn(QueryResultChangeEventPattern pattern) {
		var packageName = pattern.fullyQualifiedName.skipLast(1)
		return packageName.append(MAPPING_PACKAGE_NAME_ELEMENT)
	}

	def getPackageFqn(EventModel model) {
		model.fullyQualifiedName
	}

	def getFactoryFqn(EventModel model) {
		model.fullyQualifiedName.append("CepFactory")
	}

	def isEvent(QualifiedName fqn) {
		return fqn.toString.endsWith(EVENT_SUFFIX) || fqn.toString.endsWith(QUERYRESULT_EVENT_SUFFIX)
	}

	def isRule(QualifiedName fqn) {
		return fqn.toString.contains(RULES_PACKAGE_NAME_ELEMENT)
	}

	def getType(QualifiedName fqn) {
		if (fqn.toString.endsWith(EVENT_SUFFIX)) {
			"event class"
		} else if (fqn.toString.endsWith(QUERYRESULT_EVENT_SUFFIX)) {
			"query result event class"
		} else if (fqn.toString.endsWith(PATTERN_SUFFIX)) {
			if (fqn.toString.contains(ATOMIC_PATTERN_PACKAGE_NAME_ELEMENT)) {
				if (fqn.toString.contains(QUERYRESULT_PATTERN_PACKAGE_NAME_ELEMENT)) {
					"atomic query result event pattern"
				} else {
					"atomic event pattern"
				}
			} else {
				"complex event pattern"
			}
		} else if (fqn.toString.contains(RULES_PACKAGE_NAME_ELEMENT)) {
			"rule"
		}
	}

}
