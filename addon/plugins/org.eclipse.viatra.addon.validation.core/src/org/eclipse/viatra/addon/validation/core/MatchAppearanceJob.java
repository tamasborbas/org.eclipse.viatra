/*******************************************************************************
 * Copyright (c) 2010-2014, Balint Lorand, Zoltan Ujhelyi, Abel Hegedus, Tamas Szabo, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zoltan Ujhelyi, Abel Hegedus, Tamas Szabo - original initial API and implementation
 *   Balint Lorand - revised API and implementation
 *******************************************************************************/

package org.eclipse.viatra.addon.validation.core;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.viatra.addon.validation.core.violationkey.ViolationKey;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.emf.helper.ViatraQueryRuntimeHelper;

/**
 * The job is used to process new appearing matches upon constraint violation. It is associated to the rule that is
 * created for the constraint.
 * 
 * @author Balint Lorand
 * 
 */
public class MatchAppearanceJob implements IMatchProcessor<IPatternMatch> {

    private Constraint constraint;
    private Logger logger;

    public MatchAppearanceJob(Constraint constraint, Logger logger) {
        this.constraint = constraint;
        this.logger = logger;
    }

    @Override
    public void process(IPatternMatch match) {

        Map<String, Object> keyObjectMap = constraint.getSpecification().getKeyObjects(match);

        if (!keyObjectMap.isEmpty()) {

            ViolationKey key = constraint.getViolationKey(match);

            Violation violation = constraint.getViolation(key);

            if (violation == null) {
                violation = new Violation();
                violation.setConstraint(constraint);
                violation.setKeyObjects(constraint.getSpecification().getKeyObjects(match));
                violation.setMessage(ViatraQueryRuntimeHelper.getMessage(match, constraint.getSpecification()
                        .getMessageFormat()));
                constraint.addViolation(key, violation);

                constraint.notifyListenersViolationAppeared(violation);
            }

            if (violation.addMatch(match)) {
                violation.notifyListenersViolationEntryAppeared(match);
            }
        } else {
            logger.error("Error getting Violation key objects!");
        }
    }
}
