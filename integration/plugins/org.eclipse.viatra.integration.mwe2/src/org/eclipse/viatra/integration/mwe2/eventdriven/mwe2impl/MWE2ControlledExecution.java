/*******************************************************************************
 * Copyright (c) 2004-2015, Peter Lunk, Zoltan Ujhelyi and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Peter Lunk - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.integration.mwe2.eventdriven.mwe2impl;

import org.eclipse.viatra.integration.mwe2.eventdriven.IController;
import org.eclipse.viatra.transformation.evm.api.ConflictSetIterator;
import org.eclipse.viatra.transformation.evm.api.ScheduledExecution;
import org.eclipse.viatra.transformation.evm.api.event.EventRealm;

/**
 * A VIATRA EVM Executor, that enables the MWE 2 workflow to control any EVM based fine-grained event-driven
 * transformation, provided that the transformation's ExecutionSchema is created with this executor.
 * 
 * It also implements the IController interface which enables the workflow to explicitly start the execution of the
 * transformation. The MWE2ControllableExecutor also provides information about the state of the transformation (i.e.:
 * if the transformation has reached a steady state or not).
 * 
 * @author Peter Lunk
 *
 */
public class MWE2ControlledExecution extends ScheduledExecution implements IController {
    protected boolean canRun = false;
    protected boolean scheduled = false;
    protected boolean finished = false;

    public MWE2ControlledExecution(EventRealm realm) {
        super(realm);
    }

    /**
     * This method is typically called by the workflow. If the transformation was previously scheduled by its standard
     * EVM scheduler, calling this method will start the execution of the transformation immediately. If the
     * transformation was not scheduled before, a flag is set, that indicates that on the next schedule, the execution
     * of the transformation will commence.
     * 
     */
    @Override
    public void run() {
        if (scheduled) {
            finished = false;
            
            schedule();

            finished = true;
            canRun = false;
            scheduled = false;
        } else if (!canRun) {
            canRun = true;
        }
    }

    /**
     * This method is called by the EVM scheduler of the event-driven transformation. If the transformation is enabled,
     * calling of this method will result in the execution of the transformation. If the transformation has not been
     * enabled by the workflow, a flag is set.
     */
    @Override
    protected void schedule() {
        if (canRun) {
            finished = false;

            getExecutor().startExecution("Started MWE2 scheduling");
            getExecutor().execute(new ConflictSetIterator(getRuleBase().getAgenda().getConflictSet()));
            getExecutor().endExecution("Ended MWE2 scheduling");

            finished = true;
            canRun = false;
            scheduled = false;
        } else if (!scheduled) {
            scheduled = true;
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

}
