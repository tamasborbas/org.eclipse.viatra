/*******************************************************************************
 * Copyright (c) 2010-2016, Andras Szabolcs Nagy and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *   Andras Szabolcs Nagy - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.dse.evolutionary.survival;

import java.util.List;

import org.eclipse.viatra.dse.base.ThreadContext;
import org.eclipse.viatra.dse.evolutionary.interfaces.ISurvivalStrategy;
import org.eclipse.viatra.dse.objectives.TrajectoryFitness;

public class ParetoSurvivalStrategy implements ISurvivalStrategy {

    private int maxSurvivals;

    public ParetoSurvivalStrategy(int maxSurvivals) {
        this.maxSurvivals = maxSurvivals;
    }

    public ParetoSurvivalStrategy() {
        this(-1);
    }

    @Override
    public void init(ThreadContext context) {
    }

    @Override
    public List<TrajectoryFitness> selectSurvivedPopulation(
            List<? extends List<TrajectoryFitness>> frontsOfCurrentPopulation) {
        
        List<TrajectoryFitness> paretoFront = frontsOfCurrentPopulation.get(0);
        if (maxSurvivals > 0 && paretoFront.size() > maxSurvivals) {
            return paretoFront.subList(0, maxSurvivals);
        }
        return paretoFront;
    }

}
