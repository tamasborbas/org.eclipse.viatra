/*******************************************************************************
 * Copyright (c) 2004-2015, Istvan David, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Istvan David - initial API and implementation
 *******************************************************************************/
 
package org.eclipse.viatra.cep.core.api.engine

import org.eclipse.viatra.cep.core.engine.runtime.ResettableEventTokenMatcher
import org.eclipse.viatra.cep.core.metamodels.automaton.InternalModel
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements

class ResetTransformations {
	extension BatchTransformationRuleFactory ruleFactory = new BatchTransformationRuleFactory
	extension BatchTransformation transformation
	extension BatchTransformationStatements statements
	extension IModelManipulations manipulation

	new(InternalModel internalModel) {
		transformation = BatchTransformation.forScope(new EMFScope(internalModel)).build
		statements = transformation.transformationStatements
		manipulation = new SimpleModelManipulations(transformation.queryEngine)
	}

	def resetAll() {
		deleteTokensDuringReset.fireWhilePossible
	}

	val deleteTokensDuringReset = createRule(ResettableEventTokenMatcher::querySpecification)[
			automaton.eventTokens.remove(eventToken)
		]
}